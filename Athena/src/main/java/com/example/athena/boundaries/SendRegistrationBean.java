package com.example.athena.boundaries;

public class SendRegistrationBean
{
    private String email ;
    private String code ;

    public SendRegistrationBean(String email, String code)
    {
        this.setEmail(email) ;
        this.setCode(code) ;
    }

    public void setEmail(String email)
    {
        this.email = email ;
    }

    public void setCode(String code)
    {
        this.code = code ;
    }

    public String getCode()
    {
        return this.code ;
    }

    public String getEmail()
    {
        return this.email ;
    }
}
