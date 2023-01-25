package com.example.athena.boundaries;

import com.example.athena.beans.PurchaseResultBean;
import com.example.athena.exceptions.PurchaseException;
import com.example.servers.FakePaymentSystemBoundary;

public class PurchaseBoundary
{
    private PurchaseBoundary()
    {

    }

    public static PurchaseResultBean purchase() throws PurchaseException
    {
        try
        {   /*
            */

            FakePaymentSystemBoundary boundary = new FakePaymentSystemBoundary() ;
            boolean result = boundary.submitPayment() ;
            PurchaseResultBean bean = new PurchaseResultBean() ;
            bean.setPurchaseResult(result) ;
            return bean ;

        }
        catch (Exception e)
        {
            throw new PurchaseException("Connection to payment system failed") ;
        }
    }
}
