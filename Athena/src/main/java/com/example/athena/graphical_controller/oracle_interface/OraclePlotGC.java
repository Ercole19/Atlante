package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.view.oracle_view.OraclePlotView;

import java.util.List;

public class OraclePlotGC {

    private OraclePlotView view ;
    private List<String> tokens ;

    public OraclePlotGC(List<String> tokens) {
        this.tokens = tokens ;
        this.view = new OraclePlotView(this) ;
    }

    public int getPlotNumber() {
        return 3 ;
    }

    public int getTimeEntries() {
        return 19 ;
    }

}
