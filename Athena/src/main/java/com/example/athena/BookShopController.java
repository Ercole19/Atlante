package com.example.athena;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class BookShopController {

    @FXML
    public Label label;
    private String str;
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    AnchorPane maths;

    @FXML
    AnchorPane comps;

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
        /*stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        TextField textF = (TextField) stage.getScene().lookup("#textF");
        str = textF.getText();*/
        root = load(Objects.requireNonNull(getClass().getResource("search-view.fxml")));
        /*label = (Label) stage.getScene().lookup("#label");
        label.setText(str);*/
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        maths.setStyle("-fx-background-color: #2d8bba");
        comps.setStyle("-fx-background-color: #2d8bba");
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        root = load(Objects.requireNonNull(getClass().getResource("MainPageStudents.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    protected void onBackButtonClick2(ActionEvent event) throws IOException {
        root = load(Objects.requireNonNull(getClass().getResource("bookshop-view.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    protected void maths(){
        maths.setStyle("-fx-background-color: #2d8bfa");
    }

    @FXML
    protected void comps(){
        comps.setStyle("-fx-background-color: #2d8bfa");
    }
}