package com.example.servers;

import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FakePaymentSystemClientApp extends Application {

    private Response response = new Response() ;

    @Override
    public void start(Stage stage) throws Exception {
        ArrayList<Object> params = new ArrayList<>() ;
        params.add(response) ;

        Parent paymentScreen = SceneSwitcher.getInstance().preload("PaymentForm.fxml", params) ;
        stage.setScene(new Scene(paymentScreen)) ;
        stage.showAndWait() ;
    }

    public Response getResponse() {
        return response ;
    }
}
