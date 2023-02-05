package com.example.athena.dao;


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
    private static Connection dbConnection = null ;

    protected synchronized Connection getConnection() throws SQLException, IOException
    {
        if(dbConnection == null)
        {getCredentials();
            dbConnection = DriverManager.getConnection(dbUrl, user, pass);
        }
        return dbConnection ;
    }

    protected static void getCredentials() throws IOException {
         try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/dbConn"))) {
             user = reader.readLine().substring(9);
             pass = reader.readLine().substring(9);
             dbUrl = reader.readLine().substring(7);
         }
    }
}
