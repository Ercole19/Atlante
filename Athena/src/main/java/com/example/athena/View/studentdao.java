package com.example.athena.View;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;

public class studentdao {

    private String user = "test" ;
    private String Password = "test" ;
    private String dbUrl = "jdbc:mysql://127.0.0.1:3306/athena" ;
    private String queryFind = " SELECT * FROM studenti WHERE  email = ? and password = ? " ;
    private String queryRegister = " INSERT INTO  studenti values (? , ?) " ;
    private static String driver  = "com.mysql.jdbc.Driver" ;

    public boolean findStudent (String emailUtente, String pass)  {
        try {
            Class.forName(driver) ;
        } catch (ClassNotFoundException e ) {
            e.getMessage() ;
         }

        try ( Connection connection = DriverManager.getConnection(dbUrl, user , Password );
             PreparedStatement stmt = connection.prepareStatement(queryFind , ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_READ_ONLY)){

            stmt.setString(1 , emailUtente);
            stmt.setString(2 , pass);
            ResultSet rs = stmt.executeQuery();

            if (rs.first()) {

                return true;

            }
            else {
                return false ;
            }



        } catch (SQLException e) {
            e.getMessage();
        }


        return false;
    }
    public void registerUser(String email , String password)  {
        try {
            Class.forName(driver) ;
        } catch (ClassNotFoundException e ) {
            e.getMessage() ;
        }

        try (Connection connection = DriverManager.getConnection(dbUrl, user, Password );
             PreparedStatement stmt = connection.prepareStatement(queryRegister, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);){
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





