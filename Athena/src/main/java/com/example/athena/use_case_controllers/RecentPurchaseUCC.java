package com.example.athena.use_case_controllers;

import com.example.athena.entities.BookDao;
import com.example.athena.entities.BookEntity;
import com.example.athena.graphical_controller.BookEntityBean;

import java.util.ArrayList;
import java.util.List;

public class RecentPurchaseUCC {

    public List<BookEntityBean> formatResults(String email) {
        BookDao dao = new BookDao();

        List<BookEntityBean> bookList = new ArrayList<>();
        List<BookEntity> list = dao.getBookResults(email);

        for (BookEntity book : list) {
            BookEntityBean bookBean = new BookEntityBean(book.getOwner(), book.getBookTitle(), book.getIsbn(), book.getPrice());
            bookList.add(bookBean);

        }
        return bookList;

    }

}
