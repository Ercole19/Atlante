package com.example.athena.graphical_controller;

import com.example.athena.view.scene_decorators.SearchResultFormatterComponent;
import com.example.athena.view.scene_decorators.SearchResultFormatterScrollBar;
import com.example.athena.view.SearchResultFormatterView;
import com.example.athena.use_case_controllers.SearchTutorUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
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

    public void clickOnSearch()
    {
        SearchTutorUseCaseController controller = new SearchTutorUseCaseController() ;
        List<TutorSearchResultBean> results =  controller.formatSearchResultsByCourse(searchBar.getText()) ; //Another bean should be added
        SearchResultFormatterComponent resultView = new SearchResultFormatterView() ;
        if(resultsBox.getHeight() < results.size()*100.0)
        {
            resultView = new SearchResultFormatterScrollBar(resultView) ;
        }
        AnchorPane subSceneElems = resultView.buildTutorSearchResultsScene(resultsBox.getWidth(), resultsBox.getHeight(), (ArrayList<TutorSearchResultBean>) results) ;
        resultsBox.setRoot(subSceneElems) ;
    }

    public void clickOnSearchByName()
    {
        SearchTutorUseCaseController controller = new SearchTutorUseCaseController() ;
        List<TutorSearchResultBean> results =  controller.formatSearchResultsByName(searchBar.getText()) ; //Another bean should be added
        SearchResultFormatterComponent resultView = new SearchResultFormatterView() ;
        if(resultsBox.getHeight() < results.size()*100.0)
        {
            resultView = new SearchResultFormatterScrollBar(resultView) ;
        }
        AnchorPane subSceneElems = resultView.buildTutorSearchResultsScene(resultsBox.getWidth(), resultsBox.getHeight(), (ArrayList<TutorSearchResultBean>) results) ;
        resultsBox.setRoot(subSceneElems) ;
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
