package com.example.athena.View.SceneDecorators;

import com.example.athena.GraphicalController.TutorPersonalPageController;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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
        originalScene.getChildren().addAll(getButton(controller));
        return originalScene ;
    }


    public ArrayList<Button> getButton(Object controller) {
        TutorPersonalPageController graphController = (TutorPersonalPageController) controller;
        ArrayList<Button> buttons = new ArrayList<>();

        Button buttonCV = new Button("Upload CV");
        buttonCV.setLayoutX(41);
        buttonCV.setLayoutY(445);
        buttons.add(buttonCV);

        Button button = new Button("Confirm");
        button.setLayoutX(41);
        button.setLayoutY(575);
        buttons.add(button);

        Button button1 = new Button("Manage courses");
        button1.setLayoutX(25);
        button1.setLayoutY(644);
        buttons.add(button1);


        button.setOnAction(event -> {
            try {
                graphController.onConfirmButtonClick(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        button1.setOnAction(event -> {
            try {
                graphController.onaddcoursebuttoclick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        buttonCV.setOnAction(event -> {
            try {
            graphController.onCVButtonClicktutor(event);
        } catch (IOException e ){
            e.printStackTrace();
        }
        });

        return buttons;
    }}