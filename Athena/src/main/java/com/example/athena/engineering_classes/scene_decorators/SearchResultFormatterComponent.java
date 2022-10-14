package com.example.athena.engineering_classes.scene_decorators;

import com.example.athena.engineering_classes.search_result_factory.FormatBundle;
import javafx.scene.layout.AnchorPane;

public interface SearchResultFormatterComponent {
    public abstract AnchorPane buildScene(FormatBundle formatBundle);
}


