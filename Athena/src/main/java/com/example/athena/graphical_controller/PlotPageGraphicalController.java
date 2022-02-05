package com.example.athena.graphical_controller;

import com.example.athena.entities.PlottingOptionsEnum;
import com.example.athena.use_case_controllers.GeneratePlotsUseCaseController;
import com.example.athena.entities.TimePeriodsEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlotPageGraphicalController implements Initializable
{

    @FXML
    private ChoiceBox<String> activityTypeChoiceBox ;

    @FXML
    private ChoiceBox<String> timePeriodChoiceBox ;

    @FXML
    private StackedBarChart<String, Number> activitiesPlot ;

    public void clickOnBackButton(ActionEvent event) throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "CalendarPage.fxml");
    }

    public void generatePlot()
    {
        activitiesPlot.getData().clear() ;
        PlotSearchQueryBean queryBean = new PlotSearchQueryBean(activityTypeChoiceBox.getValue(), timePeriodChoiceBox.getValue()) ;
        GeneratePlotsUseCaseController plotsController = new GeneratePlotsUseCaseController() ;
        ActivityPlotsBean plotsBean = plotsController.evaluateQuery(queryBean) ;
        activitiesPlot.getData().addAll(plotsBean.getActivityPlots()) ;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        for(PlottingOptionsEnum e: PlottingOptionsEnum.values())
        {
            String entry = e.toString().replace("_", " ").toLowerCase() ;
            activityTypeChoiceBox.getItems().add(entry.substring(0,1).toUpperCase() + entry.substring(1)) ;
        }

        activityTypeChoiceBox.setValue("All") ;

        activityTypeChoiceBox.setOnAction(event -> generatePlot());

        for(TimePeriodsEnum e: TimePeriodsEnum.values())
        {
            String entry = e.toString().replace("_", " ").toLowerCase() ;
            timePeriodChoiceBox.getItems().add(entry.substring(0,1).toUpperCase() + entry.substring(1)) ;
        }

        timePeriodChoiceBox.setValue("From beginning") ;

        timePeriodChoiceBox.setOnAction(event -> generatePlot());

        activitiesPlot.getXAxis().setTickLabelsVisible(false) ;
        activitiesPlot.setAnimated(false) ;

        generatePlot() ;
    }
}
