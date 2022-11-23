package com.example.servers;

import com.example.athena.beans.normal.MailServerResponseBean;
import com.example.athena.beans.normal.PurchaseResultBean;
import com.example.athena.boundaries.SocketBoundary;

import java.io.IOException;

public class FakePaymentSystemSocketBoundary extends SocketBoundary {

    public PurchaseResultBean pay() throws IOException {
        MailServerResponseBean retVal = getResponse(6351) ;
        PurchaseResultBean bean = new PurchaseResultBean() ;
        bean.setPurchaseResult(retVal.getMessage().equals("OK")) ;
        return bean ;
    }
}
