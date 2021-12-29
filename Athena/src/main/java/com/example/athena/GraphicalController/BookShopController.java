package com.example.athena.GraphicalController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class BookShopController {

    HelloApplication Btn = new HelloApplication();
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    protected void onSettingsButtonClick() { System.out.println(4);}

    @FXML
    protected void onHomeButtonClick(ActionEvent event) throws IOException {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "MainPageStudents.fxml");
    }

    @FXML
    protected void onSearchButtonClick(ActionEvent event) throws IOException {
        scene = ((Node) event.getSource()).getScene();
        String res = ((TextField) scene.lookup("#textF")).getText();
        Label label;
        label =  (Label) scene.lookup("#resLab");
        SubScene searchSub = (SubScene) scene.lookup("#results");
        if (!res.equals("")){
            label.setStyle("-fx-opacity: 0");
            label =  (Label) scene.lookup("#resLab1");
            FXMLLoader loader = new FXMLLoader();
            SceneSwitcher switcher = new SceneSwitcher() ;
            loader.setLocation(switcher.generateUrl("Search-view.fxml"));
            root = loader.load() ;
            label.setText(res + ":");
        }
        else{
            FXMLLoader loader = new FXMLLoader();
            SceneSwitcher switcher = new SceneSwitcher() ;
            loader.setLocation(switcher.generateUrl("VoidSearch-view.fxml"));
            root = loader.load() ;
            label.setText("please write something to search");
        }
        label.setStyle("-fx-opacity: 1");
        searchSub.setRoot(root);
        searchSub.setStyle("-fx-opacity: 1");
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "MainPageStudents.fxml");
    }

    @FXML
    protected void onBookBtnClick(ActionEvent event) throws IOException{
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "BuyBookPage.fxml");
    }

    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        Btn.logoutBtn(event);
    }
}