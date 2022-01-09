package com.example.athena.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDAO
{
    private static final String user = "test" ;
    private static final String pass = "test" ;
    private static final String dbUrl = "jdbc:mysql://78.13.228.115:3306/athena" ;

    private static Connection dbConnection = null ;

    protected synchronized Connection getConnection() throws SQLException
    {
        if(dbConnection == null)
        {
            dbConnection = DriverManager.getConnection(dbUrl, user, pass);
        }

        return dbConnection ;
    }
}
