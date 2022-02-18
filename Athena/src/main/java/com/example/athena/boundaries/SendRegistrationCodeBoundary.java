package com.example.athena.boundaries;

import com.example.athena.exceptions.SendEmailException;

import java.io.IOException;

public class SendRegistrationCodeBoundary extends SocketBoundary
{
    private static final byte[] buff = new byte[128] ;

    private SendRegistrationCodeBoundary(){

    }

    public static void sendCode(SendRegistrationBean params) throws SendEmailException
    {
        String email = params.getEmail() ;
        String code = params.getCode() ;

        String query = String.format("R%s;%s;", email, code) ;

        try
        {
            bZero() ;
            writeQuery(query) ;
            Socket socket = new Socket("localhost", 4545);
            OutputStream out = socket.getOutputStream();
            out.write(buff) ;

            bZero() ;
            InputStream in = socket.getInputStream();
            int readChars = in.read(buff, 0, 2) ;
            if(readChars != 2) throw new SendEmailException("Unknown response from server") ;

            if(buff[0] == 'F') throw new SendEmailException("Server error...") ;

            in.close() ;
            out.close() ;
            socket.close() ;
        }catch (IOException e)
        {
            throw new SendEmailException("Error in connection to server: " + e.getMessage()) ;
        }
    }
}
