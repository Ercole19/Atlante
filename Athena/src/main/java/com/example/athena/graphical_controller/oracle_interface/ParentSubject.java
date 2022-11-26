package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.engineering_classes.observer_pattern.AbstractSubject;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class ParentSubject extends AbstractSubject {
     private Parent parent ;
     private static ParentSubject instance;

    private ParentSubject() {
        this.parent = new VBox() ;
    }

    public static synchronized ParentSubject getInstance()
    {
        if (instance == null) {
            instance = new ParentSubject();
        }
        return instance;
    }

    public Parent getParent() {
        return this.parent;
    }

    public void setCurrentParent(Parent scene) {
        this.parent = scene;
        super.notifyObserver();
    }
}
