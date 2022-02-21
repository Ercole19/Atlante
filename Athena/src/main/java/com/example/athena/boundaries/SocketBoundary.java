package com.example.athena.boundaries;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public abstract class SocketBoundary
{
    protected static final byte[] buff = new byte[128] ;

    protected static void bZero()
    {
        for(int i = 0; i < 128; i++)
        {
            buff[i] = 0 ;
        }
    }

    protected static boolean writeQuery(String query){
        if (query.length() > 128) return false ;

        int i = 0;
        for (byte b : query.getBytes(StandardCharsets.UTF_8)) {
            buff[i] = b;
            i++;
        }

        return true ;
    }

    protected static String sendMessageGetResponse(String query, int port) throws IOException
    {
        bZero() ;
        if(!writeQuery(query)) return "Error, the message is too long" ;
        Socket socket = new Socket("localhost", port) ;
        OutputStream out = socket.getOutputStream();
        out.write(buff) ;

        bZero() ;
        InputStream in = socket.getInputStream();
        int readChars = in.read(buff, 0, 2) ;
        if(readChars != 2) return "Unknown response from server" ;

        if(buff[0] == 'F') return "Server error..." ;

        in.close() ;
        out.close() ;
        socket.close() ;

        return "OK" ;
    }

    protected static String getResponse(int port) throws IOException
    {
        Socket socket = new Socket("localhost", port) ;
        bZero() ;
        InputStream in = socket.getInputStream();
        int readChars = in.read(buff, 0, 2) ;
        if(readChars != 2) return "Unknown response from server" ;

        if(buff[0] == 'F') return "Payment failed" ;

        in.close() ;
        socket.close() ;

        return "OK" ;
    }
}
