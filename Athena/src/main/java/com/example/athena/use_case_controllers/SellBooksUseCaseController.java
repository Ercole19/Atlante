package com.example.athena.use_case_controllers;

import com.example.athena.entities.BookDao;
import com.example.athena.entities.BookEntity;
import com.example.athena.entities.User;
import com.example.athena.entities.UserDao;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.graphical_controller.BookBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static com.example.athena.boundaries.IsbnCheckBoundary.isbnCheck;

public class SellBooksUseCaseController {

    public void putOnSale(BookBean book) throws ISBNException {
        isbnCheck(book.getIsbn()) ;
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), book.getNegotiable() , book.getImage(), User.getUser().getEmail()) ;
        bookE.toDB() ;
    }

    public void updateProduct(BookBean book) throws ISBNException
    {
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), book.getNegotiable() , book.getImage(), User.getUser().getEmail()) ;
        bookE.updateInDB() ;
    }

    public void deleteProduct(BookBean book)
    {
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), book.getNegotiable() , book.getImage(), User.getUser().getEmail()) ;
        bookE.removeFromDB() ;
    }

    public ObservableList<BookBean> getBookList() throws BookException {
        ObservableList<BookBean> bookBeanList = FXCollections.observableArrayList();
        List<BookEntity> bookList  ;
        BookDao bookDao = new BookDao();
        bookList = bookDao.getList();
        for (BookEntity entity : bookList) {

            BookBean bean = new BookBean(entity.getBookTitle(), entity.getIsbn(), String.valueOf(entity.getPrice()), entity.getNegotiable(), entity.getImage(), User.getUser().getEmail());
            bookBeanList.add(bean);

        }

        return  bookBeanList;

    }


    public void deleteImage(BookBean bean, File image) throws IOException {

        BookDao dao = new BookDao() ;
        byte[] fileContent = Files.readAllBytes(image.toPath());
        dao.deleteImage(bean.getIsbn(), fileContent);

    }

    public int getTotalReport(){
        UserDao dao = new UserDao();
        return  dao.getTotalReport();
    }

}
