package com.example.athena.graphical_controller;

import com.example.athena.use_case_controllers.BuyControllerUCC;
import com.example.athena.view.ErrorSceneView;
import com.example.athena.view.SearchResultFormatterView;
import com.example.athena.view.scene_decorators.SearchResultFormatterComponent;
import com.example.athena.view.scene_decorators.SearchResultFormatterScrollBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class BuyController {
    private SceneSwitcher switcher = new SceneSwitcher();

    @FXML
    private TextField searchInput ;

    @FXML
    private SubScene resultPanel ;

    @FXML
    protected void onHomeButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "MainPageStudents.fxml");
    }

    @FXML
    protected void onSearchButtonClick(){
        String query = searchInput.getText() ;
        BuyControllerUCC controller = new BuyControllerUCC();


        List<BookSearchResultBean> resultBeans =  controller.formatSearchResults(query);
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
        switcher.switcher(event, "bookshop-choose-view.fxml");
    }

}
