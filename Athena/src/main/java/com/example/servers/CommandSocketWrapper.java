package com.example.servers;

import com.example.athena.beans.normal.MailServerBean;

import java.net.Socket;

public class CommandSocketWrapper
{
    private Socket clientSocket ;
    private MailServerBean command ;

    public CommandSocketWrapper(Socket clientSocket, MailServerBean command)
    {
        setClientSocket(clientSocket) ;
        setCommand(command) ;
    }

    public void setClientSocket(Socket clientSocket)
    {
        this.clientSocket = clientSocket ;
    }

    public void setCommand(MailServerBean command)
    {
        this.command = command ;
    }

    public Socket getClientSocket()
    {
        return this.clientSocket ;
    }

    public MailServerBean getCommand()
    {
        return this.command ;
    }
}
