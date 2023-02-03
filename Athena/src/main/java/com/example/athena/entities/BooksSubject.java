package com.example.athena.entities;

import com.example.athena.beans.BookBean;
import com.example.athena.dao.BookDao;
import com.example.athena.dao.UserDao;
import com.example.athena.engineering_classes.observer_pattern.AbstractSubject;
import com.example.athena.exceptions.BidException;
import com.example.athena.exceptions.BookException;
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

    private void getBooks() throws BookException {
        BookDao bookDao = new BookDao();
        this.totalBooksOnSell.addAll(bookDao.getList());
    }

    public ObservableList<BookBean> getBooksBeansList() throws BookException{
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
            bean.setOwner(LoggedStudent.getInstance().getEmail().getMail());
            bean.setIndex(i);
            bean.setTimeStamp(entity.getSaleTimestamp());
            bookBeanList.add(bean);
            i++;

        }
        return  bookBeanList;
    }

    public boolean getNotifications() throws BookException {
        return new BookDao().getNotificationsFromDb();
    }

    public void logOut()
    {
        BooksSubject.instance.totalBooksOnSell.clear();
    }
}