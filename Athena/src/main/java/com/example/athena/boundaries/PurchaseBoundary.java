package com.example.athena.boundaries;

import com.example.athena.exceptions.PurchaseException;
import com.example.athena.graphical_controller.PurchaseResultBean;

import java.io.IOException;

public class PurchaseBoundary extends SocketBoundary
{
    private PurchaseBoundary()
    {

    }

    public static PurchaseResultBean purchase() throws PurchaseException
    {
        try
        {
            String retVal = getResponse(6351) ;
            PurchaseResultBean bean = new PurchaseResultBean() ;
            bean.setPurchaseResult(retVal.equals("OK")) ;
            return bean ;
        }
        catch (IOException e)
        {
            throw new PurchaseException("Connection to payment system failed") ;
        }
    }
}
