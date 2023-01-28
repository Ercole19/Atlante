package com.example.athena.boundaries;

import com.example.athena.beans.MailServerBean;

public final class SendReviewCodeToStudentBoundary extends CodeEmailBoundary
{
    private static SendReviewCodeToStudentBoundary instance = null ;
    private SendReviewCodeToStudentBoundary(){

    }

    public static synchronized SendReviewCodeToStudentBoundary getInstance() {
        if(instance == null) {
            instance = new SendReviewCodeToStudentBoundary() ;
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
