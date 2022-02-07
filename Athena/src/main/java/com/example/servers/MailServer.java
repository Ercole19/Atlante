package com.example.servers;

import javax.mail.* ;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
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

    private static final byte[] buff = new byte[256];

    private static InternetAddress sender;

    private static final Logger LOGGER = Logger.getLogger(MailServer.class.getName());

    private static final LinkedBlockingQueue<CommandSocketWrapper> REGISTRATIONQUEUE = new LinkedBlockingQueue<>();

    private static final ConcurrentHashMap<LocalDateTime, List<String>> REMINDERSMAP = new ConcurrentHashMap<>();

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

            Thread registerThread = new Thread(new RegisterService());
            Thread notifierThread = new Thread(new NotificationService());
            Thread quitThread = new Thread(new MailServer()) ;

            registerThread.start();
            notifierThread.start();
            quitThread.start() ;

            Socket clientSocket;
            while(!quit) {
                clientSocket = socket.accept();
                InputStream in = new BufferedInputStream(clientSocket.getInputStream());

                String receivedMessage = new String(buff, 0, in.read(buff));

                Character setting = receivedMessage.charAt(0);

                if (setting.equals('R')) {
                    CommandSocketWrapper wrapper = new CommandSocketWrapper(clientSocket, receivedMessage.substring(1));
                    REGISTRATIONQUEUE.add(wrapper);
                } else if (setting.equals('N')) {
                    String dateToParse = receivedMessage.substring(1, receivedMessage.indexOf(";"));
                    List<String> elements = REMINDERSMAP.getOrDefault(LocalDateTime.parse(dateToParse), new ArrayList<>());
                    elements.add(receivedMessage.substring(receivedMessage.indexOf(";")) + 1);
                    REMINDERSMAP.put(LocalDateTime.parse(dateToParse), elements);

                    OutputStream out = new BufferedOutputStream(clientSocket.getOutputStream());

                    out.write("T".getBytes(), 0, "T".length());
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

        private static Socket clientSocket ;
        private static String[] tokens ;

        @Override
        public void run() {
            registrations();
        }

        public static void registrations() {
            tokens = null;

            while (!quit) {
                try {
                    clientSocket = null;
                    sendMessage() ;
                } catch (IOException e) {
                    Object[] params = {tokens[0], e.getMessage()};
                    LOGGER.log(Level.SEVERE, "Error in writing response to {0}. Details follow: {1}", params);
                }
            }
        }

        private static void sendMessage() throws IOException
        {
            try (OutputStream out = new BufferedOutputStream(clientSocket.getOutputStream())){
                CommandSocketWrapper wrapper = REGISTRATIONQUEUE.take();
                tokens = wrapper.getCommand().split(";");

                clientSocket = wrapper.getClientSocket();

                MimeMessage message = new MimeMessage(session);
                message.setFrom(sender);
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(tokens[0]));
                message.setSubject("Confirm your registration");
                message.setText("Your confirmation code is: " + tokens[1] + "\n" +
                        "If you didn't request this code, please ignore the message.");

                Transport.send(message);

                out.write("T".getBytes(), 0, "T".length());

                LOGGER.log(Level.INFO, "Message sent to {0}", tokens[0]);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, "Error in retrieving from queue. Details: {0}", e.getMessage());
                Thread.currentThread().interrupt() ;
            } catch (MessagingException e) {
                OutputStream out = new BufferedOutputStream(clientSocket.getOutputStream()) ;
                LOGGER.log(Level.SEVERE, "Error in sending email. Error message following {0}", e.getMessage());
                out.write("F".getBytes(), 0, "F".length());
                out.close() ;
            } finally {
                assert clientSocket != null;
                clientSocket.close();
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

            while (true) {
                try {
                    actual = LocalDateTime.now();

                    if (!actual.equals(lastSent)) {
                        lastSent = actual;
                        List<String> reminders = REMINDERSMAP.get(actual);
                        if (reminders.isEmpty()) continue;

                        for (String remainder : reminders) {
                            tokens = remainder.split(";");

                            MimeMessage message = new MimeMessage(session);
                            message.setFrom(sender);
                            message.addRecipient(Message.RecipientType.TO, new InternetAddress(tokens[0]));
                            message.setSubject("Remainder of your event");
                            message.setText("This email is a reminder for your event: \n" +
                                    tokens[1] + "\n" +
                                    "from " + tokens[2] + " to " + tokens[3]);

                            Transport.send(message);
                        }
                    }
                } catch (MessagingException e) {
                    LOGGER.log(Level.SEVERE, "Unable to send remainder message to {0}", tokens[0]);
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
            } while (readChars != 5 || !Arrays.equals(buffer, "quit".getBytes())) ;

            updateQuit(true) ;

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
}