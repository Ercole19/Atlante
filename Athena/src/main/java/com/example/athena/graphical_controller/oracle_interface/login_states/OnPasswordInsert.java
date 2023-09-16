package com.example.athena.graphical_controller.oracle_interface.login_states;

import com.example.athena.graphical_controller.oracle_interface.OracleInsertPasswordViewGC;
import com.example.athena.graphical_controller.oracle_interface.OracleLoginGC;

public class OnPasswordInsert implements LoginAbstractState {

    public OnPasswordInsert(OracleLoginGC controller) {
        new OracleInsertPasswordViewGC(controller) ;
    }
    @Override
    public void goNext(LoginSM contextStateMachine) {
        contextStateMachine.setState(new OnLoginCheck(contextStateMachine.getController())) ;
    }
}
