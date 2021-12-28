package com.example.athena.GraphicalController;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load ;

public class TutorPersonalPageController
{

    private Parent root ;

    public void clickOnBackButton(ActionEvent event) throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "tutorSearchPage.fxml");
    }

    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "LoginPage.fxml");
    }

    public void onCVButtonClick(ActionEvent event) throws IOException
    {
        root = load(Objects.requireNonNull(getClass().getResource("tutorCVView.fxml"))) ;
        Stage tempStage = new Stage() ;
        tempStage.setScene(new Scene(root)) ;
        tempStage.initModality(Modality.APPLICATION_MODAL) ;
        tempStage.setResizable(false) ;
        tempStage.setTitle("CV") ;
        tempStage.showAndWait() ;
    }
}
