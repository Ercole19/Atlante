package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.BidBean;
import com.example.athena.entities.Student;
import com.example.athena.view.ReceivedBidsView;
import javafx.fxml.FXML;
import javafx.scene.SubScene;

import java.util.ArrayList;

public class BidPageGC implements PostInitialize {
    @FXML
    private SubScene subScene;
    
    @FXML
    private SubScene acceptedSubScene ;

    @Override
    public void postInitialize(ArrayList<Object> params) {
        BidBean book = (BidBean) params.get(0);
        String seller = Student.getInstance().getEmail();
        String isbn = book.getBookIsbn();
        String timestamp = book.getBookTimestamp();

        ReceivedBidsView view = new ReceivedBidsView(subScene.getWidth(), subScene.getHeight(), acceptedSubScene.getWidth(), acceptedSubScene.getHeight()) ;
        subScene.setRoot(view.getRoot(seller, isbn, timestamp));
        acceptedSubScene.setRoot(view.getAcceptedBidRoot(seller, isbn, timestamp)) ;
    }

    @FXML
    protected void onBackButtonClick() {
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        switcher.switcher("sell-view.fxml");
    }
}
