package com.example.athena.beans;

public class MailServerResponseBean implements SocketBean{
    private int code ;
    private String message ;
    private String details ;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
