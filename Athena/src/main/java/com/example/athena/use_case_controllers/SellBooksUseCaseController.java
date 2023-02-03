package com.example.athena.use_case_controllers;

import com.example.athena.beans.BookBean;
import com.example.athena.beans.ISBNBean;
import com.example.athena.beans.IsThereANotificationBean;
import com.example.athena.dao.BookDao;
import com.example.athena.entities.BookEntity;
import com.example.athena.entities.BooksSubject;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.ISBNException;

import static com.example.athena.boundaries.IsbnCheckBoundary.isbnCheck;

public class SellBooksUseCaseController {

    public void putOnSale(BookBean book) throws ISBNException, BookException {
        ISBNBean bean = new ISBNBean();
        bean.setISBN(book.getIsbn());
        if (!isbnCheck(bean).getResult()) throw new ISBNException("Check failed") ;
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), book.getPrice(), book.getNegotiable() , book.getImage(), LoggedStudent.getInstance().getEmail().getMail());
        BooksSubject.getInstance().addBook(bookE);
    }

    public void updateProduct(BookBean oldBook, BookBean newBook) throws ISBNException, BookException
    {
        BookEntity oldBookEntity = new BookEntity(oldBook.getBookTitle(), oldBook.getIsbn(), oldBook.getPrice(), oldBook.getNegotiable() , oldBook.getImage(), LoggedStudent.getInstance().getEmail().getMail(), oldBook.getTimeStamp() ) ;
        BookEntity newBookEntity = new BookEntity(newBook.getBookTitle(), newBook.getIsbn(), newBook.getPrice(), newBook.getNegotiable(), newBook.getImage(), LoggedStudent.getInstance().getEmail().getMail());
        BooksSubject.getInstance().deleteBook(oldBookEntity, oldBook.getIndex());
        BooksSubject.getInstance().addBook(newBookEntity);
    }

    public void deleteProduct(BookBean book) throws BookException
    {
        BookEntity bookE = new BookEntity(book.getBookTitle(), book.getIsbn(), book.getPrice(), book.getNegotiable() , book.getImage(), LoggedStudent.getInstance().getEmail().getMail(), book.getTimeStamp()) ;
        BooksSubject.getInstance().deleteBook(bookE, book.getIndex());
    }

    public IsThereANotificationBean getNotification() throws BookException {
        IsThereANotificationBean bean = new IsThereANotificationBean();
        bean.setNot(BooksSubject.getInstance().getNotifications());
        return bean;
    }

}
