package com.example.athena.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.text.Font;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class LabelBuilder {

    private static final String FONT = "System";
    private LabelBuilder() {}

    public static Label buildLabel(String content) {
        Label label = new Label(content) ;
        label.setFont(new Font(FONT, 26));
        return label ;
    }

    public static ColumnConstraints buildConstraint(double percent) {
        ColumnConstraints columnConstraint = new ColumnConstraints();
        columnConstraint.setPercentWidth(percent);

        return columnConstraint;
    }
}
