package com.example.athena.GraphicalController;

import javafx.event.ActionEvent;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class tutorMainPageController {

    HelloApplication Btn = new HelloApplication();


    public void onPersonalPageButtonClick(ActionEvent event) throws IOException {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "tutorEditingPage.fxml");
    }

    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        Btn.logoutBtn(event);
    }

    public void onReviewsClick(ActionEvent event) throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "TutorReviewPageView.fxml");
    }
}
