package com.example.athena.graphical_controller;

import javafx.event.ActionEvent;

import java.io.IOException;

public class MainPageController {




    public void onExamsButtonClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "Carriera-view.fxml");

    }

    public void onCalendarButtonClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "CalendarPage.fxml");
    }


    public void onShopButtonClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "bookshop-choose-view.fxml");
    }


    public void onTutorButtonClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "tutorSearchPage.fxml");
    }

    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "LoginPage.fxml");
    }
}