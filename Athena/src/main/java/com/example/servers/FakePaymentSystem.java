package com.example.servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.Arrays;


public class FakePaymentSystem implements Runnable
{
    private ServerSocket serverSocket ;
    private final SecureRandom random ;
    private byte[] buffer = new byte[5] ;

    private FakePaymentSystem()
    {
        try {
            serverSocket = new ServerSocket(6351) ;
        }
        catch (IOException e)
        {
            e.printStackTrace() ;
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

            if(this.executePayment()) buff[0] = 't' ;
            else buff[0] = 'f' ;

            clientSocket.getOutputStream().write(buff, 0, buff.length) ;
        }
        catch (IOException e)
        {
            e.printStackTrace() ;
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
                System.out.println("Type 'quit' to quit") ;
                readChars = System.in.read(buffer, 0, 5) ;
            }while(readChars != 5 || Arrays.equals(buffer, "quit".getBytes())) ;
        }catch (IOException e)
        {
            buffer = "quit".getBytes() ;
            System.exit(1) ;
        }
    }
}
