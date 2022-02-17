package com.example.athena.use_case_controllers;

import com.example.athena.entities.BookDao;
import com.example.athena.entities.BookEntity;
import com.example.athena.entities.User;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.graphical_controller.BookEntityBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static com.example.athena.boundaries.IsbnCheckBoundary.isbnCheck;

public class SellBooksUseCaseController {

    public void putOnSale(BookEntityBean book) throws ISBNException {
        isbnCheck(book.getIsbn()) ;
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), book.getNegotiable() , book.getImage(), User.getUser().getEmail()) ;
        bookE.toDB() ;
    }

    public void updateProduct(BookEntityBean book) throws ISBNException
    {
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), book.getNegotiable() , book.getImage(), User.getUser().getEmail()) ;
        bookE.updateInDB() ;
    }

    public void deleteProduct(BookEntityBean book)
    {
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), book.getNegotiable() , book.getImage(), User.getUser().getEmail()) ;
        bookE.removeFromDB() ;
    }

    public ObservableList<BookEntityBean> getBookList() throws BookException {
        ObservableList<BookEntityBean> bookBeanList = FXCollections.observableArrayList();
        List<BookEntity> bookList  ;
        BookDao bookDao = new BookDao();
        bookList = bookDao.getList();
        for (BookEntity entity : bookList) {

            BookEntityBean bean = new BookEntityBean(entity.getBookTitle(), entity.getIsbn(), String.valueOf(entity.getPrice()), entity.getNegotiable(), entity.getImage(), User.getUser().getEmail());
            bookBeanList.add(bean);

        }

        return  bookBeanList;

    }


    public void deleteImage(BookEntityBean bean, File image) throws IOException {

        BookDao dao = new BookDao() ;
        byte[] fileContent = Files.readAllBytes(image.toPath());
        dao.deleteImage(bean.getIsbn(), fileContent);

    }

}
