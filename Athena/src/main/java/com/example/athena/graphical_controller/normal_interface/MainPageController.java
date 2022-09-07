package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.entities.User;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;



public class MainPageController {

    private final SceneSwitcher switcher = new SceneSwitcher();
    private Stage stage;

    public void onExamsButtonClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "ExamHomepageView.fxml");

    }

    public void onCalendarButtonClick(ActionEvent event)  {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "CalendarPage.fxml");
    }


    public void onShopButtonClick(ActionEvent event)  {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "bookshop-choose-view.fxml");
    }


    public void onTutorButtonClick(ActionEvent event)  {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "tutorSearchPage.fxml");
    }

    public void onLogoutButtonClick(ActionEvent event) {
        User.logout();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "LoginPage.fxml");
    }
}