package com.example.athena.use_case_controllers;

import com.example.athena.entities.BookDao;
import com.example.athena.beans.normal.BookBean;
import com.example.athena.exceptions.BookException;

public class ReportSellerUCC {


    public void reportSeller(BookBean book, String emailBuyer) throws BookException {

        BookDao dao = new BookDao();
        dao.daoReportSeller(emailBuyer, book.getOwner());

    }

}
