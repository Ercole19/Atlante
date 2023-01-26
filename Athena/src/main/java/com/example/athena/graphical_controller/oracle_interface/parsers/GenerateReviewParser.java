package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedTutor;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.OracleGenerateReviewGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class GenerateReviewParser {

    private LabelView view = new LabelView();
    public void parseGenerateReview(List<String> tokens){
        if(tokens.size() != 4) {ParentSubject.getInstance().setCurrentParent(view.prepareParent("Incorrect arguments passed"));}
        else {
            try {
                if (LoggedTutor.getInstance().getEmail().getMail() != null) {
                    new OracleGenerateReviewGC(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3));
                } else {
                    ParentSubject.getInstance().setCurrentParent(view.prepareParent("You must login/signup before writing any command"));
                }
            } catch (LoggedUserException e) {
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("only tutors can generate review codes"));
            }
        }

    }
}
