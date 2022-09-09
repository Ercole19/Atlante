package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.oracle_interface.OracleAverageGC;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class AverageView {

    private OracleAverageGC controller ;
    private Parent root ;
    private TextArea area ;

    public AverageView() {
        this.controller = new OracleAverageGC() ;
        this.root = new AnchorPane() ;
        this.area = new TextArea() ;
        area.setEditable(false);
        ((AnchorPane)this.root).getChildren().add(area) ;
    }

    public Parent getParent() {
        String result = this.controller.getAverageInfos() ;
        this.area.setText(result);
        return this.root ;
    }
}
