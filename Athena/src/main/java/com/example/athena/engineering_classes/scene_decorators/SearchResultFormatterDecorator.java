package com.example.athena.engineering_classes.scene_decorators;

import com.example.athena.engineering_classes.abstract_factory.FormatBundle;
import com.example.athena.graphical_controller.BookBean;
import com.example.athena.graphical_controller.BookSearchResultBean;
import com.example.athena.graphical_controller.EventBean;
import com.example.athena.graphical_controller.TutorSearchResultBean;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchResultFormatterDecorator extends SearchResultFormatterComponent {
    private final SearchResultFormatterComponent component;

    protected SearchResultFormatterDecorator(SearchResultFormatterComponent component) {
        this.component = component;
    }

    @Override
    public AnchorPane buildScene(FormatBundle formatBundle) {
        return this.component.buildScene(formatBundle);
    }

}
