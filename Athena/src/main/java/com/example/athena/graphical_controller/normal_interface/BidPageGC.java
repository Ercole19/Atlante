package com.example.athena.graphical_controller.normal_interface;

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
        String seller = (String) params.get(0) ;
        String isbn = (String) params.get(1) ;
        String timestamp = (String) params.get(2) ;

        ReceivedBidsView view = new ReceivedBidsView(subScene.getWidth(), subScene.getHeight(), acceptedSubScene.getWidth(), acceptedSubScene.getHeight()) ;
        subScene.setRoot(view.getRoot(seller, isbn, timestamp));
        acceptedSubScene.setRoot(view.getAcceptedBidRoot(seller, isbn, timestamp)) ;
    }
}
