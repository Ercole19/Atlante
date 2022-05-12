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
        try
        {
            return load((new SceneSwitcher()).generateUrl("tutorPersonalPage.fxml")) ;
        }
        catch(IOException e)
        {
            Logger logger = Logger.getLogger(IsbnCheckBoundary.class);
            logger.error("error!", e);
        }
        return null;
    }
}
