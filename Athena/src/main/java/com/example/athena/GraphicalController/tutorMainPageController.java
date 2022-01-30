package com.example.athena.GraphicalController;

import com.example.athena.View.SceneDecorators.TutorPageButtonAdder;
import com.example.athena.View.SceneDecorators.TutorPageComponent;
import com.example.athena.View.TutorPageView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class tutorMainPageController {

    SceneSwitcher switcher = new SceneSwitcher();


    public void onPersonalPageButtonClick(ActionEvent event) throws IOException {
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
