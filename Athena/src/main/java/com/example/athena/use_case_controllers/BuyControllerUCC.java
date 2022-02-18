package com.example.athena.use_case_controllers;

import com.example.athena.boundaries.PurchaseBoundary;
import com.example.athena.entities.BookDao;
import com.example.athena.entities.BookEntity;
import com.example.athena.entities.User;
import com.example.athena.graphical_controller.BookEntityBean;
import com.example.athena.graphical_controller.BookSearchResultBean;

import java.util.ArrayList;
import java.util.List;

public class BuyControllerUCC {

    static final private BookDao dao = new BookDao();

    public List<BookSearchResultBean> formatSearchResults(String query) {

        List<BookEntity> bookinfos = dao.findBooks(query);
        ArrayList<BookSearchResultBean> result = new ArrayList<>();
        for (BookEntity entity : bookinfos) {

            BookSearchResultBean book = new BookSearchResultBean(entity.getBookTitle(), entity.getIsbn(), entity.getPrice(), entity.getOwner(), entity.getFile());
            result.add(book);

        }

        return result;
    }
    
    public void purchase(BookEntityBean book) {
        try {
           if(PurchaseBoundary.purchase())dao.finalizePurchase(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), User.getUser().getEmail(), book.getOwner());
           else System.out.println(1);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
