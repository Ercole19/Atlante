package com.example.athena.view.oracle_view;

import com.example.athena.engineering_classes.scene_decorators.HorizontalScrollBarDecorator;
import com.example.athena.engineering_classes.scene_decorators.SearchResultFormatterComponent;
import com.example.athena.engineering_classes.scene_decorators.VerticalScrollBarDecorator;
import com.example.athena.engineering_classes.search_result_factory.FormatBundle;
import com.example.athena.engineering_classes.tableview_adapter.TableViewAdapter;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class TableView {

    private GridPane root ;
    private int rows ;
    private int cols ;

    private double containerWidth ;
    private double containerHeight;

    public TableView(TableFormatBundle bundle) {
        root = new GridPane() ;
        root.setId("resultList");
        root.setPrefSize(bundle.getWidth(), bundle.getHeight()) ;

        rows = bundle.getRows();
        cols = bundle.getCols() ;

        containerWidth = bundle.getContainerWidth();
        containerHeight = bundle.getContainerHeight();

        for (int i : bundle.getHorizontalPercents()) {
            ColumnConstraints contstraint = new ColumnConstraints() ;
            contstraint.setPercentWidth(i) ;
            root.getColumnConstraints().add(contstraint) ;
        }

        for (int i : bundle.getVerticalPercents()) {
            RowConstraints contstraint = new RowConstraints() ;
            contstraint.setPercentHeight(i);
            root.getRowConstraints().add(contstraint) ;
        }
    }

    public void setGridPaneEntry(int xCoor, int yCoor, Node element) throws IndexOutOfBoundsException {
        if(!((xCoor >= 0) && (xCoor < rows)&&((yCoor >= 0) && (yCoor < cols)))) throw new IndexOutOfBoundsException("Wrong coordinates") ;

        root.add(element, xCoor, yCoor) ;
    }

    public Pane getRoot() {
        return this.root ;
    }

    public Pane getPrettyRoot() {
        if(this.containerWidth < root.getPrefWidth() || this.containerHeight < root.getPrefHeight()) {
            SearchResultFormatterComponent adapter = new TableViewAdapter(this) ;

            FormatBundle bundle = new FormatBundle() ;

            bundle.setContainerWidth(this.containerWidth) ;
            bundle.setContainerHeight(this.containerHeight);
            bundle.setWidth(root.getPrefWidth());
            bundle.setHeight(root.getPrefHeight());

            if(containerWidth < root.getPrefWidth()) {
                adapter = new HorizontalScrollBarDecorator(adapter) ;
            }

            if(containerHeight < root.getPrefHeight()) {
                adapter = new VerticalScrollBarDecorator(adapter) ;
            }

            return adapter.buildScene(bundle) ;
        }

        return this.root ;
    }
}
