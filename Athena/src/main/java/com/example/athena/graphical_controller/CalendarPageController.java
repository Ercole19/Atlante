package com.example.athena.graphical_controller;

import com.example.athena.view.FullCalendarView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class CalendarPageController implements Initializable {


    @FXML
    private SubScene calendario ;


    public void clickOnBackButton(ActionEvent event) throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "MainPageStudents.fxml");
    }

    public void clickOnAddEvent(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        SceneSwitcher switcher = new SceneSwitcher() ;
        loader.setLocation(switcher.generateUrl("AddEventScreen.fxml"));
        Stage stage = new Stage() ;
        stage.initModality(Modality.APPLICATION_MODAL) ;
        stage.setResizable(false) ;
        Scene scene = new Scene(loader.load()) ;
        stage.setTitle("Add a new event") ;
        stage.setScene(scene) ;
        stage.showAndWait() ;
    }

    public void clickOnPlots(ActionEvent event) throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "PlotPage.fxml");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calendario.setRoot(new FullCalendarView(YearMonth.now()).getView());
    }
}