package com.example.athena.boundaries;

import com.example.athena.exceptions.SendEmailException;

import java.io.IOException;

public class SendRegistrationCodeBoundary extends SocketBoundary
{

    private SendRegistrationCodeBoundary(){

    }

    public static void sendCode(SendRegistrationBean params) throws SendEmailException
    {
        String email = params.getEmail() ;
        String code = params.getCode() ;

        String query = String.format("R%s;%s;", email, code) ;

        try
        {
            String retMessage = sendMessageGetResponse(query, 4545) ;
            if(!retMessage.equals("OK"))
            {
                throw new SendEmailException(retMessage) ;
            }
        }catch (IOException e)
        {
            throw new SendEmailException("Error in connection to server: " + e.getMessage()) ;
        }
    }
}
