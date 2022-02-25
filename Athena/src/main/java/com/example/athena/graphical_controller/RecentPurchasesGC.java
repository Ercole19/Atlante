package com.example.athena.graphical_controller;

import com.example.athena.entities.User;
import com.example.athena.use_case_controllers.RecentPurchaseUCC;
import com.example.athena.view.SearchResultFormatterView;
import com.example.athena.view.scene_decorators.SearchResultFormatterComponent;
import com.example.athena.view.scene_decorators.SearchResultFormatterScrollBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RecentPurchasesGC implements Initializable {

    @FXML
    private SubScene subScene;

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        SceneSwitcher switcher = new SceneSwitcher() ;
        switcher.switcher(stage, "bookshop-choose-view.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RecentPurchaseUCC controller= new RecentPurchaseUCC();
        List<BookEntityBean> bookList = controller.formatResults(User.getUser().getEmail());

        SearchResultFormatterComponent resultView = new SearchResultFormatterView() ;

        if(subScene.getHeight() < bookList.size()*100.0)
        {
            resultView = new SearchResultFormatterScrollBar(resultView) ;
        }

        AnchorPane subSceneElems = resultView.buildRecentPurchaseResultScene(subScene.getWidth(), subScene.getHeight(), (ArrayList<BookEntityBean>) bookList) ;
        subScene.setRoot(subSceneElems) ;

    }
}
