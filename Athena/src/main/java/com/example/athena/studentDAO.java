package com.example.athena;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;

public class studentDAO {

    private  String USER = "root";
    private  String PASS = "Salamandra230!";
    private  String DB_URL = "jdbc:mysql://localhost:3306/atena";
    private String queryFind = "SELECT * FROM utenti WHERE email = ? and password = ?";
    private String queryRegister = "INSERT INTO  utenti values (? , ?)" ;

    public boolean findStudent (String emailUtente, String pass)  {

        PreparedStatement stmt = null;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = (PreparedStatement) connection.prepareStatement(queryFind , ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_READ_ONLY);

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


        PreparedStatement stmt = null;
        Connection connection = null;

        try {

                connection = DriverManager.getConnection(DB_URL, USER, PASS);

                stmt = connection.prepareStatement(queryRegister, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                stmt.setString(1, email);

                stmt.setString(2, password);

                stmt.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Registration successfull", ButtonType.CLOSE);
                alert.showAndWait();













        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}





