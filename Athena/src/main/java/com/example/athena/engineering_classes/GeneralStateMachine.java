package com.example.athena.engineering_classes;

public abstract class GeneralStateMachine<T extends AbstractState<GeneralStateMachine, K>, K> {

    protected T state ;

    public void setState(T state) {
        this.state = state ;
    }

    public void goNext(K input) {
        state.goNext(this, input);
    }
}
