package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.oracle_interface.OracleTakenExamsGC;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class TakenExams {

    private OracleTakenExamsGC controller ;
    private Parent root ;
    private TextArea area ;

    public TakenExams() {
        this.controller = new OracleTakenExamsGC() ;
        this.root = new AnchorPane() ;
        this.area = new TextArea() ;
        area.setEditable(false);
        ((AnchorPane)this.root).getChildren().add(area) ;
    }

    public Parent getParent() {
        String result = this.controller.getExams();
        this.area.setText(result);
        return this.root ;
    }

}
