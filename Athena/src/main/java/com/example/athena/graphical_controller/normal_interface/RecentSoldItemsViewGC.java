package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.FindRecentBooksBean;
import com.example.athena.beans.RecentBooksSearchResultBean;
import com.example.athena.engineering_classes.search_result_factory.SearchResultProduct;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.FindException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.use_case_controllers.FindRecentInteractedBooksUCC;
import com.example.athena.view.LabelBuilder;
import javafx.scene.control.Alert;

import java.util.List;

public class RecentSoldItemsViewGC {

    private List<RecentBooksSearchResultBean> searchResults ;


    public int getResultSize(String email) throws BookException, FindException {
        FindRecentInteractedBooksUCC controller = new FindRecentInteractedBooksUCC();
        FindRecentBooksBean bean = new FindRecentBooksBean();
        bean.setEmail(email);
        this.searchResults = controller.formatSoldItemsResults(bean);
        return searchResults.size();
    }

    public void setValues(SearchResultProduct result) {
        try{
            result.setLegend(0, LabelBuilder.buildLabel("Buyer"));
            result.setLegend(1, LabelBuilder.buildLabel("Title"));
            result.setLegend(2,LabelBuilder.buildLabel("Price"));
            result.setLegend(3,LabelBuilder.buildLabel("ISBN"));
            int i = 0 ;
            for(RecentBooksSearchResultBean bean : searchResults){

                result.setEntry(i, 0, LabelBuilder.buildLabel(bean.getPurchaser()));

                result.setEntry(i, 1, LabelBuilder.buildLabel(bean.getTitle()));

                result.setEntry(i, 2, LabelBuilder.buildLabel(bean.getPrice()));

                result.setEntry(i, 3, LabelBuilder.buildLabel(bean.getIsbn()));

                i++;
            }
        }
        catch (IndexOutOfBoundsException e){
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Error!", 800, 600);
            alert.showAndWait();
        }
    }
}
