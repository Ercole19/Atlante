package com.example.athena.view;

import com.example.athena.boundaries.IsbnCheckBoundary;
import com.example.athena.graphical_controller.SceneSwitcher;
import com.example.athena.engineering_classes.scene_decorators.TutorPageComponent;
import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.List;


public class TutorPageView extends TutorPageComponent
{
    @Override
    public Parent build()
    {
        SceneSwitcher switcher = new SceneSwitcher();
        List<Object> params = new ArrayList<>() ;
        params.add(Tutor.getInstance().getEmail()) ;
        params.add(false) ;
        return switcher.preload("tutorPersonalPage.fxml", params) ;
    }
}
