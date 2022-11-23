package com.example.athena.boundaries;

import com.example.athena.beans.normal.MailServerBean;
import com.example.athena.exceptions.SendEmailException;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public final class SendReviewCodeEmailBoundary extends CodeEmailBoundary
{
    private static SendReviewCodeEmailBoundary instance = null ;
    private SendReviewCodeEmailBoundary(){

    }

    public static synchronized SendReviewCodeEmailBoundary getInstance() {
        if(instance == null) {
            instance = new SendReviewCodeEmailBoundary() ;
        }

        return instance ;
    }

    @Override
    protected MailServerBean prepareQueryForServer(String email, String code) {

        MailServerBean bean = new MailServerBean() ;

        bean.setClassName("M");
        bean.setMailAccount("athena.services");
        bean.setRecipient(email) ;
        bean.setMailObject("A review code form a Tutor");
        bean.setContent(String.format(
            "A tutor has sent you a review code for a tutoring you had with him.\n" +
            "The code is %s.\n" +
            "If you didn't request this code, please ignore the message.", code));

        return bean ;
    }
}
