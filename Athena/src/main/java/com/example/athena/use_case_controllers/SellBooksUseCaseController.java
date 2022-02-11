package com.example.athena.use_case_controllers;

import com.example.athena.entities.BookEntity;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.graphical_controller.BookEntityBean;

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

  /*  public List<BookEntityBean> getBookList() {
        List<BookEntity> bookList = new ArrayList<>() ;

    }*/
}
