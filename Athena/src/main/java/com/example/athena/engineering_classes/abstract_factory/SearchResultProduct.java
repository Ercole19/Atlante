package com.example.athena.engineering_classes.abstract_factory;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public interface SearchResultProduct {
    AnchorPane getRoot();
    
    void setEntry(int entryNum, int position, Node element) throws Exception ;
    void setLegend(int position, Node element) ;
}
