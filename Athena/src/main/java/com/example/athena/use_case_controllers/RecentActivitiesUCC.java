package com.example.athena.use_case_controllers;

import com.example.athena.entities.BookDao;
import com.example.athena.entities.BookEntity;
import com.example.athena.exceptions.BookException;
import com.example.athena.graphical_controller.BookBean;

import java.util.ArrayList;
import java.util.List;

public class RecentActivitiesUCC {
    private final BookDao dao = new BookDao();

    public List<BookBean> formatPurchasesResults(String email) throws BookException {
        List<BookEntity> list = dao.getRecentPurchasesResults(email);
        return getBookBeanList(list);
    }

    public List<BookBean> formatSoldItemsResults(String email) throws BookException {
        List<BookEntity> list = dao.getRecentSoldItemsFromDB(email);
        return getBookBeanList(list);
    }


    private List<BookBean> getBookBeanList(List<BookEntity> bookEntityList) throws BookException {
        List<BookBean> bookList = new ArrayList<>();
        for (BookEntity book : bookEntityList) {
            BookBean bookBean = new BookBean() ;
            if (book.getPurchaser() == null) bookBean.setOwner(book.getOwner());
            else bookBean.setPurchaser(book.getPurchaser());
            bookBean.setTitle(book.getBookTitle());
            bookBean.setIsbn(book.getIsbn());
            bookBean.setPrice(Float.toString(book.getPrice()));
            bookList.add(bookBean);

        }
        return bookList;
    }
}
