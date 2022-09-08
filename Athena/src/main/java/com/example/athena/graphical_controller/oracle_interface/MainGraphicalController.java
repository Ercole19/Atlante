package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.engineering_classes.observer_pattern.AbstractObserver;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class MainGraphicalController implements AbstractObserver, Initializable {

    @FXML
    TextField commandField;
    @FXML
    SubScene subscene;

    private final CommandParser parser = new CommandParser();

    public void parseCommand() {
        String command = commandField.getText();
        parser.parseCommand(command);
    }

    @Override
    public void update() {
        this.subscene.setRoot(ParentSubject.getInstance().getParent());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ParentSubject.getInstance().attachObserver(this);
    }
}
