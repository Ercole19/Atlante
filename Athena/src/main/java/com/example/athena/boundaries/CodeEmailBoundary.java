package com.example.athena.boundaries;

import com.example.athena.exceptions.SendEmailException;

import java.io.IOException;

public abstract class CodeEmailBoundary extends SocketBoundary {

    public void sendCode(SendCodeMailBean params) throws SendEmailException
    {
        String email = params.getRecipient() ;
        String code = params.getCode() ;

        String query = prepareQueryForServer(email, code) ;

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

    protected abstract String prepareQueryForServer(String email, String code) ;
}
