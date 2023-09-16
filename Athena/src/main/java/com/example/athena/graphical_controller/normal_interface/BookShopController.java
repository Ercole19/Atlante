package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.use_case_controllers.ManageYourSellingBooksUCC;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


public class BookShopController extends HomeScreenController implements Initializable {

    @FXML
    private Text notText1;
    @FXML
    private Text notText2;

    private final SceneSwitcher switcher = SceneSwitcher.getInstance();

    @FXML
    protected void onBackButtonClick() {
        switcher.switcher("MainPageStudents.fxml");
    }

    public void onBuyButtonClick(){
        switcher.switcher("buy-view2.fxml");
    }

    public void onSellButtonClick(){
        switcher.switcher("sell-view.fxml");
    }

    public void onRecentActivitiesBtnClick(){
        switcher.switcher("recentActivities.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (new ManageYourSellingBooksUCC().getNotification().isThereANot()) {
                notText1.setVisible(true);
                notText2.setVisible(true);
            }
        }catch (BookException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Error in communicating with the server, try restarting the application");
            alert.showAndWait();
            switcher.switcher("MainPageStudents.fxml");
        }
    }
}