package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.OracleShowReceivedBidsGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class ShowMyReceivedBidsView {
    public void parsePlacedBids(List<String> commandToken){
        LabelView view = new LabelView();
        if (commandToken.size() != 1) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Correct usage: show#my_received_bids#isbn"));
        }
        else {
            try {
                if (LoggedStudent.getInstance().getEmail().getMail() != null) {
                    OracleShowReceivedBidsGC controller = new OracleShowReceivedBidsGC();
                    controller.getBids(commandToken.get(0));
                }
                else {ParentSubject.getInstance().setCurrentParent(view.prepareParent("Must login before using commands"));}
            }
            catch (LoggedUserException e) {
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("Only students can put books on sale"));
            }
        }
    }
}
