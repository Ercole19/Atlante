package com.example.athena.GraphicalController;

import com.example.athena.View.FullCalendarView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class CalendarPageController implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;
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
        stage = new Stage() ;
        stage.initModality(Modality.APPLICATION_MODAL) ;
        stage.setResizable(false) ;
        scene = new Scene(loader.load()) ;
        stage.setTitle("Add a new event") ;
        stage.setScene(scene) ;
        stage.showAndWait() ;
    }

    public void clickOnPlots(ActionEvent event) throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "PlotPage.fxml");
    }


    /*public void showCalendar() {
       calendario.setRoot(new FullCalendarView(YearMonth.now()).getView());

    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calendario.setRoot(new FullCalendarView(YearMonth.now()).getView());
    }
}
