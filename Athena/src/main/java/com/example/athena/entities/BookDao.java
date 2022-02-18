package com.example.athena.entities;


import com.example.athena.exceptions.BookException;
import com.example.athena.graphical_controller.BookEntityBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class BookDao extends AbstractDAO {


    private final String insertImagesQuery= "INSERT INTO `athena`.`book_images` (email, isbn, image,image_name, count_image) VALUES (?,?,?,?,?)" ;
    private final String updateQuery = "UPDATE athena.books set title_book = ? , price = ? , negotiable = ? where email_user = ? and isbn_book = ?" ;
    private final String deleteImagesQuery = "delete from athena.book_images where isbn = ? and email = ? " ;
    private final String deleteBookQuery = "delete from athena.books where isbn_book = ? and email_user = ?" ;
    private final String getBookList = "SELECT title_book, isbn_book, price, negotiable from athena.books where email_user = ?";
    private final String getBookImage = "SELECT image from athena.book_images where email = ? and isbn  = ? order by count_image" ;
    private final String bookInfosQuery = "SELECT title_book, price, negotiable from athena.books where email_user = ? and isbn_book = ? ";
    private final String findBooks = "SELECT title_book, isbn_book, price, email_user, image " +
                                            "from athena.books join athena.book_images on books.email_user = book_images.email and books.isbn_book = book_images.isbn " +
                                                   "where (title_book = ? or isbn_book = ?) and count_image = 1 and email_user != ?";
    private final String email = User.getUser().getEmail();
    private int i = 0 ;


    public void insertBook(String title , String isbn , Float price , boolean negotiability, List<File> images, int i) {
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
                statement2.setInt(5,i);
                statement2.execute();
                i++;
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
            int i = 1;
            deleteBookImages(isbn);


            for (File file : images) {
                insertImage(isbn, file,i);
                i++;

            }

            statement.setString(1,title);
            statement.setFloat(2,price);
            statement.setBoolean(3,negotiability);
            statement.setString(4,email);
            statement.setString(5 ,isbn);

            statement.execute() ;





        } catch (SQLException  exc) {
            exc.printStackTrace(); ;
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
            deleteBookImages(isbn);

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
                List<File> images = getBookImages(isbn, email);

                BookEntity book = new BookEntity(title, isbn, price, negotiable, images, email) ;

                list.add(book);


            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return list ;
    }

    private List<File> getBookImages(String isbn, String email)
    {

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
            exc.printStackTrace(); ;
        }
    }




public void insertImage(String isbn, File file, int i) {

        try (PreparedStatement statement = this.getConnection().prepareStatement("insert into athena.book_images values (?,?,?,?,?)")){

            statement.setString(1,email);
            statement.setString(2,isbn);

            Blob blob = this.getConnection().createBlob();
            blob.setBytes( 1, Files.readAllBytes(file.toPath())) ;
            statement.setBlob(3,blob);
            statement.setString(4,file.getName());
            statement.setInt(5,i);

            statement.execute();



        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }

}

public List<BookEntity> findBooks(String book) {

        List<BookEntity> books = new ArrayList<>() ;
        int i = 0;

        try (PreparedStatement statement = this.getConnection().prepareStatement(findBooks)){
            statement.setString(1, book);
            statement.setString(2, book);
            statement.setString(3, email);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                byte[] image = set.getBlob(5).getBytes(1,(int) set.getBlob(5).length()) ;

                File file = new File("image.png");
                OutputStream writeStream = new FileOutputStream(file);
                writeStream.write(image, 0, image.length);
                writeStream.close();
                BookEntity bookEntity = new BookEntity(set.getString(1), set.getString(2), set.getFloat(3), set.getString(4), file);
                books.add(bookEntity);
            }

        } catch (SQLException | IOException exc) {
            exc.printStackTrace();
        }
        return books;


    }
    public BookEntity getBookInformations(String email, String isbn) {
        BookEntity book = null ;
        try (PreparedStatement statement = this.getConnection().prepareStatement(bookInfosQuery)){

            statement.setString(1, email);
            statement.setString(2, isbn);

            List<File> images = getBookImages(isbn, email);

            ResultSet set = statement.executeQuery() ;
            while (set.next()) {

                book = new BookEntity(set.getString(1), isbn, set.getFloat(2), set.getBoolean(3),images,email);

            }
        } catch (SQLException exc){
            exc.printStackTrace();
        }
        return book ;
    }

    public void finalizePurchase(String title, String isbn, Float price, String emailBuyer, String emailVendor)
    {
        try(PreparedStatement statement = this.getConnection().prepareStatement(finalizeQuery)) {

            statement.setString(1, title);
            statement.setString(2, isbn);
            statement.setFloat(3, price);
            statement.setString(4, emailBuyer);
            statement.setString(5, emailVendor);

            statement.execute();

        }catch (SQLException exc) {
            exc.printStackTrace();
        }
    }




}
