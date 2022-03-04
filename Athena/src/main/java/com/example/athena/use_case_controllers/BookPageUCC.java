package com.example.athena.use_case_controllers;

import com.example.athena.entities.BookDao;
import com.example.athena.entities.BookEntity;
import com.example.athena.entities.UserDao;
import com.example.athena.exceptions.BookException;
import com.example.athena.graphical_controller.BookBean;
import javafx.scene.control.Alert;

public class BookPageUCC {

    public BookBean getBookInfos(String email, String isbn){
        BookBean bean = null ;

        BookDao dao = new BookDao() ;
        BookEntity book = dao.getBookInformations(email, isbn);
        try {
             bean = new BookBean(book.getBookTitle(), book.getIsbn(), String.valueOf(book.getPrice()), book.getNegotiable(), book.getImage(), book.getOwner());
        } catch (BookException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setContentText(e.getMessage()) ;
            alert.showAndWait() ;
        }
        return bean;
    }

    public  String[] getUserName(String email) {
        UserDao dao = new UserDao();
        return dao.getName(email);
    }

}
