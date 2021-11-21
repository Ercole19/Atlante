package com.example.athena;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class TutorReviewPageController
{
    private Parent root;
    private Stage stage;
    private Scene scene;

    public void clickOnBackButton(ActionEvent event) throws IOException
    {
        root = load(Objects.requireNonNull(getClass().getResource("tutorSearchPage.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void clickOnSubmitButton(ActionEvent event) throws IOException
    {
        root = load(Objects.requireNonNull(getClass().getResource("tutorPersonalReview.fxml"))) ;
        scene = ((Node) event.getSource()).getScene() ;
        SubScene reviewSubscene = (SubScene) scene.lookup("#reviewSectionPrompt") ;
        reviewSubscene.setRoot(root) ;
    }

    public void clickOnSubmitReviewButton(ActionEvent event) throws IOException
    {
        scene = ((Node) event.getSource()).getScene() ;
        SubScene reviewSubscene = (SubScene) scene.lookup("#reviewSectionPrompt") ;
        reviewSubscene.setRoot(new AnchorPane()) ;
    }

    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        root = load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}
