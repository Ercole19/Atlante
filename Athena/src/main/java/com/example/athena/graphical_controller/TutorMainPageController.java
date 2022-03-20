package com.example.athena.graphical_controller;

import com.example.athena.view.TutorPageView;
import com.example.athena.engineering_classes.scene_decorators.TutorPageButtonAdder;
import com.example.athena.engineering_classes.scene_decorators.TutorPageComponent;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TutorMainPageController {

    private final SceneSwitcher switcher = new SceneSwitcher();
    private Stage stage;

    public void onPersonalPageButtonClick(ActionEvent event)  {
        TutorPageComponent component = new TutorPageButtonAdder(new TutorPageView()) ;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(component.build());
        stage.setScene(scene);
    }

    public void onLogoutButtonClick(ActionEvent event)  {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        switcher.switcher(stage, "LoginPage.fxml");
    }

    public void onReviewsClick(ActionEvent event)
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "TutorReviewPageView.fxml");
    }


}
