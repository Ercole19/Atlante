package com.example.athena.entities;

import com.example.athena.engineering_classes.observer_pattern.AbstractSubject;
import com.example.athena.exceptions.BookException;
import com.example.athena.graphical_controller.BookBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class BooksSubject extends AbstractSubject {
    private List<BookEntity> totalBooksOnSell = new ArrayList<>();
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

    public void addBook(BookEntity book)
    {
        BookDao dao = new BookDao() ;
        dao.insertBook(book.getTitle(), book.getIsbn(), book.getPrice(), book.getNegotiable(), book.getImage());
        this.totalBooksOnSell.add(book);
        super.notifyObserver();
    }


    public void deleteBook(BookEntity book, int index)
    {
        this.totalBooksOnSell.remove(index);
        BookDao dao = new BookDao() ;
        dao.deleteBook(book.getIsbn(), book.getSaleTimestamp());
        super.notifyObserver();
    }

    public String getSellerName()
    {
        if(this.sellerNameAndSurname == null) {
            getBooks();
        }
        return this.sellerNameAndSurname[0];
    }

    public String getSellerSurname()
    {
        if(this.sellerNameAndSurname == null) {
            getBooks();
        }
        return this.sellerNameAndSurname[1];
    }

    private void getBooks() {
        BookDao bookDao = new BookDao();
        this.totalBooksOnSell.addAll(bookDao.getList());
        UserDao dao = new UserDao();
        this.sellerNameAndSurname = dao.getName(Student.getInstance().getEmail());
    }

    public ObservableList<BookBean> getBooksBeansList() throws BookException {
        ObservableList<BookBean> bookBeanList = FXCollections.observableArrayList();
        int i = 0 ;
        if(this.totalBooksOnSell.isEmpty()) {
            getBooks();
        }
        for (BookEntity entity : this.totalBooksOnSell) {

            BookBean bean = new BookBean();
            bean.setTitle(entity.getTitle());
            bean.setIsbn(entity.getIsbn());
            bean.setPrice(String.valueOf(entity.getPrice()));
            bean.setIsNegotiable(entity.getNegotiable());
            bean.setImage(entity.getImage());
            bean.setOwner(Student.getInstance().getEmail());
            bean.setIndex(i);
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
