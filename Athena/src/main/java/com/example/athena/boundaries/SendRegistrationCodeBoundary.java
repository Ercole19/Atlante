package com.example.athena.boundaries;

import com.example.athena.exceptions.SendEmailException;

import java.io.IOException;

public final class SendRegistrationCodeBoundary extends CodeEmailBoundary
{
    private static SendRegistrationCodeBoundary instance = null ;

    private SendRegistrationCodeBoundary(){

    }

    public static synchronized SendRegistrationCodeBoundary getInstance() {
        if(instance == null) {
            instance = new SendRegistrationCodeBoundary() ;
        }
        return instance;
    }

    @Override
    protected String prepareQueryForServer(String email, String code) {

        return String.format("Mathena.services;%s;Confirm your registration;" +
                "Your confirmation code is: %s.\n" +
                "If you didn't request this code, please ignore the message.",email, code) ;
    }
}
