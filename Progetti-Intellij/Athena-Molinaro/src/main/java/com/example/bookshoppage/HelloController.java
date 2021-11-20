package com.example.bookshoppage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class HelloController {
    @FXML
    static Button searchBtn;

    @FXML
    AnchorPane maths;

    @FXML
    AnchorPane comps;

    @FXML
    Text text = new Text();

    @FXML
    TextField textF = new TextField();

    @FXML
    protected void onSettingsButtonClick() { System.out.println(4);}

    @FXML
    protected void onHomeButtonClick() { System.out.println(3);}

    @FXML
    protected void onSearchButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.stage.get().setTitle("Athena");
        HelloApplication.stage.get().setScene(scene);
        HelloApplication.stage.get().show();
        maths.setStyle("-fx-background-color: #2d8bba");
        comps.setStyle("-fx-background-color: #2d8bba");
    }

    @FXML
    protected void onBackButtonClick(){
        HelloApplication.stage.get().close();
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