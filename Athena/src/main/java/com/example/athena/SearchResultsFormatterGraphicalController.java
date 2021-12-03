package com.example.athena;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static javafx.fxml.FXMLLoader.load ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SearchResultsFormatterGraphicalController
{
    public AnchorPane buildTutorSearchResultsScene(double containerWidth, double containerHeight, ArrayList<TutorSearchResultEntity> results)
    {
        float sceneHeight = (results.size())*100.0f ;
        VBox graphicalList = new VBox() ;
        graphicalList.setPrefSize(containerWidth, Math.min(sceneHeight, containerHeight));

        if(containerHeight < sceneHeight)
        {
            //This is part of a decorator pattern, now simplified
            ScrollBar scrollBar = new ScrollBar() ;
            scrollBar.setOrientation(Orientation.VERTICAL) ;
        }

        for(TutorSearchResultEntity result: results)
        {
            AnchorPane entryPane = new AnchorPane() ;
            entryPane.setPrefSize(containerWidth, 100) ;
            entryPane.setStyle("-fx-background-color: #faeeae") ;
            entryPane.setStyle("-fx-background-color: #000000") ;

            Label nameLabel = new Label(result.getName() + "" + result.getSurname()) ;
            nameLabel.setFont(new Font("System", 26)) ;
            nameLabel.setLayoutX(containerWidth*5/100) ;
            nameLabel.setLayoutY(30) ;

            Label subjectLabel = new Label(result.getTaughtSubject()) ;
            subjectLabel.setFont(new Font("System", 26)) ;
            nameLabel.setLayoutX(containerWidth*30/100) ;
            nameLabel.setLayoutY(30) ;

            Label starsLabel = new Label(result.getStarNumber()) ;
            starsLabel.setFont(new Font("System", 26)) ;
            nameLabel.setLayoutX(containerWidth*60/100) ;
            nameLabel.setLayoutY(30) ;

            Button visitPage = new Button("Visit page") ;
            nameLabel.setLayoutX(containerWidth*80/100) ;
            nameLabel.setLayoutY(30) ;

            visitPage.setOnAction(new EventHandler<>() {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    Parent root = null ;
                    try {
                        root = load(Objects.requireNonNull(getClass().getResource("tutorPersonalPage.fxml"))) ;
                    } catch (IOException e) {
                        e.printStackTrace() ;
                    }

                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow() ;
                    assert root != null ;
                    Scene scene = new Scene(root) ;
                    stage.setScene(scene) ;
                }
            });

            entryPane.getChildren().addAll(nameLabel, subjectLabel, starsLabel, visitPage) ;

            graphicalList.getChildren().add(entryPane) ;
        }

        return new AnchorPane(graphicalList) ;
    }
}