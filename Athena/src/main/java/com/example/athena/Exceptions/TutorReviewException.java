package com.example.athena.Exceptions;

public class TutorReviewException extends Exception
{
    public TutorReviewException(String message)
    {
        super("this was the original message"+message) ;
    }

    public TutorReviewException(Throwable cause)
    {
        super(cause) ;
    }

    public TutorReviewException(String message, Throwable cause)
    {
        super("+++ " + message + " +++", cause) ;
    }
}
