package com.example.athena.engineering_classes.scene_decorators;

import com.example.athena.graphical_controller.normal_interface.TutorPersonalPageController;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.ArrayList;

public class TutorPageButtonAdder extends TutorPageDecorator{

    public TutorPageButtonAdder(TutorPageComponent component)
    {
        super(component) ;
    }

    public Parent build()
    {
        AnchorPane originalScene = (AnchorPane) super.build();
        Object controller = originalScene.getProperties().get("foo") ;
        setBackButton(originalScene, controller) ;
        originalScene.getChildren().addAll(getButton(controller));
        return originalScene ;
    }

    public ArrayList<Button> getButton(Object controller) {
        TutorPersonalPageController graphController = (TutorPersonalPageController) controller;
        ArrayList<Button> buttons = new ArrayList<>();

        Button buttonCV = new Button("Upload CV");
        buttonCV.setLayoutX(41);
        buttonCV.setLayoutY(445);
        setButtonStyle(buttonCV);
        buttons.add(buttonCV);

        Button button = new Button("Confirm");
        button.setLayoutX(41);
        button.setLayoutY(575);
        setButtonStyle(button);
        buttons.add(button);

        Button button1 = new Button("Manage courses");
        button1.setLayoutX(41);
        button1.setLayoutY(644);
        setButtonStyle(button1);
        buttons.add(button1);


        button.setOnAction(event -> {
            try {
                graphController.onConfirmButtonClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        button1.setOnAction(event -> graphController.onManageCoursesBtnClick());
        buttonCV.setOnAction(event -> graphController.onCVButtonClickTutor());

        return buttons;
    }

    private void setBackButton(AnchorPane originalScene, Object controller)
    {
        ((Button) originalScene.lookup("#backBtn")).setOnAction(((TutorPersonalPageController) controller)::clickOnBackButtonTutor);
    }

    private void setButtonStyle(Button button) {
        button.setStyle("-fx-background-color:  #2d8bba; -fx-background-radius: 20; -fx-text-fill: white");
        button.setFont(Font.font("Bookman Old Style", 20));
        button.setPrefHeight(46);
        button.setPrefWidth(198);
        button.setCursor(Cursor.HAND);
    }
}
