package com.example.athena.view.oracle_view;

import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class OnSelectWhatToEditView {

    private final Parent root ;

    private final Button editDesc ;

    private final Button editTime ;

    private final Button editReminder ;

    private final Button editType ;

    private final Button saveChanges ;

    public OnSelectWhatToEditView() {
        VBox finalRoot = new VBox() ;

        finalRoot.getChildren().add(LabelBuilder.buildLabel("Select what you want to edit:")) ;
        editDesc = new Button("Edit Description") ;

        editTime = new Button("Edit Time") ;


        editReminder = new Button("Edit reminder") ;


        editType = new Button("Edit type") ;


        saveChanges = new Button("Save event") ;


        finalRoot.getChildren().addAll(editTime, editType, editReminder, editDesc, saveChanges) ;

        this.root = finalRoot ;
    }

    public Parent getRoot() {
        return this.root ;
    }

    public Button getEditDesc() {
        return editDesc;
    }

    public Button getEditTime() {
        return editTime;
    }

    public Button getEditReminder() {
        return editReminder;
    }

    public Button getEditType() {
        return editType;
    }

    public Button getSaveChanges() {
        return saveChanges;
    }
}
