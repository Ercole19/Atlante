package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.oracle_interface.OracleSellBookGC;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;

public class UploadImageView extends ShiftImageView<OracleSellBookGC>{

    public UploadImageView(OracleSellBookGC controller) {
        super(controller) ;
    }

    @Override
    protected void initializeGraphicalElements() {
        this.controller.getUploadControls(this) ;
    }

    @Override
    protected void finalizeCollection() {this.controller.goNext();}

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