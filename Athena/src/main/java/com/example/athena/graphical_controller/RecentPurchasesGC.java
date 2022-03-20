package com.example.athena.graphical_controller;

import com.example.athena.entities.Student;
import com.example.athena.entities.User;
import com.example.athena.exceptions.BookException;
import com.example.athena.use_case_controllers.RecentPurchaseUCC;
import com.example.athena.view.SearchResultFormatterView;
import com.example.athena.engineering_classes.scene_decorators.SearchResultFormatterComponent;
import com.example.athena.engineering_classes.scene_decorators.SearchResultFormatterScrollBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
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
        List<BookBean> bookList = new ArrayList<>() ;
        try {
            bookList = controller.formatResults(Student.getInstance().getEmail());
        } catch (BookException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setContentText(e.getMessage()) ;
            alert.showAndWait() ;
        }

        SearchResultFormatterComponent resultView = new SearchResultFormatterView() ;

        if(subScene.getHeight() < bookList.size()*100.0)
        {
            resultView = new SearchResultFormatterScrollBar(resultView) ;
        }

        AnchorPane subSceneElems = resultView.buildRecentPurchaseResultScene(subScene.getWidth(), subScene.getHeight(), bookList) ;
        subScene.setRoot(subSceneElems) ;

    }
}
