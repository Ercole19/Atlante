package com.example.athena.View;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class FakePaymentSystem
{
    private ServerSocket serverSocket ;
    private final Random random ;

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
        random = new Random(42) ;
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
        while(true)
        {
            paymentSystem.clientAcceptance() ;
        }
    }
}
