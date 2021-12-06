package com.example.athena;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static javafx.fxml.FXMLLoader.load ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SearchResultsFormatterGraphicalController extends SearchResultFormatterComponent
{
    @Override
    public AnchorPane buildTutorSearchResultsScene(double containerWidth, double containerHeight, ArrayList<TutorSearchResultEntity> results)
    {
        double sceneHeight = (results.size())*100.0f ;

        if(containerHeight < sceneHeight)
        {
            containerWidth = containerWidth - 10 ;
        }

        VBox graphicalList = new VBox() ;
        graphicalList.setPrefSize(containerWidth, sceneHeight) ;

        for(TutorSearchResultEntity result: results)
        {
            GridPane entryBox = new GridPane() ;
            entryBox.setPrefSize(containerWidth, 100) ;
            entryBox.setStyle("-fx-background-color: #faeeae") ;
            entryBox.setStyle("-fx-border-color: #000000") ;

            entryBox.getRowConstraints().add(new RowConstraints(100)) ;
            setColumnConstraint(30, entryBox) ;
            setColumnConstraint(30, entryBox) ;
            setColumnConstraint(30, entryBox) ;
            setColumnConstraint(10, entryBox) ;

            Label nameLabel = new Label(result.getName() + "" + result.getSurname()) ;
            nameLabel.setFont(new Font("System", 26)) ;
            entryBox.add(nameLabel, 0, 0) ;

            Label subjectLabel = new Label(result.getTaughtSubject()) ;
            subjectLabel.setFont(new Font("System", 26)) ;
            entryBox.add(subjectLabel, 1, 0) ;

            Label starsLabel = new Label(result.getStarNumber()) ;
            starsLabel.setFont(new Font("System", 26)) ;
            entryBox.add(starsLabel, 2, 0) ;

            Button visitPage = new Button("Visit page") ;
            entryBox.add(visitPage, 3, 0) ;

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

            graphicalList.getChildren().add(entryBox) ;
        }

        return new AnchorPane(graphicalList) ;
    }

    private void setColumnConstraint(double percent, GridPane pane)
    {
        ColumnConstraints columnConstraint = new ColumnConstraints() ;
        columnConstraint.setPercentWidth(percent) ;
        pane.getColumnConstraints().add(columnConstraint) ;
    }
}