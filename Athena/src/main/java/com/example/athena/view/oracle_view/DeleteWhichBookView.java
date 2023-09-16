package com.example.athena.view.oracle_view;

import com.example.athena.beans.BookBean;
import com.example.athena.graphical_controller.oracle_interface.OracleDeleteBookGC;

public class DeleteWhichBookView extends SelectWhichBookView{
    private final OracleDeleteBookGC controller;

    public DeleteWhichBookView(OracleDeleteBookGC controller) {
        super(controller.getList(), "Delete");
        this.controller = controller;
    }


    @Override
    protected void buttonFunction(BookBean bean) {
        this.controller.deleteBookUseCase(bean);
    }
}
