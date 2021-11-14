package com.example.guitrial;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //Scene 1
        Group root1 = new Group() ;
        Scene scene1 = new Scene(root1, 1280, 720);

        TextField username = new TextField() ;
        TextField password = new PasswordField() ;

        Text title = new Text("ATHENA") ;
        title.setLayoutX(500.0f);
        title.setLayoutY(100.0f);
        title.setFont(Font.font("Roboto", FontWeight.BOLD, 64));

        username.setLayoutX(550.0f);
        username.setLayoutY(200.0f);
        username.setPromptText("Username");

        password.setLayoutX(550.0f);
        password.setLayoutY(300.0f);
        password.setPromptText("Password...") ;

        Button login = new Button() ;

        login.setText("Log in");
        login.setPrefWidth(200.0f);
        login.setPrefHeight(50.0f);
        login.setLayoutX(530.0f);
        login.setLayoutY(400.0f);

        root1.getChildren().add(username) ;
        root1.getChildren().add(password) ;
        root1.getChildren().add(title) ;
        root1.getChildren().add(login) ;

        //Scene 2
        Group root2 = new Group() ;
        Scene scene2 = new Scene(root2, 1280, 720) ;

        Text welcome = new Text("Hello, Student !") ;

        welcome.setLayoutX(10.0f);
        welcome.setLayoutY(60.0f);
        welcome.setFont(Font.font("Roboto", FontWeight.BOLD, 64));

        Button reachCalendar = new Button("Calendar") ;
        Image calendarIcon = new Image(new FileInputStream("src/main/java/com/example/guitrial/calendarIcon.png")) ;
        BackgroundImage calendarImage = new BackgroundImage(calendarIcon, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT) ;
        Background calendarBackground = new Background(calendarImage) ;
        reachCalendar.setBackground(calendarBackground);

        reachCalendar.setLayoutX(400) ;
        reachCalendar.setLayoutY(300) ;
        reachCalendar.setPrefWidth(100.0f) ;
        reachCalendar.setPrefHeight(100.0f) ;

        Button reachShop = new Button("Shop") ;

        reachShop.setLayoutX(650) ;
        reachShop.setLayoutY(300) ;
        reachShop.setPrefWidth(200.0f) ;
        reachShop.setPrefHeight(50.0f) ;

        Button searchTutor = new Button("Search Tutor") ;

        searchTutor.setLayoutX(400) ;
        searchTutor.setLayoutY(400) ;
        searchTutor.setPrefWidth(200.0f) ;
        searchTutor.setPrefHeight(50.0f) ;

        Button manageCarrier = new Button("Carrier") ;

        manageCarrier.setLayoutX(650) ;
        manageCarrier.setLayoutY(400) ;
        manageCarrier.setPrefWidth(200.0f) ;
        manageCarrier.setPrefHeight(50.0f) ;

        root2.getChildren().add(welcome) ;
        root2.getChildren().add(reachCalendar) ;
        root2.getChildren().add(reachShop) ;
        root2.getChildren().add(searchTutor) ;
        root2.getChildren().add(manageCarrier) ;

        //Add event stage

        Stage addEventStage = new Stage() ;

        TextField eventTitle = new TextField() ;

        eventTitle.setPromptText("Insert the event's title") ;

        DatePicker startDate = new DatePicker() ;
        DatePicker endDate = new DatePicker() ;

        TextField eventDetails = new TextField() ;
        eventTitle.setPromptText("Insert the event's details") ;

        Button doneAdd = new Button("Done") ;

        VBox box = new VBox() ;
        box.getChildren().addAll(eventTitle, startDate, endDate, eventDetails, doneAdd) ;

        addEventStage.setScene(new Scene(box, 200, 600)) ;
        addEventStage.setTitle("Add event");
        addEventStage.initModality(Modality.APPLICATION_MODAL) ;

        //Scene 3

        Group root3 = new Group() ;
        Scene scene3 = new Scene(root3, 1280, 720) ;
        scene3.setFill(Color.CORAL) ;

        Button addEvent = new Button("Add event") ;

        addEvent.setLayoutX(240.0f) ;
        addEvent.setLayoutY(600.0f) ;
        addEvent.setPrefWidth(200.0f) ;
        addEvent.setPrefHeight(50.0f) ;

        Button manageEvents = new Button("Manage events") ;

        manageEvents.setLayoutX(540.0f) ;
        manageEvents.setLayoutY(600.0f) ;
        manageEvents.setPrefWidth(200.0f) ;
        manageEvents.setPrefHeight(50.0f) ;

        Button plotButton = new Button("Plots") ;

        plotButton.setLayoutX(840.0f) ;
        plotButton.setLayoutY(600.0f) ;
        plotButton.setPrefWidth(200.0f) ;
        plotButton.setPrefHeight(50.0f) ;

        Image calendarPlaceholder = new Image("file:src/main/java/com/example/guitrial/CalendarImage.png") ;
        ImageView calendarPlaceHolderView = new ImageView() ;

        calendarPlaceHolderView.setImage(calendarPlaceholder) ;
        calendarPlaceHolderView.setPreserveRatio(true) ;
        calendarPlaceHolderView.setLayoutX(265);
        calendarPlaceHolderView.setLayoutY(50) ;
        calendarPlaceHolderView.setFitWidth(750) ;


        root3.getChildren().add(calendarPlaceHolderView) ;
        root3.getChildren().add(addEvent) ;
        root3.getChildren().add(manageEvents) ;
        root3.getChildren().add(plotButton) ;

        login.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                stage.setScene(scene2);
            }
        });

        reachCalendar.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                stage.setScene(scene3);
            }
        });

        addEvent.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent actionEvent) {
                addEventStage.show() ;
            }
        });

        doneAdd.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent actionEvent) {
                addEventStage.close() ;
            }
        });

        stage.setTitle("Athena");
        stage.setScene(scene1);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}