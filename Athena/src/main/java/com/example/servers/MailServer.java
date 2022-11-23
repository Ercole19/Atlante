package com.example.servers ;

import com.example.athena.beans.normal.MailServerBean;
import com.example.athena.beans.normal.MailServerResponseBean;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class MailServer implements Runnable {
    private ServerSocket socket;

    private static final Logger LOGGER = Logger.getLogger(MailServer.class.getName());

    private static final LinkedBlockingQueue<CommandSocketWrapper> DIRECTMAILQUEUE = new LinkedBlockingQueue<>() ;

    private static final ConcurrentHashMap<LocalDateTime, List<MailServerBean>> REMINDERSMAP = new ConcurrentHashMap<>() ;

    private static boolean quit = false;

    private final Map<String, SessionInfo> sessionMap = new HashMap<>();

    private static MailServer instance = null ;

    private MailAuth mailAuth ;
    private ParsingStates state ;

    private MailServer() {

        socketLoggerInstantiation() ;

        try(BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/mailServerAUTHS"))) {

            String readLine = reader.readLine();
            state = ParsingStates.INIT ;
            mailAuth = null;

            while (readLine != null) {
                String[] tokens = readLine.split(" : ");

                checkTokens(tokens);

                if (state == ParsingStates.INIT) {
                    initBehavior(tokens[0], tokens[1]);
                } else {
                    if (Objects.equals(tokens[0], "NEW AUTH")) {
                        crash("Error in parsing mail auths");
                    } else if (Objects.equals(tokens[0], "END AUTH")) {
                        terminateAuthCompilation();
                    } else {
                        setAuthProperty(tokens[0], tokens[1]) ;
                    }
                }
                readLine = reader.readLine() ;
            }

            if(state == ParsingStates.AUTH_COMPILING) crash("Error in auth parsing") ;
        } catch (FileNotFoundException e) {
            crash("No auths file found");
            System.exit(1) ;
        } catch (IOException e) {
            crash("IO error occurred");
        } catch (NullPointerException e) {
            crash("Error in auth parsing") ;
        }
    }

    private void socketLoggerInstantiation() {
        try {
            socket = new ServerSocket(4545);
            StreamHandler myHandler = new StreamHandler(new BufferedOutputStream(new FileOutputStream("src/main/resources/mailServerLog")),
                    new SimpleFormatter());
            LOGGER.addHandler(myHandler);
        } catch (IOException e) {
            System.exit(1);
        }
    }

    private void initBehavior(String firstToken, String secondToken) {
        if (Objects.equals(firstToken, "NEW AUTH")) {
            mailAuth = new MailAuth();
            mailAuth.setAuthName(secondToken);
            state = ParsingStates.AUTH_COMPILING;
        } else {
            crash("Error in parsing mail auths");
        }
    }

    private void terminateAuthCompilation() {
        try {
            Properties properties = mailAuth.getProperties();
            String username = mailAuth.getUsername();
            String password = mailAuth.getPassword();
            String authName = mailAuth.getAuthName();
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            session.setDebug(true);
            this.sessionMap.put(authName, new SessionInfo(session, username));
            state = ParsingStates.INIT;
        } catch (AddressException e) {
            crash("Could not find username's inet address");
        }

    }

    private void checkTokens(String[] tokens) {
        if(tokens.length != 2 && !(tokens.length == 1 && Objects.equals(tokens[0],"END AUTH")) ) crash("Error in mail auth entry format");
    }

    private void setAuthProperty(String firstToken, String secondToken) {
        if(Objects.equals(firstToken, "SMTP_USER")) mailAuth.setUsername(secondToken);
        else if(Objects.equals(firstToken, "SMTP_PASS")) mailAuth.setPassword(secondToken);
        else mailAuth.setProperty(firstToken, secondToken) ;
    }

    public static synchronized MailServer getInstance() {
        if(instance == null) {
            instance = new MailServer() ;
        }
        return instance ;
    }

    private void crash(String message) {
        LOGGER.log(Level.SEVERE, message) ;
        System.exit(1);
    }

    public void start() {
        try {
            Thread registerThread = new Thread(new DirectMailService(), "registrations");
            Thread notifierThread = new Thread(new NotificationService(), "notifications");
            Thread quitThread = new Thread(MailServer.getInstance(), "quitting") ;

            registerThread.start() ;
            notifierThread.start() ;
            quitThread.start() ;

            Socket clientSocket;
            while(!quit) {
                clientSocket = socket.accept();
                InputStream in = clientSocket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(in) ;

                MailServerBean receivedMessage = (MailServerBean) objectInputStream.readObject() ;

                String setting = receivedMessage.getClassName() ;

                switch (setting) {
                    case "M":
                        CommandSocketWrapper wrapper = new CommandSocketWrapper(clientSocket, receivedMessage);
                        DIRECTMAILQUEUE.add(wrapper);
                        break;
                    case "N": {
                        OutputStream out = clientSocket.getOutputStream();

                        String mailAuthName = receivedMessage.getMailAccount();
                        if (!this.sessionMap.containsKey(mailAuthName)) out.write("F".getBytes());
                        else {
                            String dateToParse = receivedMessage.getSendMoment();
                            List<MailServerBean> elements = REMINDERSMAP.getOrDefault(LocalDateTime.parse(dateToParse).truncatedTo(ChronoUnit.MINUTES), new ArrayList<>());
                            elements.add(receivedMessage);
                            REMINDERSMAP.put(LocalDateTime.parse(dateToParse), elements);

                            MailServerResponseBean response = new MailServerResponseBean() ;
                            response.setCode(0);
                            response.setMessage("OK");
                            response.setDetails("Registration successful");

                            ObjectOutputStream outputStream = new ObjectOutputStream(out) ;
                            outputStream.writeObject(response) ;
                        }
                        break;
                    }
                    case "R": {
                        OutputStream out = clientSocket.getOutputStream();

                        String dateToParse = receivedMessage.getSendMoment();
                        List<MailServerBean> elements = REMINDERSMAP.getOrDefault(LocalDateTime.parse(dateToParse).truncatedTo(ChronoUnit.SECONDS), new ArrayList<>());
                        elements.remove(receivedMessage);
                        REMINDERSMAP.put(LocalDateTime.parse(dateToParse), elements);

                        MailServerResponseBean response = new MailServerResponseBean() ;
                        response.setCode(0);
                        response.setMessage("OK");
                        response.setDetails("Elimination from queue successful");

                        ObjectOutputStream outputStream = new ObjectOutputStream(out) ;
                        outputStream.writeObject(response) ;

                        break;
                    }
                }
            }
        }
        catch (IOException e)
        {
            LOGGER.log(Level.SEVERE, "Error in reading/writing to socket. Details: {0}", e.getMessage());
            System.exit(1);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        MailServer.getInstance().start() ;

    }

    private void sendMail(SessionInfo info, String recipient, String subject, String text) throws MessagingException {
        MimeMessage message = new MimeMessage(info.getSession());
        message.setFrom(info.getSender());
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(subject);
        message.setText(text) ;

        Transport.send(message);
    }

    private static class DirectMailService implements Runnable {
        @Override
        public void run() {
            mailSender();
        }

        public void mailSender() {
            while (!quit) {
                try {
                    MailServer.getInstance().sendMailDirectly();
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

        private void notifications() {
            LocalDateTime lastSent = null;
            LocalDateTime actual;

            while (!quit) {

                actual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES) ;

                if (!actual.equals(lastSent)) {
                    lastSent = actual;
                    List<MailServerBean> reminders = REMINDERSMAP.get(actual);
                    if (reminders == null) continue;

                    for (MailServerBean remainder : reminders) {

                        SessionInfo info = MailServer.getInstance().sessionMap.get(remainder.getMailAccount()) ;
                        try {
                            MailServer.getInstance().sendMail(info, remainder.getRecipient(), remainder.getMailObject(), remainder.getContent());
                        } catch (MessagingException e) {
                            LOGGER.log(Level.SEVERE, "Unable to send remainder message to {0}", remainder.getRecipient());
                        }

                    }

                    REMINDERSMAP.remove(actual) ;
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

    public synchronized void updateQuit(boolean value)
    {
        quit = value ;
    }

    private void sendMailDirectly() throws IOException
    {
        MailServerBean command = null ;
        Socket clientSocket = null;
        CommandSocketWrapper wrapper ;
        try {

            wrapper = DIRECTMAILQUEUE.take() ;

            command = wrapper.getCommand();
            clientSocket = wrapper.getClientSocket() ;
        }catch (InterruptedException e)
        {
            LOGGER.log(Level.SEVERE, "Error in retrieving from queue. Details: {0}", e.getMessage());
            Thread.currentThread().interrupt() ;
        }

        OutputStream out = null ;
        try{

            assert clientSocket != null;
            assert command != null ;
            out = clientSocket.getOutputStream() ;
            SessionInfo info = this.sessionMap.get(command.getMailAccount()) ;
            sendMail(info, command.getRecipient(), command.getMailObject(), command.getContent());

            MailServerResponseBean response = new MailServerResponseBean() ;
            response.setCode(0);
            response.setMessage("OK");
            response.setDetails("Mail sent successfully");

            ObjectOutputStream outputStream = new ObjectOutputStream(out) ;
            outputStream.writeObject(response) ;

            LOGGER.log(Level.INFO, "Message sent to {0}", command.getRecipient());

        }  catch (MessagingException e) {
            out = clientSocket.getOutputStream() ;
            LOGGER.log(Level.SEVERE, "Error in sending email. Error message following {0}", e.getMessage());
            out.write("F".getBytes());

            MailServerResponseBean response = new MailServerResponseBean() ;
            response.setCode(1);
            response.setMessage("ERROR");
            response.setDetails("Error in sending mail, details follow "+ e.getMessage());

            ObjectOutputStream outputStream = new ObjectOutputStream(out) ;
            outputStream.writeObject(response) ;

            out.close() ;
        } finally {
            assert out != null ;
            out.close() ;
            clientSocket.close();
        }
    }
}