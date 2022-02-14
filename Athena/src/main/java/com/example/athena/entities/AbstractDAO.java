package com.example.athena.entities;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.jcraft.jsch.*;

public abstract class AbstractDAO
{
    private static  String user ;
    private static  String pass ;
    private static  String dbUrl ;
    private static  String dbAddress ;
    private static  int assigned_port ;
    private static  String username ;
    private static  String password ;


    private static Connection dbConnection = null ;
    private static Session forwardingConnection = null ;

    private synchronized void prepareSession() throws JSchException
    {
        if(forwardingConnection == null)
        {
            JSch jsch = new JSch() ;
            forwardingConnection = jsch.getSession(username, dbAddress, 3400) ;
            int lPort = 3336 ;
            int rPort = 3306 ;
            String rHost = "localhost" ;
            forwardingConnection.setConfig("StrictHostKeyChecking", "no") ;
            forwardingConnection.setPassword(password) ;
            forwardingConnection.connect() ;

            assigned_port = forwardingConnection.setPortForwardingL(lPort, rHost, rPort) ;
            dbUrl = dbUrl.substring(0, dbUrl.indexOf(';')) + ":" + assigned_port + dbUrl.substring(dbUrl.indexOf(';') +1) ;
        }


    }

    protected synchronized Connection getConnection() throws SQLException
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


    protected static void getCredentials() {
        try(BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/dbConn")) ) {

        user = reader.readLine().substring(9);
        pass = reader.readLine().substring(9);
        dbUrl = reader.readLine().substring(8);
        dbAddress = reader.readLine().substring(7) ;
        username = reader.readLine().substring(11) ;
        password = reader.readLine().substring(11) ;

    }catch ( IOException exc ) {
            exc.getMessage() ;
        }
    }
}
