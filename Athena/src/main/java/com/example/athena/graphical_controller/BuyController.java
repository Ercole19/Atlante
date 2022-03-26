package com.example.athena.graphical_controller;

import com.example.athena.view.FindBooksView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class BuyController {


    @FXML
    private TextField searchInput ;

    @FXML
    private SubScene resultPanel ;
    private final SceneSwitcher switcher = new SceneSwitcher();
    private Stage stage;
    private FindBooksView findBooksView = new FindBooksView(resultPanel.getWidth(), resultPanel.getHeight());


    @FXML
    protected void onHomeButtonClick(ActionEvent event)  {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "MainPageStudents.fxml");
    }

    @FXML
    protected void onSearchButtonClick(){
        String query = searchInput.getText() ;
        this.resultPanel.setRoot(findBooksView.getRoot(query));
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        switcher.switcher(stage, "bookshop-choose-view.fxml");
    }

}
