package com.example.athena.graphical_controller.blind_interface.login_states;

import com.example.athena.graphical_controller.blind_interface.LoginPageGC;

public class StateWelcome implements LoginPageState {
    @Override
    public void goNext(LoginPageGC stateMachine, String symbol) {
        if(symbol.equals("\t")) {
            stateMachine.stateChangeFocus("emailField") ;
            stateMachine.speak("enter email") ;
            stateMachine.setState(new StateEmail()) ;
        }
    }
}
