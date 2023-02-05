package com.example.athena.dao;


import com.example.athena.entities.BookEntity;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.FindException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookDao extends AbstractDAO {
    private final String email = LoggedStudent.getInstance().getEmail().getMail();
    private int i = 1 ;

    public void insertBook(String title , String isbn , String price , boolean negotiability, List<File> images, Timestamp timestamp) throws BookException
    {
        try (PreparedStatement statement = this.getConnection().prepareStatement("insert into athena.books values (?,?,?,?,?,?)") ; PreparedStatement statement2 = this.getConnection().prepareStatement("INSERT INTO `athena`.`book_images` (email, isbn, image,image_name, count_image, bookSaleTimestamp ) VALUES (?,?,?,?,?,?)")) {

            statement.setString(1, title);
            statement.setString(2, isbn);
            statement.setString(3, price);
            statement.setBoolean(4, negotiability);
            statement.setString(5 ,email);
            statement.setTimestamp(6, timestamp);
            statement.execute() ;

            statement2.setString(1,email);
            statement2.setString(2,isbn);
            statement2.setTimestamp(6, timestamp);

            for (File file : images) {
                statement2.setBlob(3, new BufferedInputStream(new FileInputStream(file)));
                statement2.setString(4, file.getName());
                statement2.setInt(5, i);
                statement2.execute();
                i++;
            }
        }
         catch (SQLException | IOException exc) {
            throw new BookException(exc.getMessage()) ;
         }

    }

    private void deleteBookImages(String isbn) throws SQLException, IOException
    {
        try(PreparedStatement statement = this.getConnection().prepareStatement("delete from athena.book_images where isbn = ? and email = ? ")) {
            statement.setString(1, isbn) ;
            statement.setString(2, email) ;
            statement.execute() ;
        }

    }

    public void deleteBook(String isbn, String timestamp) throws BookException
    {
        try(PreparedStatement statement = this.getConnection().prepareStatement("delete from athena.books where isbn_book = ? and email_user = ? and saleTimestamp = ?"))
        {
            deleteBookImages(isbn);
            statement.setString(1, isbn) ;
            statement.setString(2, email) ;
            statement.setTimestamp(3, Timestamp.valueOf(timestamp));
            statement.execute() ;
        }
        catch(SQLException | IOException e)
        {
             throw new BookException(e.getMessage()) ;
        }
    }

    public ObservableList<BookEntity> getList() throws BookException {
        ObservableList<BookEntity> list = FXCollections.observableArrayList() ;
        try(PreparedStatement statement = this.getConnection().prepareStatement("SELECT title_book, isbn_book, price, negotiable, saleTimestamp from athena.books where email_user = ?"))
        {
            statement.setString(1, email) ;
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                String title = set.getString(1) ;
                String isbn = set.getString(2) ;
                String price = set.getString(3);
                boolean negotiable = set.getBoolean(4) ;
                String timestamp = set.getTimestamp(5).toString();
                List<File> images = getPersonalBookImages(isbn, email, timestamp, true);

                BookEntity book = new BookEntity(title, isbn, price, negotiable, images, email, timestamp);

                list.add(book);
            }
        }
        catch (SQLException | IOException  e)
        {
            throw new BookException(e.getMessage()) ;
        }
        return list ;
    }

    private List<File> getPersonalBookImages(String isbn, String email, String timestamp, boolean personal) throws SQLException, IOException
    {
        List<File> list = new ArrayList<>();
        String preparedStatement;
        if (personal) preparedStatement = "SELECT image from athena.book_images where email = ? and isbn  = ? and bookSaleTimestamp = ?  order by count_image";
        else preparedStatement = "SELECT image from athena.book_images where email != ? and isbn  = ? and bookSaleTimestamp = ?  order by count_image";
        try (PreparedStatement statement = this.getConnection().prepareStatement(preparedStatement)) {

            statement.setString(1,email) ;
            statement.setString(2, isbn) ;
            statement.setTimestamp(3, Timestamp.valueOf(timestamp));
            ResultSet set = statement.executeQuery() ;
            while(set.next())
            {
                byte[] image = set.getBlob(1).getBytes(1,(int) set.getBlob(1).length()) ;
                String pathname = "src/main/resources/book_images/tempImage" + i + ".png" ;
                list.add(writeImage(image, pathname));
                i = i + 1 ;
            }
            return list ;
        }
    }

    private File writeImage(byte[] image, String pathname) throws IOException
    {
        File file = new File(pathname);
        file.deleteOnExit() ;
        try(OutputStream writeStream = new FileOutputStream(file))
        {
            writeStream.write(image, 0, image.length);
        }
        return file ;
    }

    public List<BookEntity> findBooksWImages(String book) throws FindException{

        List<BookEntity> books = new ArrayList<>() ;

        try (PreparedStatement statement = this.getConnection().prepareStatement("SELECT title_book, isbn_book, price, email_user, image, negotiable, saleTimestamp from athena.books  left join athena.book_images on books.email_user = book_images.email and books.isbn_book = book_images.isbn  and books.saleTimestamp = bookSaleTimestamp where (title_book = ? or isbn_book = ?)  and email_user != ?   and (count_image = 1 or count_image is null)")){
            statement.setString(1, book);
            statement.setString(2, book);
            statement.setString(3, email);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                List<File> booksFiles = getPersonalBookImages(set.getString(2), email, String.valueOf(set.getTimestamp(7)),false);
                BookEntity bookEntity = new BookEntity(set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getBoolean(6), booksFiles, String.valueOf(set.getTimestamp(7)));
                books.add(bookEntity);
            }

        } catch (SQLException | IOException exc) {
           throw new FindException(exc.getMessage());
        }
        return books;
    }

    public boolean getNotificationsFromDb() throws BookException {
        try (CallableStatement call = this.getConnection().prepareCall("call athena.getNots(?,?) ")) {
            call.setString(1, LoggedStudent.getInstance().getEmail().getMail());
            call.registerOutParameter(2, Types.BOOLEAN);
            call.executeUpdate();
            return call.getBoolean(2);
        }
        catch (SQLException | IOException e) {
            throw new BookException(e.getMessage());
        }
    }
}