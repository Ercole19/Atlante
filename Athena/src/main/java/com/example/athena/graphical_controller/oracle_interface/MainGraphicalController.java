package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.engineering_classes.observer_pattern.AbstractObserver;
import com.example.athena.exceptions.ExceededLoginsException;
import com.example.athena.graphical_controller.oracle_interface.parsers.CommandParser;
import com.example.athena.view.oracle_view.LabelView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class MainGraphicalController implements AbstractObserver, Initializable{

    @FXML
    TextField commandField;
    @FXML
    SubScene subscene;
    @FXML
    Button enterButton;

    private int totalAttempts = 0 ;
    private int waitTimeMultiplier = 1;

    private final CommandParser parser = new CommandParser();

    public void parseCommand() {

        if (commandField.getText().equals("")) {
            ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Insert something before"));
        }
        else {
            try {
                String command = commandField.getText();
                parser.parseCommand(command);
            } catch (ExceededLoginsException e) {
                if (totalAttempts < 5) {
                    totalAttempts++;
                } else {
                    new Thread(() -> {
                        Platform.runLater(() -> enterButton.setDisable(true));
                        try {
                            Thread.sleep(10000L * waitTimeMultiplier);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        Platform.runLater(() -> enterButton.setDisable(false));
                    }).start();
                    waitTimeMultiplier++;
                    totalAttempts = 0;
                }

            }
        }

        commandField.requestFocus();
    }

    @Override
    public void update() {
        this.subscene.setRoot(ParentSubject.getInstance().getParent());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ParentSubject.getInstance().attachObserver(this);
    }

    @FXML
    public void onKeyTyped(KeyEvent event) {
        if(Objects.equals(event.getCharacter(), "\r")) {
            enterButton.requestFocus();
            parseCommand();
        }
    }

}