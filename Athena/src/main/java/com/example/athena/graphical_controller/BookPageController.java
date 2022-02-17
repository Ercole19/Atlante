package com.example.athena.graphical_controller;

import com.example.athena.use_case_controllers.BookPageUCC;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class BookPageController implements PostInitialize{

     @FXML
     private Button reportButton;
     @FXML
     private Button buyButton;
     @FXML
     private Text title;
     @FXML
     private Text isbn;
     @FXML
     private Text price;
     @FXML
     private Text email;
     @FXML
     private CheckBox negotiable;

    @Override
    public void postInitialize(ArrayList<Object> params) {

        BookPageUCC controller = new BookPageUCC() ;
        BookEntityBean book = controller.getBookInfos((String) params.get(0), (String) params.get(1));
        populatePage(book);



        if(!((boolean) params.get(2))) {
            reportButton.setDisable(false);
            reportButton.setVisible(true);
            buyButton.setVisible(true);
            buyButton.setDisable(false);
        }
    }


    public void populatePage(BookEntityBean book){

        title.setText(book.getBookTitle());
        isbn.setText(book.getIsbn());
        price.setText(book.getPrice());
        email.setText(book.getOwner());
        negotiable.setSelected(book.getNegotiable());

    }

}
