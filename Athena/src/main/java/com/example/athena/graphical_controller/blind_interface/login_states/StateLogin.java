package com.example.athena.graphical_controller.blind_interface.login_states;

import com.example.athena.graphical_controller.blind_interface.LoginPageGC;

public class StateLogin implements LoginPageState {

    @Override
    public void goNext(LoginPageGC stateMachine, String symbol) {
        if(symbol.equals("\t")) {
            stateMachine.stateChangeFocus("signUpButton") ;
            stateMachine.speak("Press enter to sign up") ;
            stateMachine.setState(new StateSignUp());
        } else if(symbol.equals("\n")) {
            stateMachine.switchToMainPage();
        }
    }
}
