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


    public void clickOnBackButton(ActionEvent event)
    {
        try {
            switchScene("MainPageStudents.fxml" , event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickOnAddEvent() throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher() ;
        switcher.popup("AddEventScreen.fxml", "Add a new event") ;
    }

    public void clickOnPlots(ActionEvent event)
    {
        try {
            switchScene("PlotPage.fxml" , event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchScene(String fxml , ActionEvent event) throws IOException {

        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, fxml);

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calendario.setRoot(new FullCalendarView(YearMonth.now()).getView());
    }
}
