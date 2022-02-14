package com.example.athena.entities;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends AbstractDAO {


    private final String insertImagesQuery= "insert into athena.book_images(email, isbn, image, image_name) values(?, ?, ?, ?)" ;
    private final String updateQuery = "UPDATE athena.books set title = ? , price = ? , negotiable = ? where email = ? and isbn = ?" ;
    private final String deleteImagesQuery = "delete from athena.book_images where isbn = ? and email = ? " ;
    private final String deleteBookQuery = "delete from athena.books where isbn = ? and email = ?" ;
    private final String getBookList = "SELECT title, isbn, price, negotiable from athena.books where email = ?";
    private final String getBookImage = "SELECT image from athena.book_images where email = ? and isbn  = ?" ;
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



    public void updateBookInfos(String title ,String isbn, Float price , boolean negotiability , List<File> images) {

        try (PreparedStatement statement = this.getConnection().prepareStatement(updateQuery) ) {

            deleteBookImages(isbn);


            for (File file : images) {
                insertImage(isbn, file);

            }



            statement.setString(1,title);
            statement.setFloat(2,price);
            statement.setBoolean(3,negotiability);
            statement.setString(4,email);
            statement.setString(5 ,isbn);

            statement.execute() ;





        } catch (SQLException  exc) {
            exc.getMessage() ;
        }
    }

    public void deleteBookImages(String isbn)
    {
        try(PreparedStatement statement = this.getConnection().prepareStatement(deleteImagesQuery))
        {
            statement.setString(1, isbn) ;
            statement.setString(2, email) ;

            statement.execute() ;
        }
        catch(SQLException e)
        {
            e.printStackTrace() ;
        }
    }

    public void deleteBook(String isbn)
    {
        try(PreparedStatement statement = this.getConnection().prepareStatement(deleteBookQuery))
        {
            statement.setString(1, isbn) ;
            statement.setString(2, email) ;


            statement.execute() ;
        }
        catch(SQLException e)
        {
            e.printStackTrace() ;
        }
    }


    public ObservableList<BookEntity>  getList () {
        ObservableList<BookEntity> list = FXCollections.observableArrayList() ;

        try(PreparedStatement statement = this.getConnection().prepareStatement(getBookList))
        {
            statement.setString(1, email) ;
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                String title = set.getString(1) ;
                String isbn = set.getString(2) ;
                Float price = set.getFloat(3) ;
                boolean negotiable = set.getBoolean(4) ;
                List<File> images = getBookImages(isbn);

                BookEntity book = new BookEntity(title, isbn, price, negotiable, images) ;

                list.add(book);


            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return list ;
    }

    private List<File> getBookImages(String isbn)
    {
        int i = 0 ;
        List<File> list = new ArrayList<>();
        try(PreparedStatement statement = this.getConnection().prepareStatement(getBookImage))
        {
            statement.setString(1,email) ;
            statement.setString(2, isbn) ;

            ResultSet set = statement.executeQuery() ;

            while(set.next())
            {
                byte[] image = set.getBlob(1).getBytes(1,(int) set.getBlob(1).length()) ;

                File file = new File("src/main/resources/book_images/tempImage" + i + ".png");
                OutputStream writeStream = new FileOutputStream(file);
                writeStream.write(image, 0, image.length);
                writeStream.close();

                list.add(file);
                i = i + 1 ;
            }

        }
        catch(SQLException | IOException e)
        {
            e.printStackTrace() ;
        }
        return list ;
    }


    public void deleteImage(String isbn, byte[] image) {
        try (PreparedStatement statement = this.getConnection().prepareStatement("delete from athena.book_images where isbn = ? and email = ? and image = ?")){
            Blob blob = this.getConnection().createBlob() ;
            blob.setBytes(1, image) ;
            statement.setString(1,isbn);
            statement.setString(2,email);
            statement.setBlob(3,blob);

            statement.execute() ;



        } catch (SQLException exc) {
            exc.getMessage() ;
        }
    }




public void insertImage(String isbn, File file) {

        try (PreparedStatement statement = this.getConnection().prepareStatement("insert into athena.book_images values (?,?,?,?)")){

            statement.setString(1,email);
            statement.setString(2,isbn);

            Blob blob = this.getConnection().createBlob();
            blob.setBytes( 1, Files.readAllBytes(file.toPath())) ;
            statement.setBlob(3,blob);
            statement.setString(4,file.getName());

            statement.execute();



        } catch (SQLException | IOException exception) {
            exception.getMessage();
        }

}






}
