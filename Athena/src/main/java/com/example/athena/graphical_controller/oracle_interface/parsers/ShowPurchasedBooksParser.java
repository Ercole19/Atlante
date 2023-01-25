package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.RecentPurchasesView;
import com.example.athena.view.oracle_view.LabelView;

public class ShowPurchasedBooksParser {

    public void showPurchasesParse() {
        LabelView labelView = new LabelView();
        try{
            if (LoggedStudent.getInstance().getEmail() != null) {
                RecentPurchasesView view = new RecentPurchasesView(1200, 560);
                ParentSubject.getInstance().setCurrentParent(view.getRoot());
            }
            else {
                ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("You must login before writing any command"));
            }
        }catch (
                LoggedUserException e) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Only students can see their purchases"));
        }

    }
}
