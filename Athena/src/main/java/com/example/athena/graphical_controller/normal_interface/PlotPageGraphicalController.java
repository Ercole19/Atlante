package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.ActivityPlotsBean;
import com.example.athena.beans.PlotSearchQueryBean;
import com.example.athena.entities.PlottingOptionsEnum;
import com.example.athena.entities.TimePeriodsEnum;
import com.example.athena.exceptions.PlottingException;
import com.example.athena.use_case_controllers.GeneratePlotsUseCaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class PlotPageGraphicalController extends  HomeScreenController implements Initializable
{

    @FXML
    private ChoiceBox<String> activityTypeChoiceBox ;

    @FXML
    private ChoiceBox<String> timePeriodChoiceBox ;

    @FXML
    private StackedBarChart<String, Long> activitiesPlot ;

    public void clickOnBackButton() throws IOException
    {
        SceneSwitcher switcher = SceneSwitcher.getInstance() ;
        switcher.switcher("CalendarPage.fxml");
    }

    public void generatePlot()
    {
        try
        {
            activitiesPlot.getData().clear() ;
            PlotSearchQueryBean queryBean = new PlotSearchQueryBean() ;
            queryBean.setActivityType(activityTypeChoiceBox.getValue());
            queryBean.setPeriodType(timePeriodChoiceBox.getValue());
            GeneratePlotsUseCaseController plotsController = new GeneratePlotsUseCaseController() ;
            List<ActivityPlotsBean> plotsBean = plotsController.evaluateQuery(queryBean) ;
            List<XYChart.Series<String,Long>> series = new ArrayList<>() ;

            for (ActivityPlotsBean bean : plotsBean) {
                XYChart.Series<String, Long> newSeries = new XYChart.Series<>() ;
                String name = bean.getPlotName();
                newSeries.setName(name.charAt(0) + name.substring(1).toLowerCase().replace("_" , " "));
                LocalDate today = LocalDate.now() ;
                LocalDate startDay = bean.getPlotStartTime() ;
                Map<LocalDate, Long> entrySet = bean.getPlotSeries() ;

                while(startDay.isBefore(today))
                {
                    newSeries.getData().add(new XYChart.Data<>(startDay.toString(), entrySet.getOrDefault(startDay, 0L))) ;
                    startDay = startDay.plusDays(1) ;
                }
                series.add(newSeries) ;
            }

            activitiesPlot.getData().addAll(series) ;

        }catch (PlottingException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred, here the details:\n" + e.getMessage()) ;
            alert.showAndWait() ;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        for(PlottingOptionsEnum elem: PlottingOptionsEnum.values())
        {
            String entry = elem.toString().replace("_", " ").toLowerCase() ;
            activityTypeChoiceBox.getItems().add(entry.substring(0,1).toUpperCase() + entry.substring(1)) ;
        }

        activityTypeChoiceBox.setValue("All") ;

        activityTypeChoiceBox.setOnAction(event -> generatePlot());

        for(TimePeriodsEnum e: TimePeriodsEnum.values())
        {
            String entry = e.toString().replace("_", " ").toLowerCase() ;
            timePeriodChoiceBox.getItems().add(entry.substring(0,1).toUpperCase() + entry.substring(1)) ;
        }

        timePeriodChoiceBox.setValue("Last two months") ;

        timePeriodChoiceBox.setOnAction(event -> generatePlot());

        activitiesPlot.setAnimated(false) ;

        generatePlot() ;
    }
}
