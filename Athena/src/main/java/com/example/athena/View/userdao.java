package com.example.athena.View;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;

public class userdao {



    private String user = "test";
    private String Password = "test";
    private String dbUrl = "jdbc:mysql://78.13.194.135/athena";
    private String queryFind = " SELECT * FROM utenti WHERE  email = ? and password = ? ";
    private String queryRegister = " INSERT INTO athena.utenti (email, password, type , nome  ,surname ) VALUES (? , ? , ? , ? , ? )";
    private String getType = "SELECT type FROM utenti WHERE email = ?";
    private String filltutor = "SELECT  aboutme ,  sessioninfos  , contactnumbers  FROM athena.tutordescription WHERE emailuser = ? ";
    private String setTutor = "INSERT INTO `athena`.`tutordescription` (aboutme, sessioninfos, contactnumbers, emailuser) VALUES (? ,? ,?,?)";
    private String searchTutor = "select utenti.nome , utenti.surname , corsi.nomecorso , tutordescription.Average , utenti.email from athena.tutordescription join athena.corsi on tutordescription.emailuser = corsi.emailtutor join athena.utenti on tutordescription.emailuser = utenti.email where ? in (select nomecorso from athena.corsi) and corsi.nomecorso = ?; ";
    private String updatetutor = "UPDATE athena.tutordescription SET aboutme = ?,  sessioninfos=?, contactnumbers=?  WHERE emailuser= ?";
    private String searchByName = "SELECT  utenti.nome ,  utenti.surname , corsi.nomecorso , tutordescription.Average ,  utenti.email FROM athena.utenti join athena.tutordescription on utenti.email = tutordescription.emailuser join athena.corsi on utenti.email = corsi.emailtutor WHERE CONCAT( nome,  ' ', surname ) LIKE  concat ('%' , ? , '%')";
    private static String driver = "com.mysql.jdbc.Driver";


    public boolean findStudent(String emailUtente, String pass) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }

        try (Connection connection = DriverManager.getConnection(dbUrl, user, Password);
             PreparedStatement stmt = connection.prepareStatement(queryFind, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            stmt.setString(1, emailUtente);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();

            if (rs.first()) {

                return true;

            } else {
                return false;
            }


        } catch (SQLException e) {
            e.getMessage();
        }


        return false;
    }

    public Boolean registerUser(String email, String password, String type, String name, String surname) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }

        try (Connection connection = DriverManager.getConnection(dbUrl, user, Password);
             PreparedStatement stmt = connection.prepareStatement(queryRegister, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, type);
            stmt.setString(4, name);
            stmt.setString(5, surname);
            stmt.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Registration successfull", ButtonType.CLOSE);
            alert.showAndWait();
            return true;

        } catch (SQLException exception) {
            if (exception.getMessage().equals("Email not correct type another one")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Email not valid");
                alert.showAndWait();
            } else {
                System.out.println(exception.getMessage());
            }
        }
        return false;
    }


    public Object getuserType(String email) {

        try (Connection connection = DriverManager.getConnection(dbUrl, user, Password); PreparedStatement statement = connection.prepareStatement(getType)) {

            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                return set.getString(1);
            }


        } catch (SQLException exc) {
            exc.getMessage();
        }

        return null;


    }

    public String[] filltutorinfos() {
        String[] strArray1 = new String[3];
        try (Connection connection = DriverManager.getConnection(dbUrl, user, Password); PreparedStatement statement = connection.prepareStatement(filltutor)) {

            //declare with size
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                strArray1[0] = set.getString(1);
                strArray1[1] = set.getString(2);
                strArray1[2] = set.getString(3);
                System.out.println(strArray1);


            }

        } catch (SQLException exc) {
            exc.getMessage();
        }

        return strArray1;

    }


    public void settutorinfos(String about, String sesinf, String contnum) {
        try (Connection connection = DriverManager.getConnection(dbUrl, user, Password); PreparedStatement statement = connection.prepareStatement(setTutor, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setString(1, about);
            statement.setString(2, sesinf);
            statement.setString(3, contnum);

            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException exc) {
            exc.getMessage();
        }
    }

    public void updatetutorinfos(String about, String sesinf, String contnum) {
        try (Connection connection = DriverManager.getConnection(dbUrl, user, Password); PreparedStatement statement = connection.prepareStatement(updatetutor, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setString(1, about);
            statement.setString(2, sesinf);
            statement.setString(3, contnum);
            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException exc) {
            exc.getMessage();
        }

    }

    public String[] findTutorByCourse(String corso) {
        String[] tutorInfos = new String[500];
        int i = 0;
        try (Connection connection = DriverManager.getConnection(dbUrl, user, Password); PreparedStatement statement = connection.prepareStatement(searchTutor)) {
            statement.setString(1, corso);
            statement.setString(2, corso);
            //declare with size
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                tutorInfos[i] = set.getString(1);
                tutorInfos[i + 1] = set.getString(2);
                tutorInfos[i + 2] = set.getString(3);
                tutorInfos[i + 3] = String.valueOf(set.getFloat(4));
                tutorInfos[i + 4] = set.getString(5) ;
                i = i + 5 ;


            }

        } catch (SQLException exc) {
            exc.getMessage();
        }

        return tutorInfos;

    }

    public String[] findTutorByName(String nome) {
        String[] tutorInfos = new String[500];
        int i = 0;
        try (Connection connection = DriverManager.getConnection(dbUrl, user, Password); PreparedStatement statement = connection.prepareStatement(searchByName)) {
            statement.setString(1, nome);
            //declare with size
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                tutorInfos[i] = set.getString(1);
                tutorInfos[i + 1] = set.getString(2);
                tutorInfos[i + 2] = set.getString(3);
                tutorInfos[i + 3] = Float.toString(set.getFloat(4));
                tutorInfos[i + 4] = set.getString(5) ;
                i = i + 5 ;


            }

        } catch (SQLException exc) {
            exc.getMessage();
        }

        return tutorInfos;


    }
}





