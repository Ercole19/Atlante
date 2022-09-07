package com.example.athena.graphical_controller.blind_interface;

import com.example.athena.graphical_controller.blind_interface.login_states.LoginPageState;
import com.example.athena.graphical_controller.blind_interface.login_states.StateWelcome;
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
import java.util.HashMap;
import java.util.Map;

public class LoginPageGC extends LoginPageController implements KeyEventHandler {

    private Map<String, Runnable> focusMap = new HashMap<>();

    @FXML
    Button signUpButton;

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
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        String character = event.getCharacter();
        stateGoNext(character) ;
    }

    public void stateGoNext(String key) {
        state.goNext(this, key) ;
    }

    public void stateChangeFocus(String key) {
        Runnable action = this.focusMap.get(key) ;
        action.run() ;
    }

    public void speak(String text) {
        try {
            TextToSpeech.getInstance().speak(text);
        } catch (EngineException | AudioException e) {
            System.exit(1) ;
        }
    }
}

