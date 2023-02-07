package com.example.athena.use_case_controllers;

import com.example.athena.beans.BookBean;
import com.example.athena.beans.PurchaseResultBean;
import com.example.athena.boundaries.PurchaseBoundary;
import com.example.athena.entities.BookEntity;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.PurchaseException;


public class PurchaseUCC {
    public PurchaseResultBean purchase(BookBean bookBean) throws PurchaseException {
        PurchaseResultBean bean = PurchaseBoundary.purchase() ;
        if(bean.getPurchaseResult()) {
            BookEntity book = new BookEntity(bookBean.getBookTitle(), bookBean.getIsbn(), bookBean.getPrice(), bookBean.getOwner(), bookBean.getTimeStamp());
            LoggedStudent.getInstance().getCurrentStudent().finalizePurchase(book);
        }
        return bean ;
    }

}
