package com.example.athena.use_case_controllers;

import com.example.athena.entities.*;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.graphical_controller.BookBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.print.Book;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static com.example.athena.boundaries.IsbnCheckBoundary.isbnCheck;

public class SellBooksUseCaseController {

    public void putOnSale(BookBean book) throws ISBNException {
        isbnCheck(book.getIsbn()) ;
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), book.getNegotiable() , book.getImage(), User.getUser().getEmail()) ;
        BooksSubject.getInstance().addBook(bookE);
    }

    public void updateProduct(BookBean oldBook, BookBean newBook) throws ISBNException
    {
        BookEntity oldBookEntity = new BookEntity(oldBook.getBookTitle(), oldBook.getIsbn(), Float.parseFloat(oldBook.getPrice()), oldBook.getNegotiable() , oldBook.getImage(), User.getUser().getEmail()) ;
        BookEntity newBookEntity = new BookEntity(newBook.getTitle(), newBook.getIsbn(), Float.parseFloat(newBook.getPrice()), newBook.getNegotiable(), newBook.getImage(), User.getUser().getEmail());
        BooksSubject.getInstance().deleteBook(oldBookEntity, oldBook.getIndex());
        BooksSubject.getInstance().addBook(newBookEntity);
    }

    public void deleteProduct(BookBean book)
    {
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), book.getNegotiable() , book.getImage(), User.getUser().getEmail()) ;
        BooksSubject.getInstance().deleteBook(bookE, book.getIndex());
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
