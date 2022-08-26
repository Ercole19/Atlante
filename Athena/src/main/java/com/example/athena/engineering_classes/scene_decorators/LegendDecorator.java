package com.example.athena.engineering_classes.scene_decorators;

import com.example.athena.engineering_classes.abstract_factory.FormatBundle;
import com.example.athena.view.LabelBuilder;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.RowConstraints;

public class LegendDecorator extends SearchResultFormatterDecorator {

    public LegendDecorator(SearchResultFormatterComponent component) {
        super(component) ;
    }

    @Override
    public AnchorPane buildScene(FormatBundle formatBundle) {
        formatBundle.setContainerHeight(formatBundle.getContainerHeight() - 40);
        AnchorPane resultPane = super.buildScene(formatBundle) ;
        formatBundle.setContainerHeight(formatBundle.getContainerHeight() + 40);
        return applyLegend(resultPane, formatBundle) ;
    }

    private GridPane getLegend(FormatBundle formatBundle) {
        GridPane legend = new GridPane() ;
        legend.setPrefSize(formatBundle.getContainerWidth(), 40) ;
        legend.setStyle("-fx-background-color: #faeeae");
        legend.setStyle("-fx-border-color: #000000");
        legend.setId("legend");

        legend.getRowConstraints().add(new RowConstraints(40)) ;
        for(Integer percent : formatBundle.getEntryPercents()) {
            legend.getColumnConstraints().add(LabelBuilder.buildConstraint(percent));
        }

        return legend ;
    }

    private AnchorPane applyLegend(AnchorPane anchorPane, FormatBundle formatBundle) {
        AnchorPane resultPane = new AnchorPane() ;
        resultPane.setPrefSize(formatBundle.getContainerWidth(), formatBundle.getContainerHeight()) ;

        SubScene paneSubScene = new SubScene(anchorPane, formatBundle.getContainerWidth(), formatBundle.getContainerHeight() -40);
        paneSubScene.setId("subScene");
        GridPane legend = getLegend(formatBundle);
        paneSubScene.setLayoutY(40);
        resultPane.getChildren().add(paneSubScene) ;
        resultPane.getChildren().add(legend) ;

        return resultPane ;
    }
}
