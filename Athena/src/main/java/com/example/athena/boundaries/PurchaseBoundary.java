package com.example.athena.boundaries;

import java.io.IOException;

public class PurchaseBoundary extends SocketBoundary
{
    private PurchaseBoundary()
    {

    }

    public static boolean purchase() throws Exception
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
