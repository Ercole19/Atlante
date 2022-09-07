package com.example.athena.engineering_classes.scene_decorators;

import com.example.athena.engineering_classes.abstract_factory.FormatBundle;
import javafx.scene.layout.AnchorPane;

public abstract class SearchResultFormatterDecorator implements SearchResultFormatterComponent {
    private final SearchResultFormatterComponent component;

    protected SearchResultFormatterDecorator(SearchResultFormatterComponent component) {
        this.component = component;
    }

    @Override
    public AnchorPane buildScene(FormatBundle formatBundle) {
        return this.component.buildScene(formatBundle);
    }

}
