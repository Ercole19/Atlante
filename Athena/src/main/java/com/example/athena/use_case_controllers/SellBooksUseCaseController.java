package com.example.athena.use_case_controllers;

import com.example.athena.entities.*;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.graphical_controller.BookBean;

import static com.example.athena.boundaries.IsbnCheckBoundary.isbnCheck;

public class SellBooksUseCaseController {

    public void putOnSale(BookBean book) throws ISBNException {
        isbnCheck(book.getIsbn()) ;
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), book.getNegotiable() , book.getImage(), Student.getInstance().getEmail()) ;
        BooksSubject.getInstance().addBook(bookE);
    }

    public void updateProduct(BookBean oldBook, BookBean newBook) throws ISBNException
    {
        BookEntity oldBookEntity = new BookEntity(oldBook.getBookTitle(), oldBook.getIsbn(), Float.parseFloat(oldBook.getPrice()), oldBook.getNegotiable() , oldBook.getImage(), Student.getInstance().getEmail()) ;
        BookEntity newBookEntity = new BookEntity(newBook.getTitle(), newBook.getIsbn(), Float.parseFloat(newBook.getPrice()), newBook.getNegotiable(), newBook.getImage(), Student.getInstance().getEmail());
        BooksSubject.getInstance().deleteBook(oldBookEntity, oldBook.getIndex());
        BooksSubject.getInstance().addBook(newBookEntity);
    }

    public void deleteProduct(BookBean book)
    {
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), book.getNegotiable() , book.getImage(), Student.getInstance().getEmail()) ;
        BooksSubject.getInstance().deleteBook(bookE, book.getIndex());
    }
}
