package com.example.athena.graphical_controller;

import com.example.athena.engineering_classes.abstract_factory.SearchResultProduct;
import com.example.athena.entities.ByCourseOrNameEnum;
import com.example.athena.exceptions.BookException;
import com.example.athena.use_case_controllers.RecentPurchaseUCC;
import com.example.athena.view.RecentPurchasesView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RecentPurchasesViewGC {

    private RecentPurchasesView view ;
    private List<BookBean> searchResults ;
    private static final String FONT = "System";

    public RecentPurchasesViewGC(RecentPurchasesView view)
    {
        this.view = view ;
    }

    public int getResultSize(String userEmail) throws BookException
    {
        RecentPurchaseUCC controller = new RecentPurchaseUCC();
        this.searchResults = controller.formatResults(userEmail);
        return searchResults.size();
    }

    public void setValues(SearchResultProduct searchResultProduct)
    {
        try{
            int i = 0 ;
            for(BookBean bookBean : searchResults){
                Label ownerLabel = new Label(bookBean.getOwner());
                ownerLabel.setFont(new Font(FONT, 26));
                searchResultProduct.setEntry(i, 0, ownerLabel);

                Label titleLabel = new Label(bookBean.getBookTitle());
                titleLabel.setFont(new Font(FONT, 26));
                searchResultProduct.setEntry(i, 1, titleLabel);

                Label isbnLabel = new Label(bookBean.getIsbn());
                isbnLabel.setFont(new Font(FONT, 26));
                searchResultProduct.setEntry(i, 2, isbnLabel);

                Label priceLabel = new Label(bookBean.getPrice());
                priceLabel.setFont(new Font(FONT, 26));
                searchResultProduct.setEntry(i, 3, priceLabel);

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
