package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.oracle_interface.OracleSellBookGC;
import javafx.scene.Parent;

public class UploadImageView extends UpdatedShiftImageView<OracleSellBookGC>{

    public UploadImageView(OracleSellBookGC controller) {
        super(controller) ;
        initializeGraphicalElements();
    }

    @Override
    protected void initializeGraphicalElements() {
        this.updatedController.getUploadControls(this) ;
    }

    @Override
    protected void finalizeCollection() {this.updatedController.goNext();}



    public Parent getRoot () {return this.root; }
}