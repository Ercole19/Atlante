package com.example.athena.graphical_controller.blind_interface;

import com.example.athena.graphical_controller.normal_interface.KeyEventHandler;
import com.example.athena.graphical_controller.normal_interface.LoginPageController;
import com.example.athena.view.TextToSpeech;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.speech.AudioException;
import javax.speech.EngineException;


public class LoginPageGC extends LoginPageController implements KeyEventHandler {

    private LoginPageState state = new StateWelcome();

    private Stage stage ;

    public LoginPageGC() {
        super() ;
        this.focusMap.put("emailField", () -> super.emailField.requestFocus());
        this.focusMap.put("passwordField", () -> super.passField.requestFocus()) ;
        this.focusMap.put("loginButton",() -> super.loginButton.requestFocus());
        this.focusMap.put("signUpButton", () -> signUpButton.requestFocus());
        speak("Welcome to athena, press tab to continue");
    }

    public void setState(LoginPageState state) {
        this.state = state ;
    }

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

