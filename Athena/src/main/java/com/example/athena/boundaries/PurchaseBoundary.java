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
            return retVal.equals("OK") ;
        }
        catch (IOException e)
        {
            throw new Exception("To be implemented") ;
        }
    }
}
