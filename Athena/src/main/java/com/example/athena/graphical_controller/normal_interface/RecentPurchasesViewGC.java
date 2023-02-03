package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.BookBean;
import com.example.athena.beans.FindRecentBooksBean;
import com.example.athena.beans.RecentBooksSearchResultBean;
import com.example.athena.engineering_classes.search_result_factory.SearchResultProduct;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.FindException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.use_case_controllers.FindRecentInteractedBooksUCC;
import com.example.athena.view.LabelBuilder;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class RecentPurchasesViewGC {

    private List<RecentBooksSearchResultBean> searchResults ;

    public int getResultSize(String userEmail) throws BookException, FindException {
        FindRecentInteractedBooksUCC controller = new FindRecentInteractedBooksUCC();
        FindRecentBooksBean bean = new FindRecentBooksBean();
        bean.setEmail(userEmail);
        this.searchResults = controller.formatPurchasesResults(bean);
        return searchResults.size();
    }

    public void setValues(SearchResultProduct searchResultProduct)
    {
        try{
            int i = 0 ;

            searchResultProduct.setLegend(0, LabelBuilder.buildLabel("Vendor"));
            searchResultProduct.setLegend(1, LabelBuilder.buildLabel("Title"));
            searchResultProduct.setLegend(2, LabelBuilder.buildLabel("ISBN"));
            searchResultProduct.setLegend(3, LabelBuilder.buildLabel("Price"));
            for(RecentBooksSearchResultBean bean : searchResults){

                searchResultProduct.setEntry(i, 0, LabelBuilder.buildLabel(bean.getOwner()));

                searchResultProduct.setEntry(i, 1,  LabelBuilder.buildLabel(bean.getTitle()));

                searchResultProduct.setEntry(i, 2,  LabelBuilder.buildLabel(bean.getIsbn()));

                searchResultProduct.setEntry(i, 3,  LabelBuilder.buildLabel(bean.getPrice()));

                Button reportButton = new Button("Report");
                searchResultProduct.setEntry(i, 4, reportButton);

                reportButton.setOnAction(actionEvent -> {
                    SceneSwitcher switcher = SceneSwitcher.getInstance();

                    ArrayList<Object> params = new ArrayList<>();
                    params.add(bean);
                    switcher.popup("reportChose.fxml", "Report page", params);

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
