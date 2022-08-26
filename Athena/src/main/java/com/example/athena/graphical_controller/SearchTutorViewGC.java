package com.example.athena.graphical_controller;

import com.example.athena.engineering_classes.abstract_factory.SearchResultProduct;
import com.example.athena.entities.ByCourseOrNameEnum;
import com.example.athena.exceptions.FindException;
import com.example.athena.exceptions.FindTutorException;
import com.example.athena.use_case_controllers.SearchTutorUseCaseController;
import com.example.athena.view.LabelBuilder;
import com.example.athena.view.SearchTutorView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SearchTutorViewGC {

    private final SearchTutorView view;
    private List<TutorSearchResultBean> searchResults;
    private static final String FONT = "System";

    public SearchTutorViewGC(SearchTutorView view) {
        this.view = view;
    }

    public int getResultSize(String query, ByCourseOrNameEnum byCourseOrNameEnum, boolean sortByBestReviews) throws FindTutorException {
        SearchTutorUseCaseController controller = new SearchTutorUseCaseController();
        searchResults = controller.formatSearchResults(query, byCourseOrNameEnum, sortByBestReviews);
        return searchResults.size();
    }

    public void setValues(SearchResultProduct searchResultProduct) {
        searchResultProduct.setLegend(0, LabelBuilder.buildLabel("Name and Surname"));
        searchResultProduct.setLegend(1, LabelBuilder.buildLabel("Taught subject"));
        searchResultProduct.setLegend(2, LabelBuilder.buildLabel("Reviews score"));
        searchResultProduct.setLegend(3, LabelBuilder.buildLabel("Visit page"));
        try{
            int i = 0 ;
            for(TutorSearchResultBean result : searchResults){
                searchResultProduct.setEntry(i, 0, LabelBuilder.buildLabel(result.getName() + " " + result.getSurname())) ;

                searchResultProduct.setEntry(i, 1, LabelBuilder.buildLabel(result.getTaughtSubject())) ;

                searchResultProduct.setEntry(i, 2, LabelBuilder.buildLabel(result.getStarNumber())) ;

                Button visitPage = new Button("Visit page") ;
                searchResultProduct.setEntry(i, 3, visitPage);

                visitPage.setOnAction(actionEvent -> {
                    SceneSwitcher switcher = new SceneSwitcher() ;
                    ArrayList<Object> params = new ArrayList<>() ;
                    params.add(result.getId()) ;
                    params.add(true) ;
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow() ;
                    switcher.switcher(stage, "tutorPersonalPage.fxml", params) ;
                });
                i++;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
