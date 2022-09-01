package com.example.athena.use_case_controllers;

import com.example.athena.entities.*;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.graphical_controller.BookBean;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.example.athena.boundaries.IsbnCheckBoundary.isbnCheck;

public class SellBooksUseCaseController {

    public void putOnSale(BookBean book) throws ISBNException {
        isbnCheck(book.getIsbn()) ;
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), book.getPrice(), book.getNegotiable() , book.getImage(), Student.getInstance().getEmail());
        BooksSubject.getInstance().addBook(bookE);
    }

    public void updateProduct(BookBean oldBook, BookBean newBook) throws ISBNException
    {
        BookEntity oldBookEntity = new BookEntity(oldBook.getBookTitle(), oldBook.getIsbn(), oldBook.getPrice(), oldBook.getNegotiable() , oldBook.getImage(), Student.getInstance().getEmail(), oldBook.getTimeStamp() ) ;
        BookEntity newBookEntity = new BookEntity(newBook.getBookTitle(), newBook.getIsbn(), newBook.getPrice(), newBook.getNegotiable(), newBook.getImage(), Student.getInstance().getEmail());
        BooksSubject.getInstance().deleteBook(oldBookEntity, oldBook.getIndex());
        BooksSubject.getInstance().addBook(newBookEntity);
    }

    public void deleteProduct(BookBean book)
    {
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), book.getPrice(), book.getNegotiable() , book.getImage(), Student.getInstance().getEmail(), book.getTimeStamp()) ;
        BooksSubject.getInstance().deleteBook(bookE, book.getIndex());
    }
}
