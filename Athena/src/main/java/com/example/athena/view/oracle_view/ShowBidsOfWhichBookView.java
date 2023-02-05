package com.example.athena.view.oracle_view;

import com.example.athena.beans.BookBean;
import com.example.athena.graphical_controller.oracle_interface.OracleShowReceivedBidsGC;



public class ShowBidsOfWhichBookView extends SelectWhichBookView{
    private OracleShowReceivedBidsGC controller;
    public ShowBidsOfWhichBookView(OracleShowReceivedBidsGC controller) {
        super(controller.getList(), "Show bids");
        this.controller = controller;
    }

    @Override
    protected void buttonFunction(BookBean bean) {
        this.controller.setSelectedBean(bean);
    }
}
