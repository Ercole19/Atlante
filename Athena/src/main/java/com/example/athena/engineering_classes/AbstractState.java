package com.example.athena.engineering_classes;

public interface AbstractState<M, I> {
    void goNext(M stateMachine, I input) ;
}
