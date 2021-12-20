package com.example.athena;

import java.sql.*;

public class studentDAO {

    private  String USER = "root";
    private  String PASS = "Salamandra230!";
    private  String DB_URL = "jdbc:mysql://localhost:3306/atena";
    private String query = "SELECT * FROM utenti WHERE email = ? and password = ?";

    public boolean findStudent (String emailUtente, String pass)  {

        PreparedStatement stmt = null;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = (PreparedStatement) connection.prepareStatement(query , ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_READ_ONLY);

            stmt.setString(1 , emailUtente);

            stmt.setString(2 , pass);

            ResultSet rs = stmt.executeQuery();

            if (rs.first()) {

                return true;

            }



        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }
    public void registerUser(String email , String password)  {
        //TO-DO

    }
}





