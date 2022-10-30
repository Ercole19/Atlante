package com.example.athena.entities;


import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.FindException;
import com.example.athena.exceptions.PurchaseException;
import com.example.athena.exceptions.UserInfoException;
import com.jcraft.jsch.IO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class BookDao extends AbstractDAO {
    private final String email = Student.getInstance().getEmail();
    private int i = 1 ;
    private static int imageCount = 0;

    private static synchronized void incrementImageCounter() {
        imageCount++ ;
    }

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
                List<File> images = getBookImages(isbn, email, timestamp);

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

    private List<File> getBookImages(String isbn, String email, String timestamp) throws SQLException, IOException
    {
        List<File> list = new ArrayList<>();
        try (PreparedStatement statement = this.getConnection().prepareStatement("SELECT image from athena.book_images where email = ? and isbn  = ? and bookSaleTimestamp = ?  order by count_image")) {

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
                List<File> booksFiles = new ArrayList<>();
                if (set.getBlob(5) != null && set.getBlob(5).length() != 0) {
                    byte[] image = set.getBlob(5).getBytes(1,(int) set.getBlob(5).length()) ;
                    String pathname = "image" + imageCount + ".png" ;
                    booksFiles.add(writeImage(image, pathname));
                    BookDao.incrementImageCounter();
                }
                BookEntity bookEntity = new BookEntity(set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getBoolean(6), booksFiles, String.valueOf(set.getTimestamp(7)));
                books.add(bookEntity);
            }

        } catch (SQLException | IOException exc) {
           throw new FindException(exc.getMessage());
        }
        return books;
    }

    public void finalizePurchase(String title, String isbn, Float price, String emailBuyer, String emailVendor, String timestamp) throws PurchaseException
    {
        try(PreparedStatement statement = this.getConnection().prepareStatement("CALL finalizePurchase(?,?,?,?,?,?)")) {

            statement.setString(1, title);
            statement.setString(2, isbn);
            statement.setFloat(3, price);
            statement.setString(4, emailBuyer);
            statement.setString(5, emailVendor);
            statement.setTimestamp(6, Timestamp.valueOf(timestamp));

            statement.execute();

        }catch (SQLException | IOException exc) {
            throw new PurchaseException(exc.getMessage());
        }
    }

    public List<BookEntity> getRecentPurchasesResults(String email) throws BookException {
        List<BookEntity> booksList = new ArrayList<>();
        try(PreparedStatement statement = this.getConnection().prepareStatement("SELECT vendorEmail, bookTitle, bookIsbn, bookPrice from athena.recent_purchases where purchaserEmail = ?")) {

            statement.setString(1, email);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                BookEntity book = new BookEntity(set.getString(2), set.getString(3), set.getString(4), set.getString(1), 1 );
                booksList.add(book);
            }

        }catch (SQLException | IOException exc) {
            throw new BookException(exc.getMessage()) ;
        }
        return booksList;
    }


    public List<BookEntity> getRecentSoldItemsFromDB(String vendorEmail) throws BookException{
        List<BookEntity> booksEntityList = new ArrayList<>();
        try(PreparedStatement statement = this.getConnection().prepareStatement("SELECT purchaserEmail, bookTitle, bookIsbn, bookPrice from athena.recent_purchases where vendorEmail = ?")) {

            statement.setString(1, vendorEmail);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                BookEntity book = new BookEntity(set.getString(2), set.getString(3), set.getString(4), set.getString(1), 0);
                booksEntityList.add(book);
            }

        }catch (SQLException | IOException exc) {
            throw new BookException(exc.getMessage()) ;
        }
        return booksEntityList;
    }


    public void daoReportSeller(String buyer, String seller) throws BookException {
        try(PreparedStatement statement = this.getConnection().prepareStatement("call athena.reportSeller(?,?)")) {

            statement.setString(1, buyer);
            statement.setString(2, seller);

            statement.execute();
        } catch (SQLException | IOException exc) {
            throw new BookException(exc.getMessage());
        }
    }

    public void addBookBid(BidEntity bid) throws BidException {
        try(PreparedStatement statement = this.getConnection().prepareStatement("CALL add_new_bid (?,?,?,?,?)")){
            statement.setString(1, bid.getOwner());
            statement.setString(2,bid.getBidder());
            statement.setString(3, bid.getNewPrice());
            statement.setString(4, bid.getBookIsbn());
            statement.setTimestamp(5, Timestamp.valueOf(bid.getBookTimestamp()));

            statement.execute();
        }
        catch (SQLException | IOException e){
            throw new BidException(e.getMessage());
        }
    }

    public List<BidEntity> getBookBids(String seller, String isbn, String timeStamp) throws BidException{
        try(PreparedStatement statement = this.getConnection().prepareStatement("SELECT * FROM athena.books_bids WHERE Seller = ? AND BookIsbn = ? AND BookTimestamp = ?")){

            statement.setString(1, seller) ;
            statement.setString(2, isbn);
            statement.setTimestamp(3, Timestamp.valueOf(timeStamp)) ;
            ResultSet set = statement.executeQuery();
            return extractBidsFromResultSet(set) ;
        }
        catch (SQLException | IOException e){
            throw new BidException(e.getMessage());
        }
    }

    public List<BidEntity> getBidderBids(String bidder) throws BidException{
        try(PreparedStatement statement = this.getConnection().prepareStatement("SELECT * FROM athena.books_bids WHERE Bidder = ?")){
            statement.setString(1, bidder) ;
            ResultSet set = statement.executeQuery() ;
            return extractBidsFromResultSet(set) ;
        }
        catch (SQLException | IOException e){
            throw new BidException(e.getMessage());
        }
    }

    private List<BidEntity> extractBidsFromResultSet(ResultSet set) throws SQLException {

        List<BidEntity> retVal = new ArrayList<>() ;

        while (set.next()) {
            BidEntity bid = new BidEntity(set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getTimestamp(5).toString(),
                    set.getString(4),
                    BidStatusEnum.valueOf(set.getString(6))) ;
            retVal.add(bid) ;
        }

        return retVal ;
    }

    public void deleteBid(BidEntity entity) throws BidException {
        try(PreparedStatement statement = this.getConnection().prepareStatement("DELETE FROM athena.books_bids WHERE Seller = ? AND Bidder = ? AND BookIsbn = ? AND BookTimeStamp = ? ")){
            statement.setString(1, entity.getOwner());
            statement.setString(2, entity.getBidder());
            statement.setString(3, entity.getBookIsbn());
            statement.setTimestamp(4, Timestamp.valueOf(entity.getBookTimestamp()));
            statement.execute() ;
        }
        catch (SQLException | IOException e){
            throw new BidException(e.getMessage());
        }
    }

    public void updateBidStatus(BidEntity entity) throws BidException {
        try(PreparedStatement statement = this.getConnection().prepareStatement("UPDATE athena.books_bids SET BidStatus = ? WHERE Seller = ? AND Bidder = ? AND BookIsbn = ? AND BookTimeStamp = ?")){
            statement.setString(1, entity.getStatus().toString());
            statement.setString(2, entity.getOwner());
            statement.setString(3, entity.getBidder());
            statement.setString(4, entity.getBookIsbn());
            statement.setTimestamp(5, Timestamp.valueOf(entity.getBookTimestamp()));
            statement.execute() ;
        }
        catch (SQLException | IOException e){
            throw new BidException(e.getMessage());
        }
    }

    public void payAcceptedBid(BidEntity bid) throws BidException {
        try(PreparedStatement statement = this.getConnection().prepareStatement("CALL finalizeBidPurchase(?,?,?,?)")){
            statement.setString(1, bid.getOwner());
            statement.setString(2, bid.getBidder());
            statement.setString(3, bid.getBookIsbn());
            statement.setTimestamp(4, Timestamp.valueOf(bid.getBookTimestamp()));
            statement.execute() ;
        }
        catch (SQLException | IOException e){
            throw new BidException(e.getMessage());
        }
    }
}
