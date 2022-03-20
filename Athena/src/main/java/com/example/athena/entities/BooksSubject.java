package com.example.athena.entities;

import com.example.athena.engineering_classes.observer_pattern.AbstractSubject;
import com.example.athena.exceptions.BookException;
import com.example.athena.graphical_controller.BookBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class BooksSubject extends AbstractSubject {
    private List<BookEntity> totalBooksOnSell ;
    private static BooksSubject instance;
    private final String[] sellerNameAndSurname;

    private  BooksSubject ()
    {
        BookDao bookDao = new BookDao();
        this.totalBooksOnSell = bookDao.getList();
        UserDao dao = new UserDao();
        this.sellerNameAndSurname = dao.getName(Student.getInstance().getEmail());
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
        this.totalBooksOnSell.add(book);
        BookDao dao = new BookDao() ;
        dao.insertBook(book.getBookTitle(), book.getIsbn(), book.getPrice(), book.getNegotiable(), book.getImage());
        super.notifyObserver();
    }


    public void deleteBook(BookEntity book, int index)
    {
        this.totalBooksOnSell.remove(index);
        BookDao dao = new BookDao() ;
        dao.deleteBook(book.getIsbn());
        super.notifyObserver();
    }

    public String getSellerName()
    {
        return this.sellerNameAndSurname[0];
    }

    public String getSellerSurname()
    {
        return this.sellerNameAndSurname[1];
    }



    public ObservableList<BookBean> getBooksBeansList() throws BookException {
        ObservableList<BookBean> bookBeanList = FXCollections.observableArrayList();
        int i = 0 ;
        for (BookEntity entity : this.totalBooksOnSell) {

            BookBean bean = new BookBean();
            bean.setTitle(entity.getBookTitle());
            bean.setIsbn(entity.getIsbn());
            bean.setPrice(String.valueOf(entity.getPrice()));
            bean.setNegotiable(entity.getNegotiable());
            bean.setImage(entity.getImage());
            bean.setOwner(Student.getInstance().getEmail());
            bean.setIndex(i);
            bookBeanList.add(bean);
            i++;

        }
        return  bookBeanList;
    }
}
