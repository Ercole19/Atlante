package com.example.athena.graphical_controller;

import com.example.athena.engineering_classes.abstract_factory.SearchResultProduct;
import com.example.athena.exceptions.BookException;
import com.example.athena.use_case_controllers.RecentActivitiesUCC;
import com.example.athena.view.LabelBuilder;
import javafx.scene.control.Button;


import java.util.ArrayList;
import java.util.List;

public class RecentPurchasesViewGC {

    private List<BookBean> searchResults ;

    public int getResultSize(String userEmail) throws BookException
    {
        RecentActivitiesUCC controller = new RecentActivitiesUCC();
        this.searchResults = controller.formatPurchasesResults(userEmail);
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
            for(BookBean bookBean : searchResults){

                searchResultProduct.setEntry(i, 0, LabelBuilder.buildLabel(bookBean.getOwner()));

                searchResultProduct.setEntry(i, 1,  LabelBuilder.buildLabel(bookBean.getTitle()));

                searchResultProduct.setEntry(i, 2,  LabelBuilder.buildLabel(bookBean.getIsbn()));

                searchResultProduct.setEntry(i, 3,  LabelBuilder.buildLabel(bookBean.getPrice()));

                Button visitPage = new Button("Report");
                searchResultProduct.setEntry(i, 4, visitPage);

                visitPage.setOnAction(actionEvent -> {
                    SceneSwitcher switcher = new SceneSwitcher();

                    ArrayList<Object> params = new ArrayList<>();
                    params.add(bookBean);
                    switcher.popup("reportChose.fxml", "Report page", params);

                });
                i++;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
