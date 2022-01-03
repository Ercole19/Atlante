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

import java.io.IOException;


public class BookShopController {

    SceneSwitcher switcher = new SceneSwitcher();

    @FXML
    protected void onSettingsButtonClick() { System.out.println(4);}

    @FXML
    protected void onHomeButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "MainPageStudents.fxml");
    }

    @FXML
    protected void onSearchButtonClick(ActionEvent event) throws IOException {
        Scene scene = ((Node) event.getSource()).getScene();
        String res = ((TextField) scene.lookup("#textF")).getText();
        Label label;
        SubScene searchSub = (SubScene) scene.lookup("#results");
        Parent root;
        if (!res.equals("")){
            label =  (Label) scene.lookup("#resLab");
            label.setStyle("-fx-opacity: 0");
            label =  (Label) scene.lookup("#resLab1");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(switcher.generateUrl("Search-view.fxml"));
            root = loader.load();
            label.setText(res + ":");
        }
        else{
            label =  (Label) scene.lookup("#resLab1");
            label.setStyle("-fx-opacity: 0");
            label =  (Label) scene.lookup("#resLab");
            label.setStyle("-fx-opacity: 0");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(switcher.generateUrl("VoidSearch-view.fxml"));
            root = loader.load();
            label.setText("please write something to search");
        }
        label.setStyle("-fx-opacity: 1");
        searchSub.setRoot(root);
        searchSub.setStyle("-fx-opacity: 1");
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "MainPageStudents.fxml");
    }

    @FXML
    protected void onBookBtnClick(ActionEvent event) throws IOException{
        switcher.switcher(event, "BuyBookPage.fxml");
    }

    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "LoginPage.fxml");
    }
}