package com.example.athena.graphical_controller.oracle_interface.login_states;

import com.example.athena.graphical_controller.oracle_interface.OracleLoginGC;

public class OnLoginCheck implements LoginAbstractState {

    public OnLoginCheck(OracleLoginGC controller) {
        controller.createParent();
    }
    @Override
    public void goNext(LoginSM contextStateMachine) {
        throw new UnsupportedOperationException() ;
    }
}
