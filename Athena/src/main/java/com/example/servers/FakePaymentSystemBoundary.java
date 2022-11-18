package com.example.servers;

import javafx.stage.Stage;

public class FakePaymentSystemBoundary {

    public boolean submitPayment() throws Exception {
        FakePaymentSystemClientApp client = new FakePaymentSystemClientApp() ;
        client.start(new Stage());
        return client.getResponse().getServerResponse();
    }
}
