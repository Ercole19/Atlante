package com.example.athena.use_case_controllers;

import com.example.athena.beans.BookBean;
import com.example.athena.beans.PurchaseResultBean;
import com.example.athena.boundaries.PurchaseBoundary;
import com.example.athena.dao.BookDao;
import com.example.athena.entities.BookEntity;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.FindBookException;
import com.example.athena.exceptions.FindException;
import com.example.athena.exceptions.PurchaseException;

import java.util.ArrayList;
import java.util.List;

public class BuyControllerUCC {

    public PurchaseResultBean purchase(BookBean book) throws PurchaseException {
        BookDao dao = new BookDao();
        PurchaseResultBean bean = PurchaseBoundary.purchase() ;
        if(bean.getPurchaseResult()) dao.finalizePurchase(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), LoggedStudent.getInstance().getEmail().getMail(), book.getOwner(), book.getTimeStamp());
        return bean ;
    }

}
