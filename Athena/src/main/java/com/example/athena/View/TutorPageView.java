package com.example.athena.View;
import com.example.athena.View.SceneDecorators.TutorPageComponent;
import javafx.scene.Parent;
import com.example.athena.GraphicalController.SceneSwitcher;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

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
            e.printStackTrace();
        }
        return null;
    }
}
