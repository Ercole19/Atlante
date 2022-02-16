package com.example.athena.graphical_controller;

import com.example.athena.use_case_controllers.SearchTutorUseCaseController;
import com.example.athena.view.SearchResultFormatterView;
import com.example.athena.view.scene_decorators.SearchResultFormatterComponent;
import com.example.athena.view.scene_decorators.SearchResultFormatterScrollBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ControllerSearchTutor {



    @FXML
    TextField searchBar ;

    @FXML
    SubScene resultsBox ;

    boolean byName = true ;

    public void clickOnSearchByCourse()
    {
        if(searchBar.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Insert something before!", ButtonType.CLOSE);
            alert.showAndWait();
        }
        else {
            SearchTutorUseCaseController controller = new SearchTutorUseCaseController();
            List<TutorSearchResultBean> results = controller.formatSearchResults(searchBar.getText(), !(byName)); //Another bean should be added
            if (results.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "No results found", ButtonType.CLOSE);
                alert.showAndWait();
            } else {
                SearchResultFormatterComponent resultView = new SearchResultFormatterView();
                if (resultsBox.getHeight() < results.size() * 100.0) {
                    resultView = new SearchResultFormatterScrollBar(resultView);
                }
                AnchorPane subSceneElems = resultView.buildTutorSearchResultsScene(resultsBox.getWidth(), resultsBox.getHeight(), (ArrayList<TutorSearchResultBean>) results);
                resultsBox.setRoot(subSceneElems);
            }
        }
    }

    public void clickOnSearchByName()
    {
        if(searchBar.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Insert something before!", ButtonType.CLOSE);
            alert.showAndWait();
        }
        else {
            SearchTutorUseCaseController controller = new SearchTutorUseCaseController();
            List<TutorSearchResultBean> results = controller.formatSearchResults(searchBar.getText(), byName); //Another bean should be added
            if (results.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "No results found", ButtonType.CLOSE);
                alert.showAndWait();
            } else {
                SearchResultFormatterComponent resultView = new SearchResultFormatterView();
                if (resultsBox.getHeight() < results.size() * 100.0) {
                    resultView = new SearchResultFormatterScrollBar(resultView);
                }
                AnchorPane subSceneElems = resultView.buildTutorSearchResultsScene(resultsBox.getWidth(), resultsBox.getHeight(), (ArrayList<TutorSearchResultBean>) results);
                resultsBox.setRoot(subSceneElems);
            }
        }
    }

    public void clickOnBackButton(ActionEvent event) throws IOException
    {

        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "MainPageStudents.fxml");
    }

    public void clickOnReviewTutorButton(ActionEvent event) throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "StudentsReviewTutorsView.fxml");
    }
}
