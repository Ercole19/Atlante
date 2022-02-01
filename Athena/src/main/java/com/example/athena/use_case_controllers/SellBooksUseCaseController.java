package com.example.athena.use_case_controllers;

import com.example.athena.boundaries.IsbnCheckBoundary;
import com.example.athena.entities.BookEntity;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.graphical_controller.BookEntityBean;

public class SellBooksUseCaseController {

    public void putOnSale(BookEntityBean book) throws ISBNException {
        IsbnCheckBoundary.isbnCheck(book.getIsbn()) ;
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), book.getNegotiable()) ;
        bookE.toDB() ;
    }
}
