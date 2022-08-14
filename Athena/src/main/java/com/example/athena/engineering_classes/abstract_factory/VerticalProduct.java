package com.example.athena.engineering_classes.abstract_factory;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.HashSet;
import java.util.Set;

public class VerticalProduct implements SearchResultProduct{
    private AnchorPane root;

    public VerticalProduct(AnchorPane root)
    {
        this.root = root;
    }

    @Override
    public AnchorPane getRoot() {
        return this.root;
    }

    @Override
    public void setEntry(int entryNum, int position, Node element) throws Exception {
        GridPane entry = (GridPane) this.root.lookup(String.format("#entry%d", entryNum));
        entry.add(element, position, 0);
    }
}
