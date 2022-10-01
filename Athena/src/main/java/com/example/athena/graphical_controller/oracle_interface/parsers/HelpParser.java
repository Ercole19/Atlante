package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.HelpView;
import com.example.athena.view.oracle_view.LabelView;

public class HelpParser {

    public void parseHelp() {
        ParentSubject.getInstance().setCurrentParent(new HelpView().getParent());
    }
}
