package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

public class ShowMySellingBooksParser {
    private LabelView view = new LabelView();
    public void parseShowMySellingBooks(){
        try{
            if (LoggedStudent.getInstance().getEmail() != null){
                SceneSwitcher switcher = SceneSwitcher.getInstance();
                ParentSubject.getInstance().setCurrentParent(switcher.preload("OracleSellingView.fxml"));
            }
            else{
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("You must login/signup before writing any command"));
            }
        } catch (LoggedUserException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("only students can see their selling books"));
        }

    }
}
