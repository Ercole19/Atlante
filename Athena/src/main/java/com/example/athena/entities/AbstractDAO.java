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

    private static Connection dbConnection = null ;

    protected synchronized Connection getConnection() throws SQLException
    {
        if(dbConnection == null)
        {
            getCredentials();
            dbConnection = DriverManager.getConnection(dbUrl, user, pass);
        }

        return dbConnection ;
    }


    protected static void getCredentials() {
        try(BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/dbConn")) ) {

        user = reader.readLine().substring(9);
        pass = reader.readLine().substring(9);
        dbUrl = reader.readLine().substring(7);

    }catch ( IOException exc ) {
            exc.getMessage() ;
        }
    }
}
