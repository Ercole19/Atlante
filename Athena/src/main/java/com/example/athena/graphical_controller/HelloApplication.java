package com.example.athena.graphical_controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class HelloApplication extends Application {

  

    @Override
    public void start(Stage stage) throws IOException {
        SceneSwitcher switcher = new SceneSwitcher();
        Scene scene = new Scene(load( switcher.generateUrl("LoginPage.fxml")), 1280, 720);
        stage.setTitle("Athena");
        Image icon = new Image(new File("src/main/resources/assets/icon.png").toURI().toString());
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event->
        {
            String pathnameBook = System.getProperty("user.dir") + "/src/main/resources/book_images/" ;
            String pathnameCV = System.getProperty("user.dir") + "/src/main/resources/tutor_cv/" ;
            File book = new File(pathnameBook) ;
            File cv = new File(pathnameCV) ;
            String[] pathnamesBook = book.list() ;
            String[] pathnamesCV = cv.list() ;
            for (String bookName : pathnamesBook) {
                File bookToDelete = new File(pathnameBook + bookName);
                bookToDelete.delete();
            }
            for (String cvName : pathnamesCV) {
                File cvToDelete = new File(pathnameCV + cvName) ;
                cvToDelete.delete() ;
            }


        });
    }

    public static void main(String[] args) {
        launch();
    }
}