package com.example.athena.engineering_classes.observer_pattern;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSubject {

    private final List<AbstractObserver> observers = new ArrayList<>() ;

    public void notifyObserver(){
        for (AbstractObserver observer : observers){
            observer.update();
        }
    }

    public void attachObserver(AbstractObserver abstractObserver){
        observers.add(abstractObserver);
        this.notifyObserver();
    }

    public void detachObserver(AbstractObserver abstractObserver){
        observers.remove(abstractObserver);
    }
}
