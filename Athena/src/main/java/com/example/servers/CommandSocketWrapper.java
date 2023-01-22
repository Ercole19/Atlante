package com.example.servers;

import com.example.athena.beans.normal.MailServerBean;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CommandSocketWrapper
{
    private ObjectOutputStream clientSocketOS ;

    private MailServerBean command ;

    public CommandSocketWrapper( ObjectOutputStream clientSocketOS, MailServerBean command)
    {
        setClientSocketOS(clientSocketOS) ;
        setCommand(command) ;
    }

    public ObjectOutputStream getClientSocketOS() {
        return clientSocketOS;
    }

    public void setClientSocketOS(ObjectOutputStream clientSocketOS) {
        this.clientSocketOS = clientSocketOS;
    }

    public void setCommand(MailServerBean command)
    {
        this.command = command ;
    }

    public MailServerBean getCommand()
    {
        return this.command ;
    }
}
