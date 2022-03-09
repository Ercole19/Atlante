package com.example.athena.graphical_controller;

import com.example.athena.use_case_controllers.BuyControllerUCC;
import com.example.athena.view.ErrorSceneView;
import com.example.athena.view.SearchResultFormatterView;
import com.example.athena.engineering_classes.scene_decorators.SearchResultFormatterComponent;
import com.example.athena.engineering_classes.scene_decorators.SearchResultFormatterScrollBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class BuyController {


    @FXML
    private TextField searchInput ;

    @FXML
    private SubScene resultPanel ;
    private final SceneSwitcher switcher = new SceneSwitcher();
    private Stage stage;

    @FXML
    protected void onHomeButtonClick(ActionEvent event)  {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "MainPageStudents.fxml");
    }

    @FXML
    protected void onSearchButtonClick(){
        String query = searchInput.getText() ;
        BuyControllerUCC controller = new BuyControllerUCC();


        List<BookBean> resultBeans =  controller.formatSearchResults(query);
        if(resultBeans.isEmpty())
        {
            Parent error = new ErrorSceneView().createErrorScreen("No book has been found.", resultPanel.getWidth(), resultPanel.getHeight()) ;
            resultPanel.setRoot(error) ;
            return ;
        }

        SearchResultFormatterComponent resultView = new SearchResultFormatterView();
        if (resultPanel.getWidth() < resultBeans.size() * 250.0) {
            resultView = new SearchResultFormatterScrollBar(resultView);
        }
        AnchorPane subSceneElems = resultView.buildBookSearchResultsScene(resultPanel.getWidth(), resultPanel.getHeight(), resultBeans);
        resultPanel.setRoot(subSceneElems);
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        switcher.switcher(stage, "bookshop-choose-view.fxml");
    }

}
