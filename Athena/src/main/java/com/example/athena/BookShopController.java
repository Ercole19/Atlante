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

    @FXML
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
        root = load(Objects.requireNonNull(getClass().getResource("search-view.fxml")));
        SubScene searchSub = (SubScene) scene.lookup("#results");
        Label label = (Label) scene.lookup("#resLab");
        if (!res.equals("")){label.setText("No results for " + res);}
        else{label.setText("please write something to search");}
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

    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        root = load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}