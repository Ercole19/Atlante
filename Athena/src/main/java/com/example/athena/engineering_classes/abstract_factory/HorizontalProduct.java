package com.example.athena.engineering_classes.abstract_factory;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class HorizontalProduct implements SearchResultProduct{

    private AnchorPane root ;
    public HorizontalProduct(AnchorPane root)
    {
        this.root = root ;
    }

    @Override
    public AnchorPane getRoot() {
        return this.root;
    }

    @Override
    public void setEntry(int entryNum, int position, Node element) throws Exception {
        GridPane entry = (GridPane) this.root.lookup(String.format("#entry%d", entryNum)) ;
        entry.add(element, 0, position) ;
    }
}
