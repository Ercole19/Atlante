package com.example.athena.graphical_controller;

import com.example.athena.view.FindBooksView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BuyController implements Initializable, PostInitialize {


    @FXML
    private TextField searchInput ;

    @FXML
    private SubScene resultPanel ;
    private final SceneSwitcher switcher = new SceneSwitcher();
    private Stage stage;
    private FindBooksView findBooksView ;


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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.findBooksView = new FindBooksView(resultPanel.getWidth(), resultPanel.getHeight()) ;
    }

    @Override
    public void postInitialize(ArrayList<Object> params) {
        String nameOrIsbn = (String) params.get(0);
        searchInput.setText(nameOrIsbn);
        onSearchButtonClick() ;
    }
}
