package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.entities.ByCourseOrNameEnum;
import com.example.athena.view.SearchTutorView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


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
            SearchTutorView searchTutorView = new SearchTutorView(resultsBox.getWidth(), resultsBox.getHeight());
            this.resultsBox.setRoot(searchTutorView.getRoot(searchBar.getText(), searchEnum, sortByBestReviews.isSelected())) ;
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
