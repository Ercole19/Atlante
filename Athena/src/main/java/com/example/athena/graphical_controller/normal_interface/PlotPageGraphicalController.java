package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.normal.ActivityPlotsBean;
import com.example.athena.entities.PlottingOptionsEnum;
import com.example.athena.entities.TimePeriodsEnum;
import com.example.athena.exceptions.PlottingException;
import com.example.athena.beans.normal.PlotSearchQueryBean;
import com.example.athena.use_case_controllers.GeneratePlotsUseCaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Alert;
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
            ActivityPlotsBean plotsBean = plotsController.evaluateQuery(queryBean) ;
            activitiesPlot.getData().addAll(plotsBean.getActivityPlots()) ;
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

        timePeriodChoiceBox.setValue("From beginning") ;

        timePeriodChoiceBox.setOnAction(event -> generatePlot());

        activitiesPlot.setAnimated(false) ;

        generatePlot() ;
    }
}
