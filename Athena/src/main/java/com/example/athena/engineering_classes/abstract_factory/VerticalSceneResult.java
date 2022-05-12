package com.example.athena.engineering_classes.abstract_factory;

import com.example.athena.engineering_classes.scene_decorators.SearchResultFormatterComponent;
import javafx.scene.Node;
import javafx.scene.layout.*;

import java.util.List;

public class VerticalSceneResult extends SearchResultFormatterComponent {

    public AnchorPane buildScene(FormatBundle formatBundle) {
        double sceneHeight = (formatBundle.getEntryNumber()) * formatBundle.getEntrySize();

        VBox graphicalList = new VBox();
        graphicalList.setPrefSize(formatBundle.getContainerWidth(), sceneHeight);
        graphicalList.setId("resultList");

        List<Integer> percents = formatBundle.getEntryPercents();

        for (int i = 0; i < formatBundle.getEntryNumber(); i++) {
            GridPane entryBox = new GridPane();
            entryBox.setId(String.format("entry%d", i)) ;
            entryBox.setPrefSize(formatBundle.getContainerWidth(), formatBundle.getEntrySize());
            entryBox.setStyle("-fx-background-color: #faeeae");
            entryBox.setStyle("-fx-border-color: #000000");

            entryBox.getRowConstraints().add(new RowConstraints(formatBundle.getEntrySize()));
            for (int j = 0; j < percents.size(); j++) {
                setColumnConstraint(percents.get(j), entryBox);
            }
            graphicalList.getChildren().add(entryBox);
        }

        return new AnchorPane(graphicalList);
    }

    private void setColumnConstraint(double percent, GridPane pane) {
        ColumnConstraints columnConstraint = new ColumnConstraints();
        columnConstraint.setPercentWidth(percent);
        pane.getColumnConstraints().add(columnConstraint);
    }
}
