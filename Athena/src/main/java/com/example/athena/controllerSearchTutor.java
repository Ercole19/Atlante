package com.example.athena;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class controllerSearchTutor {

    private Scene scene;
    private Stage stage ;
    private Parent root ;

    @FXML
    TextField searchBar ;

    @FXML
    SubScene resultsBox ;

    public void clickOnSearch()
    {
        SearchTutorUseCaseController controller = new SearchTutorUseCaseController() ;
        ArrayList<TutorSearchResultBean> results =  controller.formatSearchResults(searchBar.getText()) ; //Another bean should be added
        SearchResultFormatterComponent resultView = new SearchResultFormatterView() ;
        if(resultsBox.getHeight() < results.size()*100.0)
        {
            resultView = new SearchResultFormatterScrollBar(resultView) ;
        }
        AnchorPane subSceneElems = resultView.buildTutorSearchResultsScene(resultsBox.getWidth(), resultsBox.getHeight(), results) ;
        resultsBox.setRoot(subSceneElems) ;
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
