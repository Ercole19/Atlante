package com.example.servers;

import java.io.IOException;
import java.util.Arrays;

public abstract class QuitterServer implements Runnable{

    protected byte[] buffer = new byte[5] ;


    @Override
    public void run()
    {
        try
        {
            int readChars ;
            do
            {
                readChars = System.in.read(buffer, 0, 5) ;
            }while(readChars != 5 || Arrays.equals(buffer, "quit".getBytes())) ;
            System.exit(0) ;
        }catch (IOException e)
        {
            buffer = "quit".getBytes() ;
            System.exit(1) ;
        }
    }
}
