package com.example.athena.use_case_controllers;

import com.example.athena.beans.RecentBooksSearchResultBean;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.ReportException;

public class ReportSellerUCC {


    public void reportSeller(RecentBooksSearchResultBean book) throws ReportException {
        LoggedStudent.getInstance().getCurrentStudent().reportSeller(book.getOwner());
    }

}
