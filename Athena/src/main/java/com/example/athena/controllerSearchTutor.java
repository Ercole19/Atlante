package com.example.athena;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class controllerSearchTutor {

    private Scene scene;
    private Stage stage ;
    private Parent root ;


    public void clickOnSearch(ActionEvent event) throws IOException
    {
        Parent root = load(Objects.requireNonNull(getClass().getResource("resultScreen.fxml")));
        scene = (Scene) ((Node) event.getSource()).getScene() ;
        SubScene myScene = (SubScene) scene.lookup("#searchResults") ;
        myScene.setRoot(root) ;
    }
    public void clickOnPersonalPage(ActionEvent event) throws IOException
    {
        root = load(Objects.requireNonNull(getClass().getResource("tutorPersonalPage.fxml"))) ;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        scene = new Scene(root) ;
        stage.setScene(scene);
    }

    public void clickOnBackButton(ActionEvent event) throws IOException
    {
        root = load(Objects.requireNonNull(getClass().getResource("MainPageStudents.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void clickOnReviewTutorButton(ActionEvent event) throws IOException
    {
        root = load(Objects.requireNonNull(getClass().getResource("TutorReviewPage.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        root = load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}
