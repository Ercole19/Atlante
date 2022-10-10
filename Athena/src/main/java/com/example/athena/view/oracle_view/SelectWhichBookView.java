package com.example.athena.view.oracle_view;

import com.example.athena.beans.normal.BookBean;
import com.example.athena.graphical_controller.oracle_interface.OracleUpdateBookGC;
import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.List;

public class SelectWhichBookView {
    private final Parent root;

    public SelectWhichBookView(OracleUpdateBookGC controller) {
        VBox box = new VBox() ;
        List<BookBean> beans = controller.getList() ;

        box.getChildren().add(LabelBuilder.buildLabel("Which book do you want to edit?")) ;

        for (BookBean bean : beans) {
            Label bookName = LabelBuilder.buildLabel(bean.getBookTitle());
            Label bookPrice = LabelBuilder.buildLabel(bean.getPrice());
            Label bookNegotiability = LabelBuilder.buildLabel(String.format("Negotiable: %b", bean.getNegotiable())) ;
            Label bookSaleDate = LabelBuilder.buildLabel(String.format("Put on sale on: %s", bean.getTimeStamp())) ;

            Button benjamin = new Button("Edit") ;

            benjamin.setOnAction(event -> controller.getSelectedBook(bean));

            HBox entry = new HBox() ;
            entry.setSpacing(20) ;
            entry.getChildren().addAll(bookName, bookPrice, bookNegotiability, bookSaleDate, benjamin) ;
            box.getChildren().add(entry) ;
        }

        this.root = box ;
    }

    public Parent getRoot() {
        return this.root;
    }
 }
