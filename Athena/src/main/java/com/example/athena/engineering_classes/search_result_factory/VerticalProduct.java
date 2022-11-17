package com.example.athena.engineering_classes.search_result_factory;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class VerticalProduct implements SearchResultProduct{
    private final AnchorPane root;

    public VerticalProduct(AnchorPane root)
    {
        this.root = root;
    }

    @Override
    public AnchorPane getRoot() {
        return this.root;
    }

    @Override
    public void setEntry(int entryNum, int position, Node element) throws IndexOutOfBoundsException {

        GridPane entry  = (GridPane) this.root.lookup(String.format("#entry%d", entryNum));
        if (entry == null) throw new IndexOutOfBoundsException(String.format("Entry no.%d does not exist", entryNum)) ;
        entry.add(element, position, 0);
    }

    @Override
    public void setLegend(int position, Node element) {
        GridPane legend = (GridPane) this.root.lookup("#legend") ;

        if(legend != null) {
            legend.add(element, position, 0) ;
        }
    }
}