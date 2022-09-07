package com.example.athena.engineering_classes.scene_decorators;

import com.example.athena.engineering_classes.abstract_factory.FormatBundle;
import javafx.scene.layout.AnchorPane;

public interface SearchResultFormatterComponent {
    public abstract AnchorPane buildScene(FormatBundle formatBundle);
}


