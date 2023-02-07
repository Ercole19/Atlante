package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.oracle_interface.OracleMySellingBooksGC;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class MySellingBooksView {
    private OracleMySellingBooksGC controller ;
    private Parent root ;
    private TextArea area ;

    public MySellingBooksView() {
        this.controller = new OracleMySellingBooksGC();
        this.root = new AnchorPane() ;
        this.area = new TextArea() ;
        this.area.appendText("TITLE       ISBN        PRICE        NEGOTIABLE           SALE TIMESTAMP" +  '\n');
        area.setEditable(false);
        ((AnchorPane)this.root).getChildren().add(area) ;
    }

    public Parent getParent() {
        String result = this.controller.getMyBooks();
        this.area.appendText(result);
        return this.root ;
    }
}
