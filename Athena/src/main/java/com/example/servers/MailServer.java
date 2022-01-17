package com.example.servers;

import javax.mail.* ;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Properties;

public class MailServer
{
    private static ServerSocket socket ;

    private static final byte[] buff = new byte[256] ;

    public static void main(String[] args) throws IOException
    {
        try
        {
            socket = new ServerSocket(4545) ;
        }
        catch(SocketException e)
        {
            System.out.println(e.getMessage()) ;
            System.exit(1) ;
        }

        Properties properties = new Properties() ;

        properties.put("mail.smtp.host", "smtp.libero.it") ;
        properties.put("mail.smtp.port", "465") ;
        properties.put("mail.smtp.auth", "true") ;
        properties.put("mail.smtp.socketFactory.port", "465") ;
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory") ;

        System.out.println("Insert the smtp username: ") ;
        String username = new String(buff, 0, System.in.read(buff)) ;
        System.out.println("Insert the smtp password: ") ;
        String password = new String(buff, 0, System.in.read(buff)) ;

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password) ;
            }
        }) ;

        session.setDebug(true) ;


        Socket clientSocket ;
        while(true)
        {
            clientSocket = socket.accept() ;
            InputStream in = new BufferedInputStream(clientSocket.getInputStream());
            OutputStream out = new BufferedOutputStream(clientSocket.getOutputStream());

            String receivedMessage = new String(buff, 0, in.read(buff)) ;
            String[] tokens = receivedMessage.split(";") ;

            try
            {
                MimeMessage message = new MimeMessage(session) ;
                message.setFrom(new InternetAddress(username));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(tokens[0])) ;
                message.setSubject("Server message");
                message.setText("This is an automated message,\n" +
                        "a tutor has generated a review code for you :" + tokens[1]) ;

                Transport.send(message) ;

                System.out.println("Message sent to " + tokens[0]) ;

                out.write("T".getBytes(), 0, "T".length()) ;
            }catch (MessagingException e)
            {
                e.printStackTrace() ;
                out.write("F".getBytes(), 0, "F".length()) ;
            }finally
            {
                in.close() ;
                out.close() ;
                clientSocket.close() ;
            }
        }
    }
}
