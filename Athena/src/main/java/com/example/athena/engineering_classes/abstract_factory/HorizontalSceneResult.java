package com.example.athena.engineering_classes.abstract_factory;


import com.example.athena.engineering_classes.scene_decorators.SearchResultFormatterComponent;
import javafx.scene.Node;
import javafx.scene.layout.*;

import java.util.List;

public class HorizontalSceneResult extends SearchResultFormatterComponent {

    public AnchorPane buildScene(FormatBundle formatBundle) {
        double sceneWidth = (formatBundle.getEntryNumber()) * 100.0f;

        HBox graphicalList = new HBox();
        graphicalList.setPrefSize(formatBundle.getContainerHeight(), sceneWidth);
        graphicalList.setId("resultList");

        List<Integer> percents = formatBundle.getEntryPercents();

        for (int i = 0; i < formatBundle.getEntryNumber(); i++) {
            GridPane entryBox = new GridPane();
            entryBox.setId(String.format("entry%d", i)) ;
            entryBox.setPrefSize(formatBundle.getContainerHeight(), 100);
            entryBox.setStyle("-fx-background-color: #faeeae");
            entryBox.setStyle("-fx-border-color: #000000");

            entryBox.getColumnConstraints().add(new ColumnConstraints(100));
            for (int j = 0; j < percents.size(); j++) {
                setRowConstraint(percents.get(j), entryBox);
            }
            graphicalList.getChildren().add(entryBox);
        }

        return new AnchorPane(graphicalList);
    }


    private void setRowConstraint(double percent, GridPane pane)
    {
        RowConstraints rowConstraint = new RowConstraints() ;
        rowConstraint.setPercentHeight(percent) ;
        pane.getRowConstraints().add(rowConstraint) ;
    }
}