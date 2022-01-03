package com.example.athena.GraphicalController;

import javafx.event.ActionEvent;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class tutorMainPageController {

    SceneSwitcher switcher = new SceneSwitcher();


    public void onPersonalPageButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "tutorEditingPage.fxml");
    }

    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "LoginPage.fxml");
    }

    public void onReviewsClick(ActionEvent event) throws IOException
    {
        switcher.switcher(event, "TutorReviewPageView.fxml");
    }
}
