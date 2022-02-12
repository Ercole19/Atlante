package com.example.athena.use_case_controllers;

import com.example.athena.entities.BookDao;
import com.example.athena.entities.BookEntity;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.graphical_controller.BookEntityBean;
import com.example.athena.graphical_controller.ExamEntityBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

import static com.example.athena.boundaries.IsbnCheckBoundary.isbnCheck;

public class SellBooksUseCaseController {

    public void putOnSale(BookEntityBean book) throws ISBNException {
        isbnCheck(book.getIsbn()) ;
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), book.getNegotiable() , book.getImage()) ;
        bookE.toDB() ;
    }

    public void updateProduct(BookEntityBean book) throws ISBNException
    {
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), book.getNegotiable() , book.getImage()) ;
        bookE.updateInDB() ;
    }

    public void deleteProduct(BookEntityBean book)
    {
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), book.getNegotiable() , book.getImage()) ;
        bookE.removeFromDB() ;
    }

    public ObservableList<BookEntityBean> getBookList() throws BookException {
        ObservableList<BookEntityBean> bookBeanList = FXCollections.observableArrayList();
        List<BookEntity> bookList = new ArrayList<>() ;
        BookDao bookDao = new BookDao();
        bookList = bookDao.getList();
        for (BookEntity entity : bookList) {

            BookEntityBean bean = new BookEntityBean(entity.getBookTitle(), entity.getIsbn(), String.valueOf(entity.getPrice()), entity.getNegotiable(), entity.getImage());
            bookBeanList.add(bean);

        }

        return  bookBeanList;

    }


    public void deleteImage(BookEntityBean bean, byte[] image){

        BookDao dao =new BookDao() ;
        dao.deleteImage(bean.getIsbn(), image);

    }

}
