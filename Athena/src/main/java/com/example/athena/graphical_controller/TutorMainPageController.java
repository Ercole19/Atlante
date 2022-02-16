package com.example.athena.graphical_controller;

import com.example.athena.view.TutorPageView;
import com.example.athena.view.scene_decorators.TutorPageButtonAdder;
import com.example.athena.view.scene_decorators.TutorPageComponent;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TutorMainPageController {

    SceneSwitcher switcher = new SceneSwitcher();


    public void onPersonalPageButtonClick(ActionEvent event)  {
        TutorPageComponent component = new TutorPageButtonAdder(new TutorPageView()) ;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(component.build());
        stage.setScene(scene);
    }

    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "LoginPage.fxml");
    }

    public void onReviewsClick(ActionEvent event) throws IOException
    {
        switcher.switcher(event, "TutorReviewPageView.fxml");
    }


}
