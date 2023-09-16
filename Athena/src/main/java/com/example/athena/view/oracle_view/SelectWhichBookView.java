package com.example.athena.view.oracle_view;


import com.example.athena.beans.BookBean;
import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;


public abstract class SelectWhichBookView {
    private final Parent root;
    private VBox box;

    protected SelectWhichBookView(List<BookBean> beans, String buttonDesc) {
        box = new VBox() ;
        box.getChildren().add(LabelBuilder.buildLabel("Choose one book"));
        for (BookBean bean : beans) {
            Label bookName = LabelBuilder.buildLabel(bean.getBookTitle());
            Label bookPrice = LabelBuilder.buildLabel(bean.getPrice());
            Label bookNegotiability = LabelBuilder.buildLabel(String.format("Negotiable: %b", bean.getNegotiable())) ;
            Label bookSaleDate = LabelBuilder.buildLabel(String.format("Put on sale on: %s", bean.getTimeStamp())) ;

            Button benjamin = new Button(buttonDesc) ;
            benjamin.setOnAction(event -> buttonFunction(bean));

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
    
    protected abstract void buttonFunction(BookBean bean);
 }