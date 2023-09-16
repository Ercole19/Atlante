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

    private final GridPane root ;

    private final TableFormatBundle bundle ;

    public TableView(TableFormatBundle bundle) {

        root = new GridPane() ;
        root.setPrefSize(bundle.getWidth(), bundle.getHeight()) ;

        this.bundle = bundle ;
    }

    public void setGridPaneEntry(int xCoor, int yCoor, Node element) throws IndexOutOfBoundsException {
        if(!((xCoor >= 0) && (xCoor < bundle.getRows())&&((yCoor >= 0) && (yCoor < bundle.getCols())))) throw new IndexOutOfBoundsException("Wrong coordinates") ;

        root.add(element, xCoor, yCoor) ;
    }

    public Pane getRoot() {

        for (double i : bundle.getVerticalPercents()) {
            ColumnConstraints contstraint = new ColumnConstraints() ;
            contstraint.setPercentWidth(i) ;
            root.getColumnConstraints().add(contstraint) ;
        }

        for (double i : bundle.getHorizontalPercents()) {
            RowConstraints contstraint = new RowConstraints() ;
            contstraint.setPercentHeight(i);
            root.getRowConstraints().add(contstraint) ;
        }

        return this.root ;
    }

    public Pane getPrettyRoot() {
        if(this.bundle.getContainerWidth() < root.getPrefWidth() || this.bundle.getContainerHeight() < root.getPrefHeight()) {
            root.setId("resultList");
            SearchResultFormatterComponent adapter = new TableViewAdapter(this) ;

            FormatBundle formatBundle = new FormatBundle() ;

            formatBundle.setContainerWidth(this.bundle.getContainerWidth()) ;
            formatBundle.setContainerHeight(this.bundle.getContainerHeight());
            formatBundle.setWidth(root.getPrefWidth());
            formatBundle.setHeight(root.getPrefHeight());

            if(this.bundle.getContainerWidth() < root.getPrefWidth()) {
                adapter = new HorizontalScrollBarDecorator(adapter) ;
            }

            if(this.bundle.getContainerHeight() < root.getPrefHeight()) {
                adapter = new VerticalScrollBarDecorator(adapter) ;
            }

            return adapter.buildScene(formatBundle) ;
        }

        return this.getRoot() ;
    }
}
