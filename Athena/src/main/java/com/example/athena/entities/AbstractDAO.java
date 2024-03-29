package com.example.athena.entities;

import com.example.athena.exceptions.UserInfoException;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public abstract class AbstractDAO
{
    private static  String user ;
    private static  String pass ;
    private static  String dbUrl ;
    private static  String dbAddress ;
    private static  int sshPort ;
    private static  String username ;
    private static  String password ;


    private static Connection dbConnection = null ;
    private static Session forwardingConnection = null ;

    private synchronized void prepareSession() throws JSchException
    {
        if(forwardingConnection == null)
        {
            JSch jsch = new JSch() ;
            forwardingConnection = jsch.getSession(username, dbAddress, sshPort) ;
            int lPort = 3336 ;
            int rPort = 3306 ;
            String rHost = "localhost" ;
            forwardingConnection.setConfig("StrictHostKeyChecking", "no") ;
            forwardingConnection.setPassword(password) ;
            forwardingConnection.connect() ;

            int assignedPort = forwardingConnection.setPortForwardingL(lPort, rHost, rPort);
            dbUrl = dbUrl.substring(0, dbUrl.indexOf(';')) + ":" + assignedPort + dbUrl.substring(dbUrl.indexOf(';') +1) ;
        }


    }

    protected synchronized Connection getConnection() throws SQLException, IOException
    {
        if(dbConnection == null)
        {
            getCredentials();
            try {
                this.prepareSession();
            }catch(JSchException e)
            {
                throw new SQLException(e.getMessage()) ;
            }
            dbConnection = DriverManager.getConnection(dbUrl, user, pass);
        }

        return dbConnection ;
    }

    protected static void getCredentials() throws IOException {
         try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/dbConn"))) {
             user = reader.readLine().substring(9);
             pass = reader.readLine().substring(9);
             dbUrl = reader.readLine().substring(7);
             dbAddress = reader.readLine().substring(10);
             sshPort = Integer.parseInt(reader.readLine().substring(10));
             username = reader.readLine().substring(11);
             password = reader.readLine().substring(11) ;
         }
    }
}
