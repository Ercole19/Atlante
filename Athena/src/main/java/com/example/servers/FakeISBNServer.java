package com.example.servers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class FakeISBNServer extends QuitterServer{

    private ServerSocket serverSocket ;
    private static final Logger logger = Logger.getLogger("FakeISBNServer") ;

    private FakeISBNServer() {
        try {
            serverSocket = new ServerSocket(9876) ;
            logger.addHandler(new StreamHandler(new BufferedOutputStream(new FileOutputStream("src/main/resources/fakeISBNServerLog")),
                new SimpleFormatter())) ;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error instantiating server");
        }
    }

    private void clientAcceptance()
    {
        try
        {
            Socket clientSocket = this.serverSocket.accept();
            byte[] buff = new byte[2] ;

            buff[0] = 'T' ;

            clientSocket.getOutputStream().write(buff, 0, 1) ;
        }
        catch (IOException e)
        {
            logger.log(Level.SEVERE, "Error in sending response") ;
        }
    }

    public static void main(String[] args)
    {
        FakeISBNServer isbnServer = new FakeISBNServer() ;
        (new Thread(isbnServer)).start() ;
        do
        {
            logger.log(Level.INFO, "Type 'quit' to quit") ;
            isbnServer.clientAcceptance() ;

        }while(!Arrays.equals(isbnServer.buffer, "quit".getBytes())) ;
    }

}
