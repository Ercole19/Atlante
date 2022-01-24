package com.example.athena.View;

import com.example.athena.GraphicalController.SceneSwitcher;
import com.example.athena.GraphicalController.TutorSearchResultBean;
import com.example.athena.GraphicalController.eventBean;
import com.example.athena.View.SceneDecorators.SearchResultFormatterComponent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SearchResultFormatterView extends SearchResultFormatterComponent {
    @Override
    public AnchorPane buildTutorSearchResultsScene(double containerWidth, double containerHeight, ArrayList<TutorSearchResultBean> results)
    {

        double sceneHeight = (results.size())*100.0f ;

        VBox graphicalList = new VBox() ;
        graphicalList.setPrefSize(containerWidth, sceneHeight) ;
        graphicalList.setId("resultList") ;

        for(TutorSearchResultBean result: results)
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

            visitPage.setOnAction(actionEvent -> {

                SceneSwitcher switcher = new SceneSwitcher() ;
                try {
                    switcher.switcher(actionEvent, "tutorPersonalPage.fxml", new ArrayList<>(Collections.singleton(result.getId())));
                } catch (IOException e) {
                    e.printStackTrace();
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




    @Override
    public AnchorPane buildEventSearchResultsScene(double containerWidth, double containerHeight, ArrayList<eventBean> results)
    {

        double sceneHeight = (results.size())*100.0f ;

        VBox graphicalList = new VBox() ;
        graphicalList.setPrefSize(containerWidth, sceneHeight) ;
        graphicalList.setId("resultList") ;

        for(eventBean result: results)
        {
            GridPane entryBox = new GridPane() ;
            entryBox.setPrefSize(containerWidth, 100) ;
            entryBox.setStyle("-fx-background-color: #faeeae") ;
            entryBox.setStyle("-fx-border-color: #000000") ;

            entryBox.getRowConstraints().add(new RowConstraints(100)) ;
            setColumnConstraint(20, entryBox) ;
            setColumnConstraint(20, entryBox) ;
            setColumnConstraint(20, entryBox) ;
            setColumnConstraint(20, entryBox) ;
            setColumnConstraint(20, entryBox) ;
            setColumnConstraint(20, entryBox) ;


            Label nameLabel = new Label(result.getName()) ;
            nameLabel.setFont(new Font("System", 26)) ;
            entryBox.add(nameLabel, 0, 0) ;

            Label subjectLabel = new Label(result.getStart().toString()) ;
            subjectLabel.setFont(new Font("System", 26)) ;
            entryBox.add(subjectLabel, 1, 0) ;

            Label starsLabel = new Label(result.getEnd().toString()) ;
            starsLabel.setFont(new Font("System", 26)) ;
            entryBox.add(starsLabel, 2, 0) ;

            Button visitPage = new Button("Description") ;
            entryBox.add(visitPage, 3, 0) ;

            Button delete = new Button("delete") ;
            entryBox.add(delete, 4, 0) ;

            Button edit = new Button("edit") ;
            entryBox.add(edit, 5, 0) ;


            graphicalList.getChildren().add(entryBox) ;
        }

        return new AnchorPane(graphicalList) ;
    }


}