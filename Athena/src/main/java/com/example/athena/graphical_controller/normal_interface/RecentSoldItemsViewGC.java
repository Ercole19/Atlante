package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.engineering_classes.search_result_factory.SearchResultProduct;
import com.example.athena.exceptions.BookException;
import com.example.athena.beans.BookBean;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.use_case_controllers.RecentActivitiesUCC;
import com.example.athena.view.LabelBuilder;
import javafx.scene.control.Alert;

import java.util.List;

public class RecentSoldItemsViewGC {

    private List<BookBean> searchResults ;


    public int getResultSize(String email) throws BookException {
        RecentActivitiesUCC controller = new RecentActivitiesUCC();
        this.searchResults = controller.formatSoldItemsResults(email);
        return searchResults.size();
    }

    public void setValues(SearchResultProduct result) {
        try{
            result.setLegend(0, LabelBuilder.buildLabel("Buyer"));
            result.setLegend(1, LabelBuilder.buildLabel("Title"));
            result.setLegend(2,LabelBuilder.buildLabel("Price"));
            result.setLegend(3,LabelBuilder.buildLabel("ISBN"));
            int i = 0 ;
            for(BookBean bookBean : searchResults){

                result.setEntry(i, 0, LabelBuilder.buildLabel(bookBean.getPurchaser()));

                result.setEntry(i, 1, LabelBuilder.buildLabel(bookBean.getBookTitle()));

                result.setEntry(i, 2, LabelBuilder.buildLabel(bookBean.getPrice()));

                result.setEntry(i, 3, LabelBuilder.buildLabel(bookBean.getIsbn()));

                i++;
            }
        }
        catch (IndexOutOfBoundsException e){
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Error!", 800, 600);
            alert.showAndWait();
        }
    }
}
