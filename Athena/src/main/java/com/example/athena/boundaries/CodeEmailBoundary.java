package com.example.athena.boundaries;

import com.example.athena.beans.MailServerBean;
import com.example.athena.beans.MailServerResponseBean;
import com.example.athena.exceptions.SendEmailException;

import java.io.IOException;

public abstract class CodeEmailBoundary extends SocketBoundary {

    public void sendCode(SendCodeMailBean params) throws SendEmailException
    {
        String email = params.getRecipient() ;
        String code = params.getCode() ;

        MailServerBean query = prepareQueryForServer(email, code) ;

        try
        {
            MailServerResponseBean retMessage = sendMessageGetResponse(query, 4545) ;
            if(!retMessage.getMessage().equals("OK"))
            {
                throw new SendEmailException(retMessage.getMessage()) ;
            }
        }catch (IOException e)
        {
            throw new SendEmailException("Error in connection to server: " + e.getMessage()) ;
        }
    }

    protected abstract MailServerBean prepareQueryForServer(String email, String code) ;
}
