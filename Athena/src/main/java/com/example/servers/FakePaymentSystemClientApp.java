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
        this.response.setResponse(new FakePaymentSystemClientController().pay().getServerResponse());
    }

    public Response getResponse() {
        return this.response ;
    }
}
