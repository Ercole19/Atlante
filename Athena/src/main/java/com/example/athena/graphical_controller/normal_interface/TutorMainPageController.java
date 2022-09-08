package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.use_case_controllers.LoginUseCaseController;
import com.example.athena.view.TutorPageView;
import com.example.athena.engineering_classes.scene_decorators.TutorPageButtonAdder;
import com.example.athena.engineering_classes.scene_decorators.TutorPageComponent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TutorMainPageController {

    private final SceneSwitcher switcher = SceneSwitcher.getInstance() ;

    public void onPersonalPageButtonClick()  {
        TutorPageComponent component = new TutorPageButtonAdder(new TutorPageView()) ;
        Stage stage = switcher.getTopStage() ;
        Scene scene = new Scene(component.build());
        stage.setScene(scene);
    }

    public void onLogoutButtonClick()  {
        new LoginUseCaseController().logout();
        switcher.switcher("LoginPage.fxml");
    }

    public void onReviewsClick()
    {
        switcher.switcher("TutorReviewPageView.fxml");
    }


}
