package com.example.athena.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FullCalendarView {
    private ArrayList<AnchorPaneNode> allCalendarDays = new ArrayList<>(35);
    private VBox view;
    private Text calendarTitle;
    private YearMonth currentYearMonth;

    public FullCalendarView(YearMonth yearMonth) {
    currentYearMonth = yearMonth;
    // Create the calendar grid pane
    GridPane calendar = new GridPane();
        calendar.setPrefSize(700, 500);
        calendar.setGridLinesVisible(true);
    // Create rows and columns with anchor panes for the calendar
        for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 7; j++) {
            AnchorPaneNode ap = new AnchorPaneNode();
            ap.setPrefSize(200,200);
            calendar.add(ap,j,i);
            allCalendarDays.add(ap);
        }
    }
    // Days of the week labels
    Text[] dayNames = new Text[]{ new Text("Sunday"), new Text("Monday"), new Text("Tuesday"),
            new Text("Wednesday"), new Text("Thursday"), new Text("Friday"),
            new Text("Saturday") };
    GridPane dayLabels = new GridPane();
        dayLabels.setPrefWidth(600);
    Integer col = 0;
        for (Text txt : dayNames) {
        AnchorPaneNode ap = new AnchorPaneNode();
        ap.setPrefSize(200, 10);
        ap.setBottomAnchor(txt, 5.0);
        ap.getChildren().add(txt);
        dayLabels.add(ap, col++, 0);
    }
    // Create calendarTitle and buttons to change current month
    calendarTitle = new Text();
    Button previousMonth = new Button("<<");
        previousMonth.setOnAction(e -> previousMonth());
    Button nextMonth = new Button(">>");
        nextMonth.setOnAction(e -> nextMonth());
    HBox titleBar = new HBox(previousMonth, calendarTitle, nextMonth);
        titleBar.setAlignment(Pos.BASELINE_CENTER);

        view = new VBox(titleBar, dayLabels, calendar);
    }

    public VBox getView() {
        return view;
    }

    public List<AnchorPaneNode> getAllCalendarDays() {
        return allCalendarDays;
    }

    public void setAllCalendarDays(List<AnchorPaneNode> allCalendarDays) {
        this.allCalendarDays = (ArrayList<AnchorPaneNode>) allCalendarDays;
    }
    public Text getCalendarTitle() {
        return this.calendarTitle;
    }

}
