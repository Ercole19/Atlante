package com.example.athena.boundaries;

import com.example.athena.exceptions.SendEmailException;

import java.io.*;
import java.net.*;

public class SendReviewCodeEmailBoundary
{
    private static Socket socket ;

    private static OutputStream out ;
    private static InputStream in ;

    private static final byte[] buff = new byte[256] ;


    private SendReviewCodeEmailBoundary(){

    }


    private static void createConnection() throws IOException
    {
        InetAddress address = InetAddress.getByName("localhost");
        socket = new Socket(address, 4545) ;
        out = new BufferedOutputStream(socket.getOutputStream()) ;
        in = new BufferedInputStream(socket.getInputStream()) ;
    }

    private static String sendMessage(String content) throws IOException
    {
        out.write(content.getBytes(), 0, content.length()) ;
        return new String(buff, 0, in.read(buff)) ;
    }

    private static void closeConnection()
    {
        try
        {
            in.close() ;
            out.close() ;
            socket.close() ;
        }
        catch(IOException e)
        {
            e.printStackTrace() ;
        }
    }

    public static void sendEmail(String recipient, String code) throws SendEmailException
    {
        try
        {
            createConnection() ;
            String packetContent = String.format("%s;%s", recipient, code) ;
            if(sendMessage(packetContent).equals("F")) throw new SendEmailException("Unable to send request") ;
            closeConnection() ;

        }
        catch(SocketException | UnknownHostException e)
        {
            throw new SendEmailException("Unable to create a new Socket or resolve address") ;
        }
        catch(IOException e)
        {
            throw new SendEmailException("Unable to send request") ;
        }
    }
}
