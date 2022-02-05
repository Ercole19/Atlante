package com.example.athena.graphical_controller;

import com.example.athena.entities.BookDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import java.io.IOException;
import javafx.scene.image.Image;

public class SellController {

    private final SceneSwitcher switcher = new SceneSwitcher();

    @FXML
    private ImageView blob;

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "bookshop-choose-view.fxml");
    }

    public void onSellBtnClick() throws IOException{
        switcher.popup("sellBookModule.fxml", "Sell a book") ;
    }

    public void onBlobButtonClick(){
        BookDao dao = new BookDao();
        Image image = dao.getImage();
        blob.setImage(image);
    }
}
