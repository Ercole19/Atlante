package com.example.servers;

import com.example.athena.boundaries.IsbnCheckBoundary;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;


public class FakePaymentSystem implements Runnable
{
    private ServerSocket serverSocket ;
    private final SecureRandom random ;
    private byte[] buffer = new byte[5] ;
    private Logger logger;

    private FakePaymentSystem()
    {
        try {
            serverSocket = new ServerSocket(6351) ;
            logger = Logger.getLogger(this.getClass().getName()) ;
            logger.addHandler(new StreamHandler(new BufferedOutputStream(new FileOutputStream("src/main/resources/fakePaymentSystemLog")),
                    new SimpleFormatter())) ;
        }
        catch (IOException e)
        {
            org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(IsbnCheckBoundary.class);
            logger.error("error!", e);
            System.exit(1) ;
        }
        random = new SecureRandom() ;
    }

    private boolean executePayment()
    {
        return random.nextBoolean() ;
    }

    private void clientAcceptance()
    {
        try
        {
            Socket clientSocket = this.serverSocket.accept();
            byte[] buff = new byte[2] ;

            if(this.executePayment())
            {
                buff[0] = 't' ;
                logger.log(Level.INFO, "Payment successful for {0}", clientSocket.getInetAddress()) ;
            }
            else
            {
                buff[0] = 'f' ;
                logger.log(Level.INFO,"Payment failed for {0}", clientSocket.getInetAddress()) ;
            }

            clientSocket.getOutputStream().write(buff, 0, buff.length) ;
        }
        catch (IOException e)
        {
            org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(IsbnCheckBoundary.class);
            logger.error("error!", e);
        }
    }



    public static void main(String[] args)
    {
        FakePaymentSystem paymentSystem = new FakePaymentSystem() ;
        (new Thread(paymentSystem)).start() ;
        do
        {
            paymentSystem.clientAcceptance() ;
        }while(!Arrays.equals(paymentSystem.buffer, "quit".getBytes())) ;
    }

    @Override
    public void run()
    {
        try
        {
            int readChars ;
            do
            {
                logger.log(Level.INFO, "Type 'quit' to quit") ;
                readChars = System.in.read(buffer, 0, 5) ;
            }while(readChars != 5 || Arrays.equals(buffer, "quit".getBytes())) ;
        }catch (IOException e)
        {
            buffer = "quit".getBytes() ;
            System.exit(1) ;
        }
    }
}
