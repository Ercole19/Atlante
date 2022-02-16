package com.example.athena.view;

import com.example.athena.entities.EventDao;
import com.example.athena.graphical_controller.EventBean;
import com.example.athena.graphical_controller.SceneSwitcher;
import com.example.athena.graphical_controller.TutorSearchResultBean;
import com.example.athena.view.scene_decorators.SearchResultFormatterComponent;
import com.jcraft.jsch.IO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SearchResultFormatterView extends SearchResultFormatterComponent {
    private static final String FONT = "System";

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
            nameLabel.setFont(new Font(FONT, 26)) ;
            entryBox.add(nameLabel, 0, 0) ;

            Label subjectLabel = new Label(result.getTaughtSubject()) ;
            subjectLabel.setFont(new Font(FONT, 26)) ;
            entryBox.add(subjectLabel, 1, 0) ;


            Label starsLabel = new Label(result.getStarNumber());

            starsLabel.setFont(new Font(FONT, 26)) ;
            entryBox.add(starsLabel, 2, 0) ;

            Button visitPage = new Button("Visit page") ;
            entryBox.add(visitPage, 3, 0) ;

            visitPage.setOnAction(actionEvent -> {

                SceneSwitcher switcher = new SceneSwitcher() ;
                try {
                    ArrayList<Object> params = new ArrayList<>() ;
                    params.add(result.getId()) ;
                    params.add(true) ;
                    switcher.switcher(actionEvent, "tutorPersonalPage.fxml", params) ;
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
    public AnchorPane buildEventSearchResultsScene(double containerWidth, double containerHeight, ArrayList<EventBean> results)
    {

        double sceneHeight = (results.size())*100.0f ;


        VBox graphicalList = new VBox() ;
        graphicalList.setPrefSize(containerWidth, sceneHeight) ;
        graphicalList.setId("resultList") ;

        for(EventBean result: results)
        {
            GridPane entryBox = new GridPane() ;
            entryBox.setPrefSize(containerWidth, 100) ;
            entryBox.setStyle("-fx-background-color: #faeeae") ;
            entryBox.setStyle("-fx-border-color: #000000") ;

            entryBox.getRowConstraints().add(new RowConstraints(100)) ;
            setColumnConstraint(20, entryBox) ;
            setColumnConstraint(15, entryBox) ;
            setColumnConstraint(15, entryBox) ;
            setColumnConstraint(20, entryBox) ;
            setColumnConstraint(10, entryBox) ;
            setColumnConstraint(10, entryBox) ;
            setColumnConstraint(10, entryBox) ;


            Label nameLabel = new Label(result.getName()) ;
            nameLabel.setFont(new Font(FONT, 26)) ;
            entryBox.add(nameLabel, 0, 0) ;

            Label startTimeLabel = new Label(result.getStart().toString()) ;
            startTimeLabel.setFont(new Font(FONT, 26)) ;
            entryBox.add(startTimeLabel, 1, 0) ;

            Label endTimeLabel = new Label(result.getEnd().toString()) ;
            endTimeLabel.setFont(new Font(FONT, 26)) ;
            entryBox.add(endTimeLabel, 2, 0) ;

            Label eventTypeLabel = new Label(result.getType().charAt(0) + result.getType().substring(1).toLowerCase().replace("_", " ")) ;
            eventTypeLabel.setFont(new Font(FONT, 26)) ;
            entryBox.add(eventTypeLabel, 3, 0) ;

            Button description = new Button("Description") ;
            description.setOnAction(event -> {
                FXMLLoader loader = new FXMLLoader();
                SceneSwitcher switcher = new SceneSwitcher();
                try {
                    loader.setLocation(switcher.generateUrl("EventDescription.fxml"));
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Text testo = (Text) root.lookup("#description");
                testo.setText(result.getDescription());
                Stage stage =new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();


            });



            entryBox.add(description, 4, 0) ;

            Button delete = new Button("delete") ;
            delete.setOnAction(event -> {
                EventDao eventDao = new EventDao() ;
                eventDao.delete(result.getName() , result.getDate()) ;
                entryBox.setStyle("-fx-border-color: #ffffff") ;
                deleteRow(entryBox, entryBox.getRowIndex(delete));
            });
            entryBox.add(delete, 5, 0) ;

            Button edit = new Button("edit") ;
            edit.setOnAction(event -> {
                SceneSwitcher switcher = new SceneSwitcher() ;
                ArrayList<Object> params = new ArrayList<>() ;
                params.add(result) ;
                try {
                   switcher.popup("AddEventScreen.fxml","Edit your event", params );
                } catch (IOException e) {
                    e.printStackTrace();
                }



            });
            entryBox.add(edit, 6, 0) ;


            graphicalList.getChildren().add(entryBox) ;
        }

        return new AnchorPane(graphicalList) ;
    }


    static void deleteRow(GridPane grid, final int row) {
        Set<Node> deleteNodes = new HashSet<>();
        for (Node child : grid.getChildren()) {
            // get index from child
            Integer rowIndex = GridPane.getRowIndex(child);

            // handle null values for index=0
            int r = rowIndex == null ? 0 : rowIndex;

            if (r > row) {
                // decrement rows for rows after the deleted row
                GridPane.setRowIndex(child, r-1);
            } else if (r == row) {
                // collect matching rows for deletion
                deleteNodes.add(child);
            }
        }

        // remove nodes from row
        grid.getChildren().removeAll(deleteNodes);
    }


}