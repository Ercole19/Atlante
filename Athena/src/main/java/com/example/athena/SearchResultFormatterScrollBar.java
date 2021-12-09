package com.example.athena;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        resultPane.setPrefSize(containerWidth, containerHeight) ;
        ScrollBar scrollBar = this.getScrollBar(containerWidth, containerHeight, results.size()*100.0) ;
        resultPane.getChildren().add(scrollBar) ;

        scrollBar.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newVal) {
                SearchResultsGraphicalController pageGraphController = new SearchResultsGraphicalController() ;
                pageGraphController.scrollResults((VBox) resultPane.lookup("#resultList"), newVal) ;
            }
        });

        return resultPane ;
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
}
