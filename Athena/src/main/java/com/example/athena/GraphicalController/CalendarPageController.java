package com.example.athena.GraphicalController;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class CalendarPageController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    public void clickOnBackButton(ActionEvent event) throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "MainPageStudents.fxml");
    }

    public void clickOnAddEvent(ActionEvent event) throws IOException
    {
        root = load(Objects.requireNonNull(getClass().getResource("AddEventScreen.fxml"))) ;
        stage = new Stage() ;
        stage.initModality(Modality.APPLICATION_MODAL) ;
        stage.setResizable(false) ;
        scene = new Scene(root) ;
        stage.setTitle("Add a new event") ;
        stage.setScene(scene) ;
        stage.showAndWait() ;
    }

    public void clickOnPlots(ActionEvent event) throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "PlotPage.fxml");
    }
}
