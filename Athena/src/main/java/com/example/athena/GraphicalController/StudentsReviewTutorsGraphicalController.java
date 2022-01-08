package com.example.athena.GraphicalController;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class StudentsReviewTutorsGraphicalController
{
    private Parent root;
    private Scene scene;

    public void clickOnBackButton(ActionEvent event) throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "tutorSearchPage.fxml");
    }

    public void clickOnSubmitButton(ActionEvent event) throws IOException
    {
        root = load((new SceneSwitcher()).generateUrl("tutorPersonalReview.fxml")) ;
        scene = ((Node) event.getSource()).getScene() ;
        SubScene reviewSubscene = (SubScene) scene.lookup("#reviewSectionPrompt") ;
        reviewSubscene.setRoot(root);
    }

    public void clickOnSubmitReviewButton(ActionEvent event)
    {
        scene = ((Node) event.getSource()).getScene() ;
        SubScene reviewSubscene = (SubScene) scene.lookup("#reviewSectionPrompt") ;
        reviewSubscene.setRoot(new AnchorPane());
    }

    public void onLogoutButtonClick(ActionEvent event) throws IOException {

        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "LoginPage.fxml");
    }
}
