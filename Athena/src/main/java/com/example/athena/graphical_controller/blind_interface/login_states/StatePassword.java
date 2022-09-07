package com.example.athena.graphical_controller.blind_interface.login_states;

import com.example.athena.graphical_controller.blind_interface.LoginPageGC;

public class StatePassword implements LoginPageState {
    @Override
    public void goNext(LoginPageGC stateMachine, String symbol) {
        if(symbol.equals("\t")) {
            stateMachine.stateChangeFocus("loginButton") ;
            stateMachine.speak("press enter to login") ;
            stateMachine.setState(new StateLogin());
        }
    }
}
