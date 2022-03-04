package com.example.athena.use_case_controllers;

import com.example.athena.entities.BookDao;
import com.example.athena.entities.BookEntity;
import com.example.athena.exceptions.BookException;
import com.example.athena.graphical_controller.BookBean;

import java.util.ArrayList;
import java.util.List;

public class RecentPurchaseUCC {

    public List<BookBean> formatResults(String email) throws BookException {
        BookDao dao = new BookDao();

        List<BookBean> bookList = new ArrayList<>();
        List<BookEntity> list = dao.getBookResults(email);

        for (BookEntity book : list) {
            BookBean bookBean = new BookBean() ;
            bookBean.setOwner(book.getOwner());
            bookBean.setTitle(book.getBookTitle());
            bookBean.setIsbn(book.getIsbn());
            bookBean.setPrice(Float.toString(book.getPrice()));
            bookList.add(bookBean);

        }
        return bookList;

    }

}
