package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import javafx.scene.Parent;

public class HelpView {

    private Parent root ;

    public HelpView() {
        LabelView view = new LabelView();
        root = view.prepareParent("commands list:\n" +
                "update_exam#[oldname#olddate#oldgrade#oldcfu#newName#newDate#newGrade#newCfu]\n" +
                "login#[username#password]\n" +
                "signup#[name#surname#email#password]\n" +
                "validate_signup#[code]\n" +
                "add_event\n" +
                "show#my_selling_books\n" +
                "\tplots\n" +
                "\texams_completion\n" +
                "\tcfu_completion\n" +
                "\ttaken_exams\n" +
                "\taverages\n" +
                "\ttutors#[Type#name#ordered]\n" +
                "\tbooks#[name/isbn]\n" +
                "\tsold_books\n" +
                "\tpurchased_books\n" +
                "\tmy_courses\n" +
                "\tmy_infos\n" +
                "\tmy_cv\n" +
                "generate_review\n" +
                "review#[code]\n" +
                "sell_book\n" +
                "add_course#[name]\n" +
                "add_cv#[pathname]\n" +
                "set_maxCfus#[newmax]\n" +
                "set_maxExams#[newmax]\n" +
                "delete_course#[name]\n" +
                "add_exam#[name#cfu#grade#date]\n" +
                "delete_exam#[name#date#grade#cfu]");
    }

    public Parent getParent() {
        return this.root ;
    }
}
