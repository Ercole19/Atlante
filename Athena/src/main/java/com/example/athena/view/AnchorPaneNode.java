package com.example.athena.view;

import com.example.athena.graphical_controller.SceneSwitcher;
import com.example.athena.view.scene_decorators.SearchResultFormatterComponent;
import com.example.athena.view.scene_decorators.SearchResultFormatterScrollBar;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import com.example.athena.graphical_controller.eventPageUCC;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.example.athena.graphical_controller.eventBean ;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AnchorPaneNode extends AnchorPane {
    // Date associated with this pane
    private LocalDate date;


    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(Node... children) {
        super(children);
        // Add action handler for mouse clicked
        this.setOnMouseClicked(mouseEvent -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                SceneSwitcher switcher = new SceneSwitcher() ;
                loader.setLocation(switcher.generateUrl("eventPage.fxml"));
                Parent root = loader.load() ;
                Label label = (Label) root.lookup("#label1");
                label.setText(String.valueOf(this.getDate()));
                SubScene result = (SubScene) root.lookup("#results") ;

                eventPageUCC controller = new eventPageUCC() ;
                ArrayList<eventBean> results =  controller.formatSearchResultsByDate(this.getDate()) ; //Another bean should be added
                SearchResultFormatterComponent resultView = new SearchResultFormatterView() ;

                if(result.getHeight() < results.size()*100.0)
                {
                    resultView = new SearchResultFormatterScrollBar(resultView) ;
                }

                AnchorPane subSceneElems = resultView.buildEventSearchResultsScene(result.getWidth(), result.getHeight(), results) ;
                result.setRoot(subSceneElems) ;





































                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                Scene scene = new Scene(root);
                stage.setTitle("Event infos");
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public LocalDate getDate() {
        return this.date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
