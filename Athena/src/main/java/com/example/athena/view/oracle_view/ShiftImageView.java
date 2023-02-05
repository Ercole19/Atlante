package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.normal_interface.ShiftImageController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class ShiftImageView<T extends ShiftImageController> {
    protected final T shiftController;
    protected final ImageView leftArrowImage ;
    protected final ImageView rightArrowImage ;
    protected final ImageView bookImage ;
    protected VBox mainBox;
    protected HBox imageLayer;

    protected Parent root ;

    protected ShiftImageView(T controller) {

        mainBox = new VBox() ;
        imageLayer = new HBox() ;
        double w = 82;
        double h = 63;

        mainBox.getChildren().add(imageLayer) ;
        this.shiftController = controller;

        Image arrowImage = new Image(getClass().getResourceAsStream("/assets/backBtn.png"));

        this.rightArrowImage = new ImageView();
        this.rightArrowImage.setImage(arrowImage);
        this.rightArrowImage.setFitWidth(w);
        this.rightArrowImage.setFitHeight(h);
        this.rightArrowImage.setRotate(178.3);
        this.rightArrowImage.setOnMouseClicked(mouseEvent -> this.shiftController.rightArrowClick());

        this.leftArrowImage = new ImageView();
        this.leftArrowImage.setImage(arrowImage);
        this.leftArrowImage.setFitHeight(h);
        this.leftArrowImage.setFitWidth(w);
        this.leftArrowImage.setOnMouseClicked(mouseEvent -> this.shiftController.leftArrowClick());

        this.bookImage = new ImageView();
        this.bookImage.setImage(new Image(getClass().getResourceAsStream("/assets/NoImage.png")));
        this.bookImage.setFitWidth(314);
        this.bookImage.setFitHeight(241);

        imageLayer.getChildren().addAll(leftArrowImage, bookImage, rightArrowImage) ;
        HBox.setMargin(rightArrowImage, new Insets(100, 0, 0 ,0)) ;
        HBox.setMargin(leftArrowImage, new Insets(100, 0, 0 ,0)) ;

        this.root = mainBox ;
    }


    protected abstract void initializeGraphicalElements() ;
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