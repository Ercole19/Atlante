package com.example.athena.boundaries;

public class ReviewViaMailBean
{
    private String recipient ;
    private String code ;

    public ReviewViaMailBean(String recipient, String code)
    {
        setRecipient(recipient) ;
        setCode(code) ;
    }

    public void setRecipient(String recipient)
    {
        this.recipient = recipient ;
    }

    public void setCode(String code)
    {
        this.code = code ;
    }

    public String getRecipient()
    {
        return this.recipient ;
    }

    public String getCode()
    {
        return this.code ;
    }
}
