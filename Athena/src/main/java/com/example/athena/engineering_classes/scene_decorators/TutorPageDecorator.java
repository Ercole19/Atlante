package com.example.athena.engineering_classes.scene_decorators;

import javafx.scene.Parent;

public abstract class TutorPageDecorator extends TutorPageComponent {

    private TutorPageComponent component ;

    protected TutorPageDecorator(TutorPageComponent component)
    {
        this.component = component ;
    }

    @Override
    public Parent build()
    {
        return this.component.build();
    }
}
