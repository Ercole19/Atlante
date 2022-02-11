package com.example.athena.entities;

import javafx.scene.image.Image;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends AbstractDAO {


    private final String email = User.getUser().getEmail();


    public void insertBook(String title , String isbn , Float price , boolean negotiability, List<File> images) {
        String insertQuery = "insert into athena.books values (?,?,?,?,?)";
        try (PreparedStatement statement = this.getConnection().prepareStatement(insertQuery) ; PreparedStatement statement2 = this.getConnection().prepareStatement(insertImagesQuery)) {

            statement.setString(1, title);
            statement.setString(2, isbn);
            statement.setFloat(3, price);
            statement.setBoolean(4, negotiability);
            statement.setString(5 ,email);
            statement.execute() ;

            statement2.setString(1,email);
            statement2.setString(2,isbn);


            for (File file : images) {
                statement2.setBlob(3, new BufferedInputStream(new FileInputStream(file)));
                statement2.setString(4,file.getName());
                statement2.execute();
            }





             }
         catch (SQLException | FileNotFoundException exc) {
                exc.getMessage() ;
         }

    }


    public String[] searchBook(String titolo) {
        String[] bookinfos = new String[500];
        int i = 0;
        String search = "select title , price , negotiable ,  email , isbn from athena.books where title = ?";
        try (PreparedStatement statement = this.getConnection().prepareStatement(search)) {
            statement.setString(1, titolo);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                bookinfos[i] = set.getString(1);
                bookinfos[i + 1] = set.getString(2);
                bookinfos[i + 2] = set.getString(3);
                bookinfos[i + 3] = set.getString(4) ;
                bookinfos[i + 4] = set.getString(5) ;
                i = i + 5 ;
            }

        } catch (SQLException exc) {
            exc.getMessage();
        }

        return bookinfos;

    }

    public Image getImage() {

        try (PreparedStatement statement = this.getConnection().prepareStatement("Select image from athena.books")) {

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                byte[] imageBytes = set.getBlob(1).getBytes(1, (int) set.getBlob(1).length());
                File file = new File("src/main/resources/assets/temp.jpg") ;
                OutputStream writeStream = new FileOutputStream(file) ;
                writeStream.write(imageBytes, 0, imageBytes.length);
                writeStream.close() ;
            }

        } catch (SQLException |  IOException exc){
            exc.getMessage();
        }
        return new Image(new File("src/main/resources/assets/temp.jpg").toURI().toString());
    }

}
