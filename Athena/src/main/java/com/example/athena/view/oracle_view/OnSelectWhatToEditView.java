package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.oracle_interface.OnSelectWhatToEditGC;
import com.example.athena.graphical_controller.oracle_interface.edit_event_states.EditStatesEnum;
import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class OnSelectWhatToEditView {

    private final Parent root ;

    public OnSelectWhatToEditView(OnSelectWhatToEditGC controller) {
        VBox finalRoot = new VBox() ;

        finalRoot.getChildren().add(LabelBuilder.buildLabel("Select what you want to edit:")) ;
        Button editDesc = new Button("Edit Description") ;
        editDesc.setOnAction(event -> {
            controller.setChoice(EditStatesEnum.EDIT_DESC) ;
        });

        Button editTime = new Button("Edit Time") ;
        editTime.setOnAction(event -> controller.setChoice(EditStatesEnum.EDIT_TIME));

        Button editReminder = new Button("Edit reminder") ;
        editReminder.setOnAction(event -> controller.setChoice(EditStatesEnum.EDIT_REMINDER));

        Button editType = new Button("Edit type") ;
        editType.setOnAction(event -> controller.setChoice(EditStatesEnum.EDIT_TYPE));

        Button saveChanges = new Button("Save event") ;
        saveChanges.setOnAction(event -> controller.setChoice(EditStatesEnum.SAVE_EVENT)) ;

        finalRoot.getChildren().addAll(editTime, editType, editReminder, editDesc, saveChanges) ;

        this.root = finalRoot ;
    }

    public Parent getRoot() {
        return this.root ;
    }
}
