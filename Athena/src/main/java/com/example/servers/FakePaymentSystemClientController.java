package com.example.servers;

import java.io.IOException;

public class FakePaymentSystemClientController {

    public Response pay() {
        boolean result ;
        try {
            result = new FakePaymentSystemSocketBoundary().pay().getPurchaseResult() ;
        } catch (IOException e) {
            result = false ;
        }

        Response retVal = new Response() ;
        retVal.setResponse(result);

        return retVal ;
    }
}
