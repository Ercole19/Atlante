package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.engineering_classes.abstract_factory.SearchResultProduct;
import com.example.athena.entities.SellerOrBuyerEnum;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.FindBookException;
import com.example.athena.beans.BookBean;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.use_case_controllers.BuyControllerUCC;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FindBooksViewGC {

    private List<BookBean> searchResults ;
    private static final String FONT = "System";

    public int getResultSize(String query) throws FindBookException, BookException
    {
        BuyControllerUCC controller = new BuyControllerUCC();
        this.searchResults = controller.formatSearchResults(query);
        return this.searchResults.size();
    }

    public void setValues(SearchResultProduct product, double entrySize, String query)
    {
        try
        {
            int i = 0 ;
            for(BookBean bookBean: this.searchResults)
            {
                ImageView image ;
                if (!bookBean.getImage().isEmpty()) {
                    image = new ImageView(bookBean.getImage().get(0).toURI().toString());
                }
                else {
                    image = new ImageView(new Image(new File("src/main/resources/assets/NoImage.png").toURI().toString())) ;
                }

                image.setFitHeight(entrySize);
                image.setFitWidth(entrySize);
                image.setPreserveRatio(true);
                product.setEntry(i, 0, image);

                Label titleLabel = new Label(bookBean.getBookTitle()) ;
                titleLabel.setFont(new Font(FONT, 26)) ;
                product.setEntry(i, 1, titleLabel) ;

                Label isbnLabel = new Label(bookBean.getIsbn());
                isbnLabel.setFont(new Font(FONT, 26));
                product.setEntry(i, 2, isbnLabel);

                Label priceLabel = new Label(String.valueOf(bookBean.getPrice()));
                priceLabel.setFont(new Font(FONT, 26));
                product.setEntry(i, 3, priceLabel);

                image.setOnMouseClicked(mouseEvent -> {
                    SceneSwitcher switcher = SceneSwitcher.getInstance();
                    List<Object> params = new ArrayList<>() ;
                    params.add(SellerOrBuyerEnum.BUYER);
                    params.add(bookBean);
                    params.add(query) ;
                    switcher.switcher("Book-Page2.fxml", params) ;

                });
                i++ ;
            }
        }
        catch(IndexOutOfBoundsException e)
        {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Error!", 800, 600);
            alert.showAndWait();
        }


    }
}
