package com.example.athena.View.SceneDecorators;

import javafx.scene.Parent;

public abstract class TutorPageDecorator extends TutorPageComponent {

    private TutorPageComponent component ;

    public  TutorPageDecorator(TutorPageComponent component)
    {
        this.component = component ;
    }

    @Override
    public Parent build()
    {
        return this.component.build();
    }
}
