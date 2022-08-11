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

    @Override
    public void deleteEntry(int entryNum) {
        GridPane entry = (GridPane) this.root.lookup(String.format("#entry%d", entryNum));
        /*entry.getChildren().removeAll() ;
        this.root.getChildren().removeAll(entry) ;*/
        deleteRow(entry, entryNum);
    }


    public void deleteRow(GridPane grid, final int row) {
        Set<Node> deleteNodes = new HashSet<>();
        for (Node child : grid.getChildren()) {
            // get index from child
            Integer rowIndex = GridPane.getRowIndex(child);

            // handle null values for index=0
            int r = rowIndex == null ? 0 : rowIndex;

            if (r > row) {
                // decrement rows for rows after the deleted row
                GridPane.setRowIndex(child, r-1);
            } else if (r == row) {
                // collect matching rows for deletion
                deleteNodes.add(child);
            }
        }

        // remove nodes from row
        grid.getChildren().removeAll(deleteNodes);
    }
}
