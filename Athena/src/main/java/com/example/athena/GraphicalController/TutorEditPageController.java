package com.example.athena.GraphicalController;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class TutorEditPageController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "LoginPage.fxml");
    }

    public void onConfirmButtonClick(ActionEvent event) throws IOException {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "MainPageTutor.fxml");
    }

    public void onCVButtonClick(ActionEvent event) throws IOException
    {
        AnchorPane root = new AnchorPane() ;
        root.setPrefSize(600, 800) ;
        Label CV = new Label("Lorem Ipsum Dolor Sit Amet") ;
        root.getChildren().add(CV) ;
        Scene CVScene = new Scene(root) ;
        Stage tempStage = new Stage() ;
        tempStage.setScene(CVScene) ;
        tempStage.initModality(Modality.APPLICATION_MODAL) ;
        tempStage.setResizable(false) ;
        tempStage.setTitle("CV") ;
        tempStage.showAndWait() ;
    }

    public void clickOnBackButton(ActionEvent event) throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "MainPageTutor.fxml");
    }
}
