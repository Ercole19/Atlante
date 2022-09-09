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


public class FakePaymentSystem extends QuitterServer
{
    private ServerSocket serverSocket ;
    private static final Logger logger = Logger.getLogger("FakePaymentSystem") ;
    private final SecureRandom random ;



    private FakePaymentSystem()
    {
        try {
            serverSocket = new ServerSocket(6351) ;
            logger.addHandler(new StreamHandler(new BufferedOutputStream(new FileOutputStream("src/main/resources/fakePaymentSystemLog")),
                    new SimpleFormatter())) ;
        }
        catch (IOException e)
        {
            org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(IsbnCheckBoundary.class);
            log.error("error!", e);
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
            org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(IsbnCheckBoundary.class);
            log.error("error!", e);
        }
    }



    public static void main(String[] args)
    {
        FakePaymentSystem paymentSystem = new FakePaymentSystem() ;
        (new Thread(paymentSystem)).start() ;
        do
        {
           logger.log(Level.INFO, "Type 'quit' to quit") ;
            paymentSystem.clientAcceptance() ;
        }while(!Arrays.equals(paymentSystem.buffer, "quit".getBytes())) ;
    }


}
