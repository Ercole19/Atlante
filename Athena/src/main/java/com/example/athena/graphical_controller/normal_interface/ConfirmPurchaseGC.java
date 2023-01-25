package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.BookBean;
import com.example.athena.beans.PurchaseResultBean;
import com.example.athena.exceptions.PurchaseException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.use_case_controllers.BuyControllerUCC;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ConfirmPurchaseGC implements PostInitialize {

    private BookBean bean;
    private Stage stage;

    @FXML
    private Button yesButton ;

    @FXML
    private Button noButton ;

    public void onYesBtnClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;

        yesButton.setDisable(true) ;
        noButton.setDisable(true) ;
        stage.setOnCloseRequest(Event::consume);

        BuyControllerUCC controller = new BuyControllerUCC();
        try {
            PurchaseResultBean purchaseResultBean = controller.purchase(this.bean) ;
            SizedAlert sizedAlert;
            if(purchaseResultBean.getPurchaseResult())
            {
                sizedAlert = new SizedAlert(Alert.AlertType.CONFIRMATION, "Your purchase has been made! You can see it in recent purchase window.", ButtonType.CLOSE);
            }else {
                sizedAlert = new SizedAlert(Alert.AlertType.ERROR, "Your purchase has been declined", ButtonType.CLOSE);
            }
            sizedAlert.showAndWait();

        }
        catch (PurchaseException e) {
            SizedAlert sizedAlert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE);
            sizedAlert.showAndWait();
        }


        stage.close();
    }

    public void onNoBtnClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void postInitialize(ArrayList<Object> params) {
        this.bean = (BookBean) params.get(0);
    }
}
