package com.example.servers;

import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class SessionInfo {
    private Session session ;
    private InternetAddress sender ;

    public SessionInfo(Session session, String senderName) throws AddressException {
        this.session = session ;
        this.sender = new InternetAddress(senderName) ;
    }

    public Session getSession(){
        return this.session ;
    }

    public InternetAddress getSender() {
        return sender ;
    }
}
