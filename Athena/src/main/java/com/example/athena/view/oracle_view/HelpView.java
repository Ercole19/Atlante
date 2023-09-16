package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.oracle_interface.OracleHelpGC;
import javafx.scene.Parent;

public class HelpView {

    private Parent root ;

    public HelpView(OracleHelpGC controller) {
        LabelView view = new LabelView();
        String helpCommands = controller.getHelpFileString();
        root = view.prepareParent(helpCommands);
    }

    public Parent getParent() {
        return this.root ;
    }
}