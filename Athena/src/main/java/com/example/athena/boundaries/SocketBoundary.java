package com.example.athena.boundaries;

import com.example.athena.beans.MailServerResponseBean;
import com.example.athena.beans.SocketBean;
import com.example.athena.engineering_classes.SecureObjectInputStream;

import java.io.*;
import java.net.Socket;

public abstract class SocketBoundary
{
    protected static final byte[] buff = new byte[1024] ;

    protected SocketBoundary()
    {
        
    }

    protected static MailServerResponseBean sendMessageGetResponse(SocketBean bean, int port) throws IOException
    {
        try(Socket socket = new Socket("localhost", port) ; OutputStream out = socket.getOutputStream() ; InputStream in = socket.getInputStream())
        {
            ObjectOutputStream writeStream = new ObjectOutputStream(out) ;

            writeStream.writeObject(bean);

            ObjectInputStream readStream = new SecureObjectInputStream(in) ;

            return (MailServerResponseBean) readStream.readObject();

        } catch (ClassNotFoundException e) {
            MailServerResponseBean error = new MailServerResponseBean() ;
            error.setCode(4);
            error.setMessage("Bad response");
            error.setDetails("Error in response deserialization");

            return error ;
        }
    }

    protected static MailServerResponseBean getResponse(int port) throws IOException
    {
        try(Socket socket = new Socket("localhost", port) ; InputStream in = socket.getInputStream())
        {
            ObjectInputStream readStream = new SecureObjectInputStream(in) ;

            return (MailServerResponseBean) readStream.readObject() ;

        } catch (ClassNotFoundException e) {
            MailServerResponseBean error = new MailServerResponseBean() ;
            error.setCode(4);
            error.setMessage("Bad response");
            error.setDetails("Error in response deserialization");

            return error ;
        }
    }
}
