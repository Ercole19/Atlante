package com.example.athena.engineering_classes;

public interface AbstractState<T> {

    void goNext(T contextStateMachine) ;
}
