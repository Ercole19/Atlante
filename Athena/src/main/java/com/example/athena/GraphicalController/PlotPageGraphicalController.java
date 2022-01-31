package com.example.athena.GraphicalController;

import com.example.athena.Entities.ActivityTypesEnum;
import com.example.athena.UseCaseControllers.GeneratePlotsUseCaseController;
import com.example.athena.Entities.TimePeriodsEnum;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

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
        for(ActivityTypesEnum e: ActivityTypesEnum.values())
        {
           activityTypeChoiceBox.getItems().add(e.toString().replace("_", " ")) ;
        }

        activityTypeChoiceBox.setValue("All") ;

        activityTypeChoiceBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                generatePlot() ;
            }
        });

        for(TimePeriodsEnum e: TimePeriodsEnum.values())
        {
            timePeriodChoiceBox.getItems().add(e.toString().replace("_", " ")) ;
        }

        timePeriodChoiceBox.setValue("From beginning") ;

        timePeriodChoiceBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                generatePlot() ;
            }
        });

        activitiesPlot.getXAxis().setTickLabelsVisible(false) ;
        activitiesPlot.setAnimated(false) ;

        generatePlot() ;
    }
}
