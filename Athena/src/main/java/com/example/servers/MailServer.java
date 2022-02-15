package com.example.servers ;

import javax.mail.* ;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import java.util.logging.*;

public class MailServer implements Runnable {
    private static ServerSocket socket;

    private static Session session;

    private static final byte[] buff = new byte[128];

    private static InternetAddress sender;

    private static final Logger LOGGER = Logger.getLogger(MailServer.class.getName());

    private static final LinkedBlockingQueue<CommandSocketWrapper> REGISTRATIONQUEUE = new LinkedBlockingQueue<>() ;

    private static final ConcurrentHashMap<LocalDateTime, List<String>> REMINDERSMAP = new ConcurrentHashMap<>() ;

    private static final LinkedBlockingQueue<CommandSocketWrapper> REVIEWSQUEUE = new LinkedBlockingQueue<>() ;

    private static boolean quit = false;

    public static void main(String[] args) {

        try {
            socket = new ServerSocket(4545);
            StreamHandler myHandler = new StreamHandler(new BufferedOutputStream(new FileOutputStream("src/main/resources/mailServerLog")),
                    new SimpleFormatter());
            LOGGER.addHandler(myHandler);
        } catch (IOException e) {
            System.exit(1);
        }

        try {
            Properties properties = new Properties();

            properties.put("mail.smtp.host", "smtp.libero.it");
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.socketFactory.port", "465");
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.ssl.checkserveridentity", true);

            LOGGER.log(Level.INFO, "Insert the smtp username: ");
            String username = new String(buff, 0, System.in.read(buff) - 1);
            LOGGER.log(Level.INFO, "Insert the smtp password: ");
            String password = new String(buff, 0, System.in.read(buff) - 1);

            session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            sender = new InternetAddress(username);

            session.setDebug(true);

            Thread registerThread = new Thread(new RegisterService(), "registrations");
            Thread notifierThread = new Thread(new NotificationService(), "notifications");
            Thread reviewThread = new Thread(new ReviewService(), "reviews") ;
            Thread quitThread = new Thread(new MailServer(), "quitting") ;

            registerThread.start() ;
            notifierThread.start() ;
            reviewThread.start() ;
            quitThread.start() ;

            Socket clientSocket;
            while(!quit) {
                clientSocket = socket.accept();
                InputStream in = clientSocket.getInputStream();

                String receivedMessage = new String(buff, 0, in.read(buff));

                Character setting = receivedMessage.charAt(0);

                if (setting.equals('R')) {
                    CommandSocketWrapper wrapper = new CommandSocketWrapper(clientSocket, receivedMessage.substring(1));
                    REGISTRATIONQUEUE.add(wrapper);
                }
                else if(setting.equals('T'))
                {
                    CommandSocketWrapper wrapper = new CommandSocketWrapper(clientSocket, receivedMessage.substring(1)) ;
                    REVIEWSQUEUE.add(wrapper) ;
                }
                else if (setting.equals('N')) {
                    String dateToParse = receivedMessage.substring(1, receivedMessage.indexOf(";"));
                    List<String> elements = REMINDERSMAP.getOrDefault(LocalDateTime.parse(dateToParse).truncatedTo(ChronoUnit.MINUTES), new ArrayList<>());
                    elements.add(receivedMessage.substring(receivedMessage.indexOf(";") +1));
                    REMINDERSMAP.put(LocalDateTime.parse(dateToParse), elements);

                    OutputStream out = clientSocket.getOutputStream() ;

                    out.write("T".getBytes());
                }
            }
        }
        catch (IOException e)
        {
            LOGGER.log(Level.SEVERE, "Error in reading/writing to socket. Details: {0}", e.getMessage());
            System.exit(1);
        }
        catch (AddressException e)
        {
            LOGGER.log(Level.SEVERE, "Unable to resolve username's host. Details: {0}", e.getMessage());
            System.exit(1);
        }
    }

    private static class RegisterService implements Runnable {
        @Override
        public void run() {
            registrations();
        }

        public static void registrations() {
            while (!quit) {
                try {
                    sendMessageRegistrationReview('R');
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error in writing response. Details follow: {0}", e.getMessage());
                }
            }
        }
    }

    private static class NotificationService implements Runnable {

        @Override
        public void run() {
            notifications();
        }

        private static void notifications() {
            LocalDateTime lastSent = null;
            LocalDateTime actual;
            String[] tokens = null;

            while (!quit) {
                try {
                    actual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES) ;

                    if (!actual.equals(lastSent)) {
                        lastSent = actual;
                        List<String> reminders = REMINDERSMAP.get(actual);
                        if (reminders == null) continue;

                        for (String remainder : reminders) {
                            tokens = remainder.split(";");

                            MimeMessage message = new MimeMessage(session);
                            message.setFrom(sender);
                            message.addRecipient(Message.RecipientType.TO, new InternetAddress(tokens[0]));
                            message.setSubject("Remainder of your event");
                            message.setText("This email is a reminder for your event: \n" +
                                    tokens[1] + "\n" +
                                    "on " + tokens[2] + " from " + tokens[3] + " to " + tokens[4] + ".\n" +
                                    "Details: " + tokens[5]) ;

                            Transport.send(message);
                        }

                        REMINDERSMAP.remove(actual) ;
                    }
                } catch (MessagingException e) {
                    LOGGER.log(Level.SEVERE, "Unable to send remainder message to {0}", tokens[0]);
                }
            }
        }

    }

    private static class ReviewService implements Runnable
    {

        @Override
        public void run() {
            reviewsSending() ;
        }

        private static void reviewsSending()
        {
            while (!quit) {
                try {
                    sendMessageRegistrationReview('T') ;
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error in writing response. Details follow: {0}", e.getMessage());
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            int readChars;
            byte[] buffer = new byte[16] ;
            do {
                LOGGER.log(Level.INFO, "Type 'quit' to quit");
                readChars = System.in.read(buffer, 0, 5);
            } while (readChars != 5 || Arrays.equals(buffer, "quit".getBytes())) ;

            updateQuit(true) ;
            System.exit(1) ;

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error in reading from stdin. Exiting.") ;
            updateQuit(true) ;
            System.exit(1) ;
        }
    }

    public static synchronized void updateQuit(boolean value)
    {
        quit = value ;
    }

    private static void sendMessageRegistrationReview(char kind) throws IOException
    {
        String[] tokens = null ;
        Socket clientSocket = null;
        CommandSocketWrapper wrapper ;
        try {
            if(kind == 'T')
            {
                wrapper = REVIEWSQUEUE.take();
            }
            else
            {
                wrapper = REGISTRATIONQUEUE.take() ;
            }
            tokens = wrapper.getCommand().split(";");
            clientSocket = wrapper.getClientSocket() ;
        }catch (InterruptedException e)
        {
            LOGGER.log(Level.SEVERE, "Error in retrieving from queue. Details: {0}", e.getMessage());
            Thread.currentThread().interrupt() ;
        }

        OutputStream out = null ;
        try{

            assert clientSocket != null;
            out = clientSocket.getOutputStream() ;

            MimeMessage message = new MimeMessage(session);
            message.setFrom(sender);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(tokens[0]));
            if(kind == 'T')
            {
                message.setSubject("A review code form a Tutor") ;
                message.setText("A tutor has sent you a review code for a tutoring you had with him.\n" +
                        "The code is" + tokens[1] + "\n" +
                        "If you didn't request this code, please ignore the message.");
            }
            else
            {
                message.setSubject("Confirm your registration") ;
                message.setText("Your confirmation code is: " + tokens[1] + "\n" +
                        "If you didn't request this code, please ignore the message.");
            }

            Transport.send(message);

            out.write("T".getBytes());

            LOGGER.log(Level.INFO, "Message sent to {0}", tokens[0]);

        }  catch (MessagingException e) {
            out = clientSocket.getOutputStream() ;
            LOGGER.log(Level.SEVERE, "Error in sending email. Error message following {0}", e.getMessage());
            out.write("F".getBytes());
            out.close() ;
        } finally {
            assert out != null ;
            out.close() ;
            clientSocket.close();
        }
    }
}