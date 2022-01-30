package com.example.athena.UseCaseControllers;

import com.example.athena.Boundaries.IsbnCheckBoundary;
import com.example.athena.Entities.bookEntity;
import com.example.athena.Exceptions.ISBNException;
import com.example.athena.GraphicalController.BookEntityBean;

public class SellBooksUseCaseController {

    public void putOnSale(BookEntityBean book) throws ISBNException {
        IsbnCheckBoundary.ISBNcheck(book.getISBN()) ;
        bookEntity bookE = new bookEntity(book.getBookTitle(), book.getISBN(), Float.parseFloat(book.getPrice()), book.getNegotiable()) ;
        bookE.toDB() ;
    }
}
