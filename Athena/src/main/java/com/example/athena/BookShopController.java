package com.example.athena;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        root = load(Objects.requireNonNull(getClass().getResource("MainPageStudents.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
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
            root = load(Objects.requireNonNull(getClass().getResource("Search-view.fxml")));
            label.setText(res + ":");
        }
        else{
            root = load(Objects.requireNonNull(getClass().getResource("VoidSearch-view.fxml")));
            label.setText("please write something to search");
        }
        label.setStyle("-fx-opacity: 1");
        searchSub.setRoot(root);
        searchSub.setStyle("-fx-opacity: 1");
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        root = load(Objects.requireNonNull(getClass().getResource("MainPageStudents.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    protected void onBookBtnClick(ActionEvent event) throws IOException{
        root = load(Objects.requireNonNull(getClass().getResource("BuyBookPage.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        Btn.logoutBtn(event);
    }
}