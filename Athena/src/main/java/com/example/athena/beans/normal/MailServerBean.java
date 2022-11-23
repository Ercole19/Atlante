package com.example.athena.beans.normal;

public class MailServerBean implements SocketBean {

    private String className ;
    private String mailAccount ;
    private String recipient ;

    private String mailObject ;
    private String content ;
    private String sendMoment ;

    public String getSendMoment() {
        return sendMoment;
    }

    public String getClassName() {
        return className;
    }

    public String getMailAccount() {
        return mailAccount;
    }

    public String getContent() {
        return content;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMailObject() {
        return mailObject;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMailAccount(String mailAccount) {
        this.mailAccount = mailAccount;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setSendMoment(String sendMoment) {
        this.sendMoment = sendMoment;
    }

    public void setMailObject(String mailObject) {
        this.mailObject = mailObject;
    }
}
