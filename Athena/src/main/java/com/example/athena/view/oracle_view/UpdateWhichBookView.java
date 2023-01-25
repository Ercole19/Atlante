package com.example.athena.view.oracle_view;

import com.example.athena.beans.BookBean;
import com.example.athena.graphical_controller.oracle_interface.OracleUpdateBookGC;

public class UpdateWhichBookView extends SelectWhichBookView{
    private OracleUpdateBookGC oracleUpdateBookGC;

    public UpdateWhichBookView(OracleUpdateBookGC controller) {
        super(controller.getList(), "Update");
        this.oracleUpdateBookGC = controller;
    }


    @Override
    protected void buttonFunction(BookBean bean) {
        this.oracleUpdateBookGC.getSelectedBook(bean);
    }
}
