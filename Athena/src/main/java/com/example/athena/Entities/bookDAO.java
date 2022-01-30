package com.example.athena.Entities;

import java.sql.*;

public class bookDAO extends AbstractDAO {

    private final String email = com.example.athena.Entities.user.getUser().getEmail();


    public void insertBook(String title , String isbn , Float price , boolean negotiability) {
        String insertQuery = "insert into athena.books values (?,?,?,?,?)";
        try (PreparedStatement statement = this.getConnection().prepareStatement(insertQuery)){

             statement.setString(1, title);
             statement.setString(2, isbn);
             statement.setFloat(3, price);
             statement.setBoolean(4, negotiability);
             statement.setString(5 ,email);
             statement.execute() ;

             }
         catch (SQLException exc) {
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


}
