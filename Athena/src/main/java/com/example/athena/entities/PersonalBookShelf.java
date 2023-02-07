package com.example.athena.entities;

import com.example.athena.beans.BookBean;
import com.example.athena.dao.BookDao;
import com.example.athena.engineering_classes.observer_pattern.AbstractSubject;
import com.example.athena.exceptions.BookException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PersonalBookShelf extends AbstractSubject {
    private final List<BookEntity> totalBooksOnSell = new ArrayList<>();
    private static PersonalBookShelf instance;
    private final BookDao dao = new BookDao() ;

    private PersonalBookShelf()
    {

    }

    public static synchronized PersonalBookShelf getInstance()
    {
        if (instance == null) {
            instance = new PersonalBookShelf();
        }
        return instance;
    }

    public void addBook(BookBean bean) throws BookException
    {
        BookEntity book = new BookEntity(bean.getBookTitle(), bean.getIsbn(), bean.getPrice(), bean.getNegotiable() , bean.getImage(), String.valueOf(Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))));
        dao.insertBook(book.getTitle(), book.getIsbn(), book.getPrice(), book.getNegotiable(), book.getImage(), Timestamp.valueOf(book.getSaleTimestamp()));
        this.totalBooksOnSell.add(book);
        super.notifyObserver();
    }


    public void deleteBook(BookBean book) throws BookException
    {
        this.totalBooksOnSell.remove(book.getIndex());
        dao.deleteBook(book.getIsbn(), book.getTimeStamp());
        super.notifyObserver();
    }

    private void getBooks() throws BookException {
        this.totalBooksOnSell.addAll(dao.getList());
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
        return dao.getNotificationsFromDb();
    }

    public void logOut()
    {
        this.totalBooksOnSell.clear();
        instance = null;
    }
}