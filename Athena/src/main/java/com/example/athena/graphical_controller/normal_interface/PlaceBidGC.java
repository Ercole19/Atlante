package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.BidBean;
import com.example.athena.beans.BookBean;
import com.example.athena.entities.BidStatusEnum;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.BidException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.use_case_controllers.PlaceBidUCC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PlaceBidGC implements PostInitialize{

    private BookBean bookBean;

    @FXML
    private TextField bidTextField ;


    @Override
    public void postInitialize(ArrayList<Object> params) {
        this.bookBean = (BookBean) params.get(0) ;
    }

    public void sendBid(ActionEvent event) {
        PlaceBidUCC controller = new PlaceBidUCC() ;
        BidBean bidBean = new BidBean();
        try {
            if (bidTextField.getText().equals("")) {
                SizedAlert alert = new SizedAlert(Alert.AlertType.INFORMATION, "You must insert a new price before", 800 ,600);
                alert.showAndWait();
                return;
            }

            bidBean.setNewPrice(bidTextField.getText());

            if (Float.parseFloat(bidTextField.getText()) > Float.parseFloat(this.bookBean.getPrice())) {
                SizedAlert alert = new SizedAlert(Alert.AlertType.INFORMATION, "Bid can not be higher than original price", 800 ,600);
                alert.showAndWait();
                return;
            }

            bidBean.setBidder(LoggedStudent.getInstance().getEmail());
            bidBean.setOwner(this.bookBean.getOwner());
            bidBean.setBookTimestamp(this.bookBean.getTimeStamp());
            bidBean.setBookIsbn(this.bookBean.getIsbn());
            bidBean.setStatus(BidStatusEnum.PENDING.toString());

            controller.placeBid(bidBean);

            ((Stage) ((Node)event.getSource()).getScene().getWindow()).close() ;

        } catch (BidException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), 800 ,600);
            alert.showAndWait();
        }
    }
}
