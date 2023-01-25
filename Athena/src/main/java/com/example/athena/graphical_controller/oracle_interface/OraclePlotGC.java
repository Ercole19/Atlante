package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.ActivityPlotsBean;
import com.example.athena.beans.PlotSearchQueryBean;
import com.example.athena.entities.ActivityTypesEnum;
import com.example.athena.entities.TimePeriodsEnum;
import com.example.athena.exceptions.PlottingException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.use_case_controllers.GeneratePlotsUseCaseController;
import com.example.athena.view.LabelBuilder;
import com.example.athena.view.oracle_view.LabelView;
import com.example.athena.view.oracle_view.OraclePlotView;
import com.example.athena.view.oracle_view.TableView;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OraclePlotGC {

    private OraclePlotView view ;
    private List<String> tokens ;

    public OraclePlotGC(List<String> tokens) {
        this.tokens = tokens ;
        if (tokens.size() != 2 || !checkPlotType() || !checkTimePeriod()) {
            ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent(
                    "Usage: show#plots#[ALL][STUDY_SESSION][LECTURE_TIME][OTHER]#[LAST_WEEK][LAST_TWO_WEEKS][LAST_MONTH][LAST_TWO_MONTHS]"));
            return ;
        }
        this.view = new OraclePlotView(this) ;
    }

    private boolean checkPlotType() {
        try {
            ActivityTypesEnum.valueOf(tokens.get(0)) ;
            return true ;

        } catch (IllegalArgumentException e) {
            return Objects.equals(tokens.get(0), "ALL") ;
        }
    }

    private boolean checkTimePeriod() {
        try {
            TimePeriodsEnum.valueOf(tokens.get(1)) ;
            return true ;
        } catch (IllegalArgumentException e) {
            return false ;
        }
    }

    public int getPlotNumber() {
        if (Objects.equals(tokens.get(0), "ALL")) return 3 ;
        else return 1 ;
    }

    public int getTimeEntries() {
        switch (TimePeriodsEnum.valueOf(tokens.get(1))) {
            case LAST_WEEK:
                return 7 ;
            case LAST_TWO_WEEKS:
                return 14 ;
            case LAST_MONTH:
                return 31 ;
            case LAST_TWO_MONTHS:
                return 61 ;
            default:
                return 0 ;
        }
    }

    private List<ActivityPlotsBean> getPlotData() throws PlottingException{
        GeneratePlotsUseCaseController controller = new GeneratePlotsUseCaseController() ;
        PlotSearchQueryBean bean = new PlotSearchQueryBean() ;
        bean.setActivityType(tokens.get(0));
        bean.setPeriodType(tokens.get(1));
        try {
            return controller.evaluateQuery(bean) ;
        } catch (PlottingException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK) ;
            alert.showAndWait() ;
            throw new PlottingException(e.getMessage()) ;
        }
    }

    public void setPlotData(TableView view) {
        List<ActivityPlotsBean> beans ;
        try {
            beans = getPlotData() ;
        } catch (PlottingException e) {
            return;
        }

        int i = 1 ;
        LocalDate startDate = beans.get(0).getPlotStartTime() ;
        LocalDate date = startDate ;
        while (date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now())) {
            view.setGridPaneEntry(0, i, LabelBuilder.buildSmallLabel(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            date = date.plusDays(1) ;
            i++ ;
        }

        int j = 1 ;
        for(ActivityPlotsBean bean : beans) {
            view.setGridPaneEntry(j,0, LabelBuilder.buildSmallLabel(bean.getPlotName()));
            date = startDate ;
            i = 1 ;
            Map<LocalDate, Long> data = bean.getPlotSeries() ;

            long max = 0;
            for (Long entry : data.values()) {
                if(entry > max) max = entry ;
            }
            while (date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now())) {
                long value = data.getOrDefault(date, 0L) ;
                Pane pane = new AnchorPane(LabelBuilder.buildSmallLabel(String.format("%.1f",value/60.0))) ;
                pane.setPrefSize(220,50);
                if (value == 0) pane.setBackground(new Background(new BackgroundFill(Color.rgb(224, 220,136), CornerRadii.EMPTY, Insets.EMPTY)));
                else pane.setBackground(new Background(new BackgroundFill(Color.rgb(0, (int)(255 - 128*value/max),0), CornerRadii.EMPTY, Insets.EMPTY)));

                view.setGridPaneEntry(j, i, pane);
                date = date.plusDays(1) ;
                i++ ;
            }
            j++ ;
        }
    }

}
