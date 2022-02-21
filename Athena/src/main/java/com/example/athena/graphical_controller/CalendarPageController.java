package com.example.athena.graphical_controller;

import com.example.athena.entities.CalendarEntity;
import com.example.athena.exceptions.EventException;
import com.example.athena.view.AnchorPaneNode;
import com.example.athena.view.FullCalendarView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CalendarPageController implements Initializable {


    @FXML
    private SubScene calendario ;

    private YearMonth currentYearMonth ;
    private FullCalendarView view ;
    private CalendarEntity calendarEntity ;

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


    public void populateCalendar(YearMonth yearMonth)  {
        // Get the date we want to start with on the calendar
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        // Dial back the day until it is SUNDAY (unless the month starts on a sunday)
        while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY") ) {
            calendarDate = calendarDate.minusDays(1);
        }
        try
        {
            this.calendarEntity = new CalendarEntity(this.currentYearMonth) ;
        }
        catch (EventException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage()) ;
            alert.showAndWait() ;
            return ;
        }
        // Populate the calendar with day numbers
        for (AnchorPaneNode ap : view.getAllCalendarDays()) {
            if (ap.getChildren().size() != 0) {
                ap.getChildren().clear();
            }
            Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));

            if(this.calendarEntity.isThereAnEventOnThatDay(calendarDate).getThereAnEvent())
            {
                Circle bookMark = new Circle() ;
                bookMark.setRadius(5) ;
                bookMark.setFill(Paint.valueOf("RED")) ;
                bookMark.setLayoutX(50) ;
                bookMark.setLayoutY(60) ;
                ap.getChildren().add(bookMark) ;
            }
            ap.setDate(calendarDate);
            ap.setTopAnchor(txt, 5.0);
            ap.setLeftAnchor(txt, 5.0);
            ap.getChildren().add(txt);
            calendarDate = calendarDate.plusDays(1);
        }



       this.view.getCalendarTitle().setText(yearMonth.getMonth().toString() + " " + yearMonth.getYear());
    }


    public void previousMonth()  {
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
    }


    public void nextMonth()  {
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
    }

    public void loadEventsByDate(LocalDate day)
    {
        try {
            List<EventBean> results =  calendarEntity.getEvents(day); //Another bean should be added

            SceneSwitcher switcher = new SceneSwitcher() ;
            ArrayList<Object> params = new ArrayList<>() ;
            params.add(String.valueOf(day));
            params.add(results) ;
            switcher.popup("eventPage.fxml", "Event infos", params) ;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.currentYearMonth = YearMonth.now() ;
        this.view = new FullCalendarView(this) ;
        this.populateCalendar(this.currentYearMonth);
        calendario.setRoot(this.view.getView());
    }
}
