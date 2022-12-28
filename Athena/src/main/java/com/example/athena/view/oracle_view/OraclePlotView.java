package com.example.athena.view.oracle_view;

import com.example.athena.exceptions.PercentFormatException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.exceptions.WrongIndexNumberException;
import com.example.athena.graphical_controller.oracle_interface.OraclePlotGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.LabelBuilder;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class OraclePlotView {

    public OraclePlotView(OraclePlotGC controller) {

        int plotNum = controller.getPlotNumber() ;
        int timeEntries = controller.getTimeEntries() ;

        TableFormatBundle bundle = new TableFormatBundle() ;
        bundle.setWidth(2042);
        bundle.setHeight(1122);
        bundle.setCols(timeEntries +1);
        bundle.setRows(plotNum +1);
        bundle.setContainerWidth(1021);
        bundle.setContainerHeight(561);
        int[] rowPercents = new int[plotNum +1] ;
        for (int i = 0 ; i < plotNum +1 ; i++) {
            rowPercents[i] = 100/(plotNum+1) ;
        }

        int[] colPercents = new int[timeEntries +1] ;
        for (int i = 0 ; i < timeEntries +1 ; i++) {
            colPercents[i] = 100/(timeEntries +1) ;
        }

        try {
            bundle.setHorizontalPercents(colPercents);
            bundle.setVerticalPercents(rowPercents);
        } catch (WrongIndexNumberException | PercentFormatException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Error in building table. Please try reinstalling the application", ButtonType.OK) ;
            alert.showAndWait() ;
            System.exit(-1);
        }

        TableView view = new TableView(bundle) ;

        view.setGridPaneEntry(1,0, LabelBuilder.buildLabel("SS"));
        view.setGridPaneEntry(2,0, LabelBuilder.buildLabel("LT"));
        view.setGridPaneEntry(3,0, LabelBuilder.buildLabel("OT"));
        view.setGridPaneEntry(0,1, LabelBuilder.buildLabel("3"));
        view.setGridPaneEntry(0,2, LabelBuilder.buildLabel("3"));
        view.setGridPaneEntry(0,3, LabelBuilder.buildLabel("3"));
        view.setGridPaneEntry(0,4, LabelBuilder.buildLabel("3"));

        ParentSubject.getInstance().setCurrentParent(view.getPrettyRoot());
    }
}
