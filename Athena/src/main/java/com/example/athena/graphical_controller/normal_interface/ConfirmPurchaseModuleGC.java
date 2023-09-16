package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.BookBean;
import com.example.athena.beans.PurchaseResultBean;
import com.example.athena.exceptions.PurchaseException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.use_case_controllers.PurchaseUCC;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ConfirmPurchaseModuleGC implements PostInitialize {

    private BookBean bookToBuy;

    @FXML
    private Button yesButton ;
    @FXML
    private Button noButton ;
    private SceneSwitcher switcher = SceneSwitcher.getInstance();

    public void onYesBtnClick() {
        yesButton.setDisable(true) ;
        noButton.setDisable(true) ;

        PurchaseUCC controller = new PurchaseUCC();
        try {
            PurchaseResultBean purchaseResultBean = controller.purchase(this.bookToBuy) ;
            SizedAlert sizedAlert;
            if(purchaseResultBean.getPurchaseResult())
            {
                sizedAlert = new SizedAlert(Alert.AlertType.CONFIRMATION, "Your purchase has been made! You can see it in recent purchase window.", ButtonType.CLOSE);
                sizedAlert.showAndWait();
                switcher.getTopStage().close();
                switcher.popStage();
                switcher.switcher("buy-view2.fxml");
                switcher.pushStage(new Stage());
            }else {
                sizedAlert = new SizedAlert(Alert.AlertType.ERROR, "Your purchase has been declined", ButtonType.CLOSE);
                sizedAlert.showAndWait();
            }
        }
        catch (PurchaseException e) {
            SizedAlert sizedAlert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE);
            sizedAlert.showAndWait();
        }
        switcher.getTopStage().close();
    }

    public void onNoBtnClick() {
        switcher.getTopStage().close();
    }

    @Override
    public void postInitialize(ArrayList<Object> params) {
        this.bookToBuy = (BookBean) params.get(0);
    }
}
