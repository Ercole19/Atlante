package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.entities.User;
import com.example.athena.view.TutorPageView;
import com.example.athena.engineering_classes.scene_decorators.TutorPageButtonAdder;
import com.example.athena.engineering_classes.scene_decorators.TutorPageComponent;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TutorMainPageController {

    private final SceneSwitcher switcher = SceneSwitcher.getInstance() ;

    public void onPersonalPageButtonClick(ActionEvent event)  {
        TutorPageComponent component = new TutorPageButtonAdder(new TutorPageView()) ;
        Stage stage = switcher.getTopStage() ;
        Scene scene = new Scene(component.build());
        stage.setScene(scene);
    }

    public void onLogoutButtonClick(ActionEvent event)  {
        User.logout();
        switcher.switcher("LoginPage.fxml");
    }

    public void onReviewsClick(ActionEvent event)
    {
        switcher.switcher("TutorReviewPageView.fxml");
    }


}
