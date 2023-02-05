package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.normal_interface.ShiftImageController;
import com.example.athena.graphical_controller.normal_interface.UpdatedShiftImageController;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public abstract class UpdatedShiftImageView<T extends UpdatedShiftImageController> extends ShiftImageView<T> {
    protected final T updatedController;

    protected UpdatedShiftImageView(T controller) {

        super(controller);
        this.updatedController = controller;
        HBox utilityButtons = new HBox() ;

        mainBox.getChildren().add(utilityButtons) ;

        super.bookImage.setOnMouseClicked(mouseEvent -> this.updatedController.onUploadBtnClick());

        Button confirm = new Button("Confirm");
        confirm.setOnAction(event -> finalizeCollection());
        Button delete = new Button("Delete");
        delete.setOnAction(event -> this.updatedController.deleteImageOnScreen());

        utilityButtons.getChildren().addAll(confirm, delete) ;
    }
    protected abstract void finalizeCollection() ;
}

