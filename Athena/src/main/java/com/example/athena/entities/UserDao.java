package com.example.athena.entities;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDAO {


    private String queryFind = " SELECT * FROM utenti WHERE  email = ? and password = ? ";
    private String queryRegister = " INSERT INTO athena.utenti (email, password, type , nome  ,surname ) VALUES (? , ? , ? , ? , ? )";
    private String getType = "SELECT type FROM utenti WHERE email = ?";
    private String filltutor = "SELECT  aboutme ,  sessioninfos  , contactnumbers  FROM athena.tutordescription WHERE emailuser = ? ";
    private String setTutor = "INSERT INTO `athena`.`tutordescription` (aboutme, sessioninfos, contactnumbers, emailuser) VALUES (? ,? ,?,?)";
    private String searchTutor = "select utenti.nome , utenti.surname , corsi.nomecorso , tutordescription.Average , utenti.email from athena.tutordescription join athena.corsi on tutordescription.emailuser = corsi.emailtutor join athena.utenti on tutordescription.emailuser = utenti.email where  nomecorso like concat('%' , ? , '%') ";
    private String updatetutor = "UPDATE athena.tutordescription SET aboutme = ?,  sessioninfos=?, contactnumbers=?  WHERE emailuser= ?";
    private String searchByName = "SELECT  utenti.nome ,  utenti.surname , corsi.nomecorso , tutordescription.Average ,  utenti.email FROM athena.utenti join athena.tutordescription on utenti.email = tutordescription.emailuser join athena.corsi on utenti.email = corsi.emailtutor WHERE CONCAT( nome,  ' ', surname ) LIKE  concat ('%' , ? , '%')";
    private String insertCV ="update athena.tutordescription  set CV = ?    where emailuser = ?" ;


    public boolean findStudent(String emailUtente, String pass) {


        try (PreparedStatement stmt = this.getConnection().prepareStatement(queryFind, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            stmt.setString(1, emailUtente);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();

            return rs.first();


        } catch (SQLException e) {
            e.getMessage();
        }


        return false;
    }

    public Boolean registerUser(String email, String password, String type, String name, String surname) {


        try (PreparedStatement stmt = this.getConnection().prepareStatement(queryRegister, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
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
                exception.getMessage();
            }
        }
        return false;
    }


    public Object getuserType(String email) {

        try (PreparedStatement statement = this.getConnection().prepareStatement(getType)) {

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

    public List<String> filltutorinfos(String email) {
        List<String> infos = new ArrayList<>();
        try (PreparedStatement statement = this.getConnection().prepareStatement(filltutor)) {


            statement.setString(1, email);


            //declare with size
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                infos.add(set.getString(1));
                infos.add(set.getString(2));
                infos.add(set.getString(3));
            }
        } catch (SQLException exc) {
            exc.getMessage();
        }

        return infos;

    }


    public void settutorinfos(String about, String sesinf, String contnum) {
        try (PreparedStatement statement = this.getConnection().prepareStatement(setTutor, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setString(1, about);
            statement.setString(2, sesinf);
            statement.setString(3, contnum);


            statement.executeUpdate();
        } catch (SQLException exc) {
            exc.getMessage();
        }
    }

    public void updatetutorinfos(String about, String sesinf, String contnum) {
        try (PreparedStatement statement = this.getConnection().prepareStatement(updatetutor, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setString(1, about);
            statement.setString(2, sesinf);
            statement.setString(3, contnum);
            statement.setString(4, User.getUser().getEmail());

            statement.executeUpdate();
        } catch (SQLException exc) {
            exc.getMessage();
        }

    }


    public String[] findTutor(String query, boolean byname) {
        String prepStatement;

        if (byname) {
            prepStatement = searchByName;
        } else {
            prepStatement = searchTutor;
        }

        String[] tutorInfos = new String[500];
        int i = 0;
        try (PreparedStatement statement = this.getConnection().prepareStatement(prepStatement)) {

            statement.setString(1, query);

            //declare with size
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                tutorInfos[i] = set.getString(1);
                tutorInfos[i + 1] = set.getString(2);
                tutorInfos[i + 2] = set.getString(3);
                tutorInfos[i + 3] = Float.toString(set.getFloat(4));
                tutorInfos[i + 4] = set.getString(5);
                i = i + 5;


            }

        } catch (SQLException exc) {
            exc.getMessage();
        }

        return tutorInfos;


    }


    public String[] getName(String email) {

        String[] infos = new String[2];
        try (PreparedStatement statement = this.getConnection().prepareStatement("select nome,surname from athena.utenti where email = ? ")) {

            statement.setString(1,email);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                infos[0] = set.getString(1);
                infos[1] = set.getString(2);
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return infos;
    }


    public float getAvg(String email) {
        float avg = 0;

        try (PreparedStatement statement = this.getConnection().prepareStatement("select average from athena.tutordescription where emailuser =?")){

            statement.setString(1,email);
            ResultSet set = statement.executeQuery();

            while (set.next()){
                avg = set.getFloat(1);
            }


        } catch (SQLException e) {
            e.getMessage();
        }
        return avg;

    }


    public void inserisciCV(File cv) {
        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(insertCV) ) {
            preparedStatement.setBlob(1, new BufferedInputStream(new FileInputStream(cv))) ;
            preparedStatement.setString(2 , com.example.athena.entities.User.getUser().getEmail());
            preparedStatement.execute();


        } catch (SQLException | FileNotFoundException exc) {
            System.out.println(exc.getMessage());
        }
    }


    public void getCV(String email) {
        try(PreparedStatement statement = this.getConnection().prepareStatement("Select CV from athena.tutordescription where emailuser = ? ")) {

            statement.setString(1, email);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                byte[] cvBytes = set.getBlob(1).getBytes(1, (int) set.getBlob(1).length());
                File file = new File("src/main/resources/tutor_cv/tempCV.html");
                OutputStream writeStream = new FileOutputStream(file);
                writeStream.write(cvBytes, 0, cvBytes.length);
                writeStream.close();
            }

        }catch (SQLException  | IOException exc) {
            exc.getMessage() ;
        }
    }
}





