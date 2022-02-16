package com.example.athena.graphical_controller;

import com.example.athena.view.FullCalendarView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;

import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.ResourceBundle;

public class CalendarPageController implements Initializable {


    @FXML
    private SubScene calendario ;


    public void clickOnBackButton(ActionEvent event)
    {
        try {
            switchScene("MainPageStudents.fxml" , event);
        } catch (IOException e) {
            e.getMessage();
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
            e.getMessage();
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
