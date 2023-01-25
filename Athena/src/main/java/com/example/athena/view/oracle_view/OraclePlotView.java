package com.example.athena.view.oracle_view;

import com.example.athena.exceptions.PercentFormatException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.exceptions.WrongIndexNumberException;
import com.example.athena.graphical_controller.oracle_interface.OraclePlotGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class OraclePlotView {

    public OraclePlotView(OraclePlotGC controller) {

        int plotNum = controller.getPlotNumber() ;
        int timeEntries = controller.getTimeEntries() ;

        TableFormatBundle bundle = new TableFormatBundle() ;
        bundle.setWidth(220*(plotNum+1));
        bundle.setHeight(50*(timeEntries+2));
        bundle.setRows(plotNum +1);
        bundle.setCols(timeEntries +2);
        bundle.setContainerWidth(1021);
        bundle.setContainerHeight(561);
        double[] rowPercents = new double[plotNum +1] ;
        for (int i = 0 ; i < plotNum +1 ; i++) {
            rowPercents[i] = 100.0/(plotNum+1) ;
        }

        double[] colPercents = new double[timeEntries +2] ;
        for (int i = 0 ; i < timeEntries +2 ; i++) {
            colPercents[i] = 100.0/(timeEntries +2) ;
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

        controller.setPlotData(view) ;

        ParentSubject.getInstance().setCurrentParent(view.getPrettyRoot());
    }
}
