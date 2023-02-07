package com.example.athena.use_case_controllers;

import com.example.athena.beans.BookBean;
import com.example.athena.beans.ISBNBean;
import com.example.athena.beans.IsThereANotificationBean;
import com.example.athena.entities.PersonalBookShelf;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.ISBNException;
import static com.example.athena.boundaries.IsbnCheckBoundary.isbnCheck;

public class ManageYourSellingBooksUCC {

    public void putOnSale(BookBean book) throws ISBNException, BookException {
        ISBNBean bean = new ISBNBean();
        bean.setISBN(book.getIsbn());
        if (!isbnCheck(bean).getResult()) throw new ISBNException("Check failed") ;
        PersonalBookShelf.getInstance().addBook(book);
    }

    public void updateProduct(BookBean oldBook, BookBean newBook) throws ISBNException, BookException
    {
        PersonalBookShelf.getInstance().deleteBook(oldBook);
        PersonalBookShelf.getInstance().addBook(newBook);
    }

    public void deleteProduct(BookBean book) throws BookException
    {
        PersonalBookShelf.getInstance().deleteBook(book);
    }

    public IsThereANotificationBean getNotification() throws BookException {
        IsThereANotificationBean bean = new IsThereANotificationBean();
        bean.setNot(PersonalBookShelf.getInstance().getNotifications());
        return bean;
    }

}
