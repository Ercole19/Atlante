package com.example.athena.graphical_controller.blind_interface;

import com.example.athena.graphical_controller.normal_interface.KeyEventHandler;
import com.example.athena.graphical_controller.normal_interface.LoginPageController;
import com.example.athena.view.TextToSpeech;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

import javax.speech.AudioException;
import javax.speech.EngineException;


public class LoginPageGC extends LoginPageController implements KeyEventHandler {

    @Override
    @FXML
    public void handlerKeyEvent(KeyEvent event) {
        String character = event.getCharacter();
        if (character.equals("\t")) {
            try {
                TextToSpeech.getInstance().speak("enter email");
            } catch (AudioException | EngineException exc) {
                exc.printStackTrace();
            }
        }
    }
}

