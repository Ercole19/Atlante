package com.example.servers;

import java.util.Objects;
import java.util.Properties;

public class MailAuth {
    private final Properties properties = new Properties() ;
    private String username ;
    private String password ;

    private String authName ;

    public String getAuthName() {
        return this.authName ;
    }

    public void setAuthName(String authName) {
        this.authName = authName ;
    }

    public String getUsername() {
        return this.username ;
    }

    public String getPassword() {
        return this.password ;
    }

    public void setUsername(String username) {
        this.username = username ;
    }

    public void setPassword(String password) {
        this.password = password ;
    }

    public void setProperty(Object first, Object second) {
        if (Objects.equals(first, "mail.smtp.ssl.checkserveridentity"))
        {
            this.properties.put(first, Boolean.parseBoolean((String) second)) ;
        } else this.properties.put(first, second) ;
    }

    public Properties getProperties() {
        return this.properties ;
    }
}
