package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.oracle_interface.OracleSellBookGC;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UploadImageView {
    private final OracleSellBookGC controller;
    private final Button leftArrowButton ;
    private final ImageView leftArrowImage ;
    private final Button rightArrowButton ;
    private final ImageView rightArrowImage ;
    private final ImageView bookImage ;

    private final Parent root ;

    public UploadImageView(OracleSellBookGC controller) {

        VBox mainBox = new VBox() ;
        HBox imageLayer = new HBox() ;
        HBox utilityButtons = new HBox() ;

        mainBox.getChildren().addAll(imageLayer, utilityButtons) ;

        this.controller = controller;

        this.leftArrowButton = new Button();
        this.leftArrowButton.setVisible(false);
        this.rightArrowButton = new Button();
        this.rightArrowButton.setVisible(false);
        this.rightArrowImage = new ImageView();
        this.leftArrowImage = new ImageView();
        this.bookImage = new ImageView();
        this.bookImage.setOnMouseClicked(mouseEvent -> this.controller.uploadImage());

        Button confirm = new Button("Confirm");
        confirm.setOnAction(event -> {
            finalizeSellBook();
        });
        Button delete = new Button("Delete");
        delete.setOnAction(event -> this.controller.deleteImage());
        initializeGraphicalElements();

        imageLayer.getChildren().addAll(leftArrowImage, leftArrowButton, bookImage, rightArrowButton, rightArrowImage) ;
        utilityButtons.getChildren().addAll(confirm, delete) ;

        this.root = mainBox ;

    }

    private void initializeGraphicalElements() {
        this.controller.getUploadControls(this) ;
    }

    public Button getLeftArrowButton() {
        return this.leftArrowButton ;
    }

    public Button getRightArrowButton() {
        return this.rightArrowButton ;
    }

    private void finalizeSellBook() {this.controller.goNext();}


    public ImageView getLeftArrowImage() {
        return this.leftArrowImage ;
    }

    public ImageView getRightArrowImage() {
        return this.rightArrowImage ;
    }

    public ImageView getBookImageView() {
        return this.bookImage ;
    }

    public Parent getRoot () {return this.root; }
}
