package com.example.athena.boundaries;

import com.example.athena.exceptions.SendEmailException;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SendReviewCodeEmailBoundary extends SocketBoundary
{
    private SendReviewCodeEmailBoundary(){

    }

    public static void sendReviewCode(ReviewViaMailBean review) throws SendEmailException
    {
        String email = review.getRecipient() ;
        String code = review.getCode() ;

        String query = String.format("T%s;%s;", email, code) ;

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
