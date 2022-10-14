package com.example.athena.engineering_classes.search_result_factory;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public interface SearchResultProduct {
    AnchorPane getRoot();
    
    void setEntry(int entryNum, int position, Node element) throws IndexOutOfBoundsException;
    void setLegend(int position, Node element) ;
}
