package com.example.athena.use_case_controllers;

import com.example.athena.boundaries.PurchaseBoundary;
import com.example.athena.entities.BookDao;
import com.example.athena.entities.BookEntity;
import com.example.athena.entities.Student;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.FindBookException;
import com.example.athena.exceptions.FindException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.graphical_controller.BookBean;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class BuyControllerUCC {

    private static final BookDao dao = new BookDao();

    public List<BookBean> formatSearchResults(String query) throws FindBookException, BookException {

        ArrayList<BookBean> result = new ArrayList<>();
        try {
           List<BookEntity> bookinfos = dao.findBooks(query);
           for (BookEntity entity : bookinfos)
           {
               BookBean book = new BookBean();
               book.setTitle(entity.getBookTitle());
               book.setIsbn(entity.getIsbn());
               book.setPrice(String.valueOf(entity.getPrice()));
               book.setNegotiable(entity.getNegotiable());
               book.setOwner(entity.getOwner());
               book.setImage(entity.getImage());
               result.add(book);
          }
       }
        catch (FindException e) {
            throw new FindBookException(e.getMessage());
        }
        return result;
    }
    
    public void purchase(BookBean book) {
        try {
           if(PurchaseBoundary.purchase())dao.finalizePurchase(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), Student.getInstance().getEmail(), book.getOwner());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
