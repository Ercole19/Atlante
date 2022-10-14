package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.oracle.HelpBean;
import com.example.athena.exceptions.HelpDaoException;
import com.example.athena.use_case_controllers.OracleHelpUcc;
import com.example.athena.view.oracle_view.LabelView;
import java.net.URISyntaxException;


public class OracleHelpGC {

    public String getHelpFileString() {
        OracleHelpUcc controller = new OracleHelpUcc();
        String commands = null;
        try {
           HelpBean bean = controller.getString();
           commands = bean.getHelpCommands();
        }catch (HelpDaoException e) {
            ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent(e.getMessage()));
        }
        return commands;
    }
}