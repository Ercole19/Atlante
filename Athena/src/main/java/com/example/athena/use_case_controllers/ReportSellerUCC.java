package com.example.athena.use_case_controllers;

import com.example.athena.entities.BookDao;
import com.example.athena.graphical_controller.BookBean;

public class ReportSellerUCC {


    public void reportSeller(BookBean book, String emailBuyer) {

        BookDao dao = new BookDao();
        dao.daoReportSeller(emailBuyer, book.getOwner());

    }

}
