package com.example.athena.boundaries;

import com.example.athena.beans.MailServerBean;

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
    protected MailServerBean prepareQueryForServer(String email, String code) {

        MailServerBean bean = new MailServerBean() ;
        bean.setClassName("M");
        bean.setMailAccount("athena.services") ;
        bean.setRecipient(email);
        bean.setMailObject("Confirm your registration");
        bean.setContent(String.format(
                "Your confirmation code is: %s.\n" +
                "If you didn't request this code, please ignore the message.", code));
        return bean ;
    }
}
