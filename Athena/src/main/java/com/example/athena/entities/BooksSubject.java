package com.example.athena.entities;

import com.example.athena.engineering_classes.observer_pattern.AbstractSubject;
import com.example.athena.exceptions.BidException;
import com.example.athena.exceptions.BookException;
import com.example.athena.beans.normal.BookBean;
import com.example.athena.exceptions.UserInfoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BooksSubject extends AbstractSubject {
    private final List<BookEntity> totalBooksOnSell = new ArrayList<>();
    private static BooksSubject instance;
    private String[] sellerNameAndSurname;

    private  BooksSubject ()
    {

    }

    public static synchronized BooksSubject getInstance()
    {
        if (instance == null) {
            instance = new BooksSubject();
        }
        return instance;
    }

    public void addBook(BookEntity book) throws BookException
    {
        BookDao dao = new BookDao() ;
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        dao.insertBook(book.getTitle(), book.getIsbn(), book.getPrice(), book.getNegotiable(), book.getImage(), timestamp);
        book.setSaleTimestamp(String.valueOf(timestamp));
        this.totalBooksOnSell.add(book);
        super.notifyObserver();
    }


    public void deleteBook(BookEntity book, int index) throws BookException
    {
        this.totalBooksOnSell.remove(index);
        BookDao dao = new BookDao() ;
        dao.deleteBook(book.getIsbn(), book.getSaleTimestamp());
        super.notifyObserver();
    }

    public void addBid(BidEntity bid) throws BidException {
        BookDao dao = new BookDao();
        dao.addBookBid(bid);
    }

    public String getSellerName() throws BookException, UserInfoException
    {
        if(this.sellerNameAndSurname == null) {
            getBooks();
        }
        return this.sellerNameAndSurname[0];
    }

    public String getSellerSurname() throws BookException, UserInfoException
    {
        if(this.sellerNameAndSurname == null) {
            getBooks();
        }
        return this.sellerNameAndSurname[1];
    }

    private void getBooks() throws BookException, UserInfoException {
        BookDao bookDao = new BookDao();
        this.totalBooksOnSell.addAll(bookDao.getList());
        UserDao dao = new UserDao();
        this.sellerNameAndSurname = dao.getName(Student.getInstance().getEmail());
    }

    public ObservableList<BookBean> getBooksBeansList() throws BookException, UserInfoException{
        ObservableList<BookBean> bookBeanList = FXCollections.observableArrayList();
        int i = 0 ;
        if(this.totalBooksOnSell.isEmpty()) {
            getBooks();
        }
        for (BookEntity entity : this.totalBooksOnSell) {

            BookBean bean = new BookBean();
            bean.setBookTitle(entity.getTitle());
            bean.setIsbn(entity.getIsbn());
            bean.setPrice(String.valueOf(entity.getPrice()));
            bean.setIsNegotiable(entity.getNegotiable());
            bean.setImage(entity.getImage());
            bean.setOwner(Student.getInstance().getEmail());
            bean.setIndex(i);
            bean.setTimeStamp(entity.getSaleTimestamp());
            bookBeanList.add(bean);
            i++;

        }
        return  bookBeanList;
    }

    public void logOut()
    {
        BooksSubject.instance.totalBooksOnSell.clear();
        BooksSubject.instance.sellerNameAndSurname = null ;
    }
}