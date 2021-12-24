package com.example.athena;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private ChoiceBox<String> activityTypeChoiceBox ;

    @FXML
    private ChoiceBox<String> timePeriodChoiceBox ;

    @FXML
    private StackedBarChart<String, Number> activitiesPlot ;

    public void clickOnBackButton(ActionEvent event) throws IOException
    {
        root = load(Objects.requireNonNull(getClass().getResource("CalendarPage.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        root = load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
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
