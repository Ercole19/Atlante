package com.example.athena.engineering_classes.tableview_adapter;

import com.example.athena.engineering_classes.scene_decorators.SearchResultFormatterComponent;
import com.example.athena.engineering_classes.search_result_factory.FormatBundle;
import com.example.athena.view.oracle_view.TableView;
import javafx.scene.layout.AnchorPane;

public class TableViewAdapter implements SearchResultFormatterComponent {

    private final TableView adaptee ;

    public TableViewAdapter(TableView view) {
        this.adaptee = view ;
    }

    @Override
    public AnchorPane buildScene(FormatBundle formatBundle) {
        AnchorPane retVal = new AnchorPane(adaptee.getRoot()) ;
        retVal.setPrefWidth(formatBundle.getContainerWidth());
        retVal.setPrefHeight(formatBundle.getContainerHeight());

        return retVal ;
    }
}
