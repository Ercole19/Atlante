package com.example.servers;

import java.net.Socket;

public class CommandSocketWrapper
{
    private Socket clientSocket ;
    private String command ;

    public CommandSocketWrapper(Socket clientSocket, String command)
    {
        setClientSocket(clientSocket) ;
        setCommand(command) ;
    }

    public void setClientSocket(Socket clientSocket)
    {
        this.clientSocket = clientSocket ;
    }

    public void setCommand(String command)
    {
        this.command = command ;
    }

    public Socket getClientSocket()
    {
        return this.clientSocket ;
    }

    public String getCommand()
    {
        return this.command ;
    }
}
