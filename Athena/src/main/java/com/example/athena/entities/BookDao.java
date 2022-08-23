package com.example.athena.entities;


import com.example.athena.exceptions.FindException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends AbstractDAO {
    private final String email = Student.getInstance().getEmail();
    private int i = 1 ;
    private static int imageCount = 0;

    private static synchronized void incrementImageCounter() {
        imageCount++ ;
    }

    public void insertBook(String title , String isbn , Float price , boolean negotiability, List<File> images)
    {
        try (PreparedStatement statement = this.getConnection().prepareStatement("insert into athena.books values (?,?,?,?,?)") ; PreparedStatement statement2 = this.getConnection().prepareStatement("INSERT INTO `athena`.`book_images` (email, isbn, image,image_name, count_image) VALUES (?,?,?,?,?)")) {

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
                statement2.setString(4, file.getName());
                statement2.setInt(5, i);
                statement2.execute();
                i++;
            }
        }
         catch (SQLException | FileNotFoundException exc) {
                exc.getMessage() ;
         }

    }

    private void deleteBookImages(String isbn)
    {
        try(PreparedStatement statement = this.getConnection().prepareStatement("delete from athena.book_images where isbn = ? and email = ? "))
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
        try(PreparedStatement statement = this.getConnection().prepareStatement("delete from athena.books where isbn_book = ? and email_user = ?"))
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

        try(PreparedStatement statement = this.getConnection().prepareStatement("SELECT title_book, isbn_book, price, negotiable from athena.books where email_user = ?"))
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
        try(PreparedStatement statement = this.getConnection().prepareStatement("SELECT image from athena.book_images where email = ? and isbn  = ? order by count_image"))
        {
            statement.setString(1,email) ;
            statement.setString(2, isbn) ;

            ResultSet set = statement.executeQuery() ;

            while(set.next())
            {
                byte[] image = set.getBlob(1).getBytes(1,(int) set.getBlob(1).length()) ;
                String pathname = "src/main/resources/book_images/tempImage" + i + ".png" ;
                list.add(writeImage(image, pathname));
                i = i + 1 ;
            }
        }
        catch(SQLException | IOException e)
        {
            e.printStackTrace() ;
        }
        return list ;
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

    public List<BookEntity> findBooksWImages(String book) throws FindException {

        List<BookEntity> books = new ArrayList<>() ;

        try (PreparedStatement statement = this.getConnection().prepareStatement("SELECT title_book, isbn_book, price, email_user, image, negotiable from athena.books  left join athena.book_images on books.email_user = book_images.email and books.isbn_book = book_images.isbn where (title_book = ? or isbn_book = ?)  and email_user != ? and (count_image = 1 or count_image is null)")){
            statement.setString(1, book);
            statement.setString(2, book);
            statement.setString(3, email);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                List<File> booksFiles = new ArrayList<>();
                if (set.getBlob(5) != null && set.getBlob(5).length() != 0) {
                    byte[] image = set.getBlob(5).getBytes(1,(int) set.getBlob(5).length()) ;
                    String pathname = "image" + imageCount + ".png" ;
                    booksFiles.add(writeImage(image, pathname));
                    BookDao.incrementImageCounter();
                }
                BookEntity bookEntity = new BookEntity(set.getString(1), set.getString(2), set.getFloat(3), set.getString(4), set.getBoolean(6), booksFiles);
                books.add(bookEntity);
            }

        } catch (SQLException | IOException exc) {
           throw new FindException(exc.getMessage());
        }
        return books;
    }

    public void finalizePurchase(String title, String isbn, Float price, String emailBuyer, String emailVendor)
    {
        try(PreparedStatement statement = this.getConnection().prepareStatement("CALL finalizePurchase(?,?,?,?,?)")) {

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

    public List<BookEntity> getBookResults(String email) {
        List<BookEntity> books = new ArrayList<>();
        try(PreparedStatement statement = this.getConnection().prepareStatement("SELECT vendorEmail, bookTitle, bookIsbn, bookPrice from athena.recent_purchases where purchaserEmail = ?")) {

            statement.setString(1, email);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                BookEntity book = new BookEntity(set.getString(1), set.getString(2), set.getString(3), set.getFloat(4));
                books.add(book);
            }

        }catch (SQLException exc) {
            exc.printStackTrace();
        }
        return books;
    }


    public void daoReportSeller(String buyer, String seller) {
        try(PreparedStatement statement = this.getConnection().prepareStatement("call athena.reportSeller(?,?)")) {

            statement.setString(1, buyer);
            statement.setString(2, seller);

            statement.execute();
        } catch (SQLException exc) {
            if (exc.getMessage().equals("Duplicate entry " + "'" +  seller + "-" + buyer + "'" +   " for key 'book_report.PRIMARY'")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "You already reported this seller!", ButtonType.CLOSE);
                alert.showAndWait();
            }
        }
    }
}
