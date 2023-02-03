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

public class BuyUCC {

    public PurchaseResultBean purchase(BookBean book) throws PurchaseException {
        PurchaseResultBean bean = PurchaseBoundary.purchase() ;
        if(bean.getPurchaseResult()) LoggedStudent.getInstance().getCurrentStudent().finalizePurchase(book.getBookTitle(), book.getIsbn(), Float.parseFloat(book.getPrice()), book.getOwner(), book.getTimeStamp());
        return bean ;
    }

}
