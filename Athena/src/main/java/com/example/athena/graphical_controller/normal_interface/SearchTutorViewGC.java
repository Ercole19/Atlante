package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.normal.SearchTutorQueryBean;
import com.example.athena.engineering_classes.search_result_factory.SearchResultProduct;
import com.example.athena.entities.ByCourseOrNameEnum;
import com.example.athena.exceptions.FindTutorException;
import com.example.athena.beans.normal.TutorSearchResultBean;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.use_case_controllers.SearchTutorUseCaseController;
import com.example.athena.view.LabelBuilder;
import com.example.athena.view.SearchTutorView;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class SearchTutorViewGC {

    private final SearchTutorView view;
    private List<TutorSearchResultBean> searchResults;

    public SearchTutorViewGC(SearchTutorView view) {
        this.view = view;
    }

    public int getResultSize(String query, ByCourseOrNameEnum byCourseOrNameEnum, boolean sortByBestReviews) throws FindTutorException {
        SearchTutorUseCaseController controller = new SearchTutorUseCaseController();
        SearchTutorQueryBean bean = new SearchTutorQueryBean();
        bean.setByBestReviews(sortByBestReviews);
        bean.setQuery(query);
        bean.setByCourseOrName(byCourseOrNameEnum.toString());
        searchResults = controller.formatSearchResults(bean);
        return searchResults.size();
    }

    public void setValues(SearchResultProduct searchResultProduct) {
        searchResultProduct.setLegend(0, LabelBuilder.buildLabel("Name and Surname"));
        searchResultProduct.setLegend(1, LabelBuilder.buildLabel("Taught subject"));
        searchResultProduct.setLegend(2, LabelBuilder.buildLabel("Review score"));
        searchResultProduct.setLegend(3, LabelBuilder.buildLabel(""));
        try{
            int i = 0 ;
            for(TutorSearchResultBean result : searchResults){
                searchResultProduct.setEntry(i, 0, LabelBuilder.buildLabel(result.getName() + " " + result.getSurname())) ;

                searchResultProduct.setEntry(i, 1, LabelBuilder.buildLabel(result.getTaughtSubject())) ;

                HBox stars = new HBox() ;
                ImageView starIcon = new ImageView("file:./src/main/resources/assets/starIcon.png") ;
                starIcon.setPreserveRatio(true);
                starIcon.setFitWidth(40);
                Label score = LabelBuilder.buildLabel(result.getStarNumber()) ;
                stars.setAlignment(Pos.CENTER_LEFT);
                stars.getChildren().addAll(score, starIcon) ;

                searchResultProduct.setEntry(i, 2, stars) ;

                Button visitPage = new Button("Visit page") ;
                searchResultProduct.setEntry(i, 3, visitPage);

                visitPage.setOnAction(actionEvent -> {
                    SceneSwitcher switcher = SceneSwitcher.getInstance();
                    ArrayList<Object> params = new ArrayList<>() ;
                    params.add(result.getId()) ;
                    params.add(true) ;
                    if (System.getProperty("oracle").equals("false")) switcher.switcher("tutorPersonalPage.fxml", params) ;
                    else switcher.popup("OracleTutorPersonalPage.fxml", "Tutor personal page", params);
                });
                i++;
            }
        }
        catch (IndexOutOfBoundsException e){
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Error!", 800, 600);
            alert.showAndWait();
        }
    }
}
