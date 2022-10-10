package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.normal_interface.UpdatedShiftImageController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class ShiftImageView<T extends UpdatedShiftImageController> {
    protected final T controller;
    protected final ImageView leftArrowImage ;
    protected final ImageView rightArrowImage ;
    protected final ImageView bookImage ;

    protected Parent root ;

    public ShiftImageView(T controller) {

        VBox mainBox = new VBox() ;
        HBox imageLayer = new HBox() ;
        HBox utilityButtons = new HBox() ;
        double w = 82;
        double h = 63;

        mainBox.getChildren().addAll(imageLayer, utilityButtons) ;
        this.controller = controller;

        Image arrowImage = new Image(getClass().getResourceAsStream("/assets/backBtn.png"));

        this.rightArrowImage = new ImageView();
        this.rightArrowImage.setImage(arrowImage);
        this.rightArrowImage.setFitWidth(w);
        this.rightArrowImage.setFitHeight(h);
        this.rightArrowImage.setRotate(178.3);
        this.rightArrowImage.setOnMouseClicked(mouseEvent -> this.controller.rightArrowClick());

        this.leftArrowImage = new ImageView();
        this.leftArrowImage.setImage(arrowImage);
        this.leftArrowImage.setFitHeight(h);
        this.leftArrowImage.setFitWidth(w);
        this.leftArrowImage.setOnMouseClicked(mouseEvent -> this.controller.leftArrowClick());

        this.bookImage = new ImageView();
        this.bookImage.setImage(new Image(getClass().getResourceAsStream("/assets/NoImage.png")));
        this.bookImage.setOnMouseClicked(mouseEvent -> this.controller.onUploadBtnClick());
        this.bookImage.setFitWidth(314);
        this.bookImage.setFitHeight(241);

        Button confirm = new Button("Confirm");
        confirm.setOnAction(event -> {
            finalizeCollection();
        });
        Button delete = new Button("Delete");
        delete.setOnAction(event -> this.controller.deleteImageOnScreen());
        initializeGraphicalElements();

        imageLayer.getChildren().addAll(leftArrowImage, bookImage, rightArrowImage) ;
        HBox.setMargin(rightArrowImage, new Insets(100, 0, 0 ,0)) ;
        HBox.setMargin(leftArrowImage, new Insets(100, 0, 0 ,0)) ;

        utilityButtons.getChildren().addAll(confirm, delete) ;

        this.root = mainBox ;

    }

    protected abstract void finalizeCollection() ;

    protected abstract void initializeGraphicalElements() ;

    public Parent getRoot () {return this.root; }
}

