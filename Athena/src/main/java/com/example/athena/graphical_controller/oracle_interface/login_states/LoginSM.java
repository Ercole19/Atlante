package com.example.athena.graphical_controller.oracle_interface.login_states;

import com.example.athena.graphical_controller.oracle_interface.OracleLoginGC;

public class LoginSM {

    private final OracleLoginGC controller ;

    private LoginAbstractState current ;

    public LoginSM(OracleLoginGC controller, LoginAbstractState startState) {
        this.controller = controller ;
        this.current = startState ;
    }

    public void setState(LoginAbstractState state) {
        this.current = state;
    }

    public OracleLoginGC getController() {
        return controller;
    }

    public void goNext() {
        current.goNext(this) ;
    }
}
