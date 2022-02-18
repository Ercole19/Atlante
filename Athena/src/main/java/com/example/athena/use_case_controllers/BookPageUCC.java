package com.example.athena.use_case_controllers;

import com.example.athena.entities.BookDao;
import com.example.athena.entities.BookEntity;
import com.example.athena.entities.UserDao;
import com.example.athena.exceptions.BookException;
import com.example.athena.graphical_controller.BookEntityBean;

public class BookPageUCC {

    public BookEntityBean getBookInfos(String email, String isbn){
        BookEntityBean bean = null ;

        BookDao dao = new BookDao() ;
        BookEntity book = dao.getBookInformations(email, isbn);
        try {
             bean = new BookEntityBean(book.getBookTitle(), book.getIsbn(), String.valueOf(book.getPrice()), book.getNegotiable(), book.getImage(), book.getOwner());
        } catch (BookException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public  String[] getUserName(String email) {
        UserDao dao = new UserDao();
        String[] userCompleteName = dao.getName(email);
        return userCompleteName;
    }

}
