package com.example.servers;

import javafx.application.Application;
import javafx.stage.Stage;

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
