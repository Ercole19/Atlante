package com.example.athena.engineering_classes.scene_decorators;

import com.example.athena.engineering_classes.search_result_factory.FormatBundle;
import com.example.athena.view.LabelBuilder;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

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
        legend.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY))) ;
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
        GridPane legend = getLegend(formatBundle);
        anchorPane.setLayoutY(40);
        resultPane.getChildren().add(anchorPane) ;
        resultPane.getChildren().add(legend) ;

        return resultPane ;
    }
}
