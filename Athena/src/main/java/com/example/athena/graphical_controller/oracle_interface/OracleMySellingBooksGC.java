package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.BookBean;
import com.example.athena.entities.BooksSubject;
import com.example.athena.exceptions.BookException;
import com.example.athena.view.oracle_view.LabelView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OracleMySellingBooksGC {
    public String getMyBooks() {
        LabelView labelView = new LabelView();
        String booksString = "";
        ObservableList<BookBean> books = FXCollections.observableArrayList() ;
        try {
            books = BooksSubject.getInstance().getBooksBeansList();
        } catch (BookException e) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Error in retrieving informations, details follow: " + e.getMessage()));
        }
        for (BookBean bean : books) {
            booksString = booksString.concat(bean.getBookTitle() + "  " + bean.getIsbn() + "  " + bean.getPrice() + "  " + "Negotiable: " + bean.getNegotiable() + "  " + "Put on sale on: " + bean.getTimeStamp() + "\n");
        }
        return booksString;
    }
}
