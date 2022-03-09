package com.example.athena.graphical_controller;

import com.example.athena.entities.ByCourseOrNameEnum;
import com.example.athena.use_case_controllers.SearchTutorUseCaseController;
import com.example.athena.view.ErrorSceneView;
import com.example.athena.view.SearchResultFormatterView;
import com.example.athena.engineering_classes.scene_decorators.SearchResultFormatterComponent;
import com.example.athena.engineering_classes.scene_decorators.SearchResultFormatterScrollBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class ControllerSearchTutor {

    @FXML
    private TextField searchBar ;

    @FXML
    private SubScene resultsBox ;

    @FXML
    private RadioButton sortByBestReviews;



    private final SceneSwitcher switcher = new SceneSwitcher();
    private Stage stage;


    public void clickOnSearchByCourse()
    {
        search(ByCourseOrNameEnum.BY_COURSE);
    }

    public void clickOnSearchByName()
    {
        search(ByCourseOrNameEnum.BY_NAME);
    }


    private void search(ByCourseOrNameEnum searchEnum)
    {
        if(searchBar.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Insert something before!", ButtonType.CLOSE);
            alert.showAndWait();
        }
        else {
            SearchTutorUseCaseController controller = new SearchTutorUseCaseController();
            List<TutorSearchResultBean> results = controller.formatSearchResults(searchBar.getText(), searchEnum, sortByBestReviews.isSelected()); //Another bean should be added
            if (results.isEmpty()) {
                Parent error = new ErrorSceneView().createErrorScreen("No book has been found.", resultsBox.getWidth(), resultsBox.getHeight()) ;
                resultsBox.setRoot(error) ;
                return ;
            }

            SearchResultFormatterComponent resultView = new SearchResultFormatterView();
            if (resultsBox.getHeight() < results.size() * 100.0) {
                resultView = new SearchResultFormatterScrollBar(resultView);
            }
            AnchorPane subSceneElems = resultView.buildTutorSearchResultsScene(resultsBox.getWidth(), resultsBox.getHeight(), (ArrayList<TutorSearchResultBean>) results);
            resultsBox.setRoot(subSceneElems);
        }
    }
    public void clickOnBackButton(ActionEvent event)
    {

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "MainPageStudents.fxml");
    }

    public void clickOnReviewTutorButton(ActionEvent event)
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "StudentsReviewTutorsView.fxml");
    }
}
