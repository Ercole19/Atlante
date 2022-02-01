package com.example.athena.view.scene_decorators;

import com.example.athena.graphical_controller.SearchResultsGraphicalController;
import com.example.athena.graphical_controller.TutorSearchResultBean;
import com.example.athena.graphical_controller.EventBean;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SearchResultFormatterScrollBar extends  SearchResultFormatterDecorator
{
    public SearchResultFormatterScrollBar(SearchResultFormatterComponent component) {
        super(component);
    }

    @Override
    public AnchorPane buildTutorSearchResultsScene(double containerWidth, double containerHeight, ArrayList<TutorSearchResultBean> results)
    {
        AnchorPane resultPane = super.buildTutorSearchResultsScene(containerWidth -20, containerHeight, results) ;
        return applyScrollBar(resultPane, containerWidth, containerHeight, results.size()*100.0) ;
    }

    @Override
    public AnchorPane buildEventSearchResultsScene(double containerWidth, double containerHeight, ArrayList<EventBean> results)
    {
        AnchorPane resultPane = super.buildEventSearchResultsScene(containerWidth -20, containerHeight, results) ;
        return applyScrollBar(resultPane, containerWidth, containerHeight, results.size()*100.0) ;
    }


    private ScrollBar getScrollBar(double containerWidth, double containerHeight, double listSize)
    {
        ScrollBar scrollBar = new ScrollBar() ;
        scrollBar.setOrientation(Orientation.VERTICAL) ;
        scrollBar.setLayoutX(containerWidth -20) ;
        scrollBar.setPrefSize(20, containerHeight) ;
        scrollBar.setMin(0) ;
        scrollBar.setMax(listSize - containerHeight + 25.0) ;
        return scrollBar ;
    }

    private AnchorPane applyScrollBar(AnchorPane pane, double containerWidth, double containerHeight, double size)
    {
        pane.setPrefSize(containerWidth, containerHeight) ;
        ScrollBar scrollBar = this.getScrollBar(containerWidth, containerHeight, size) ;
        pane.getChildren().add(scrollBar) ;

        scrollBar.valueProperty().addListener((observableValue, number, newVal) -> {
            SearchResultsGraphicalController pageGraphController = new SearchResultsGraphicalController() ;
            pageGraphController.scrollResults((VBox) pane.lookup("#resultList"), newVal) ;
        });

        return pane ;
    }
}
