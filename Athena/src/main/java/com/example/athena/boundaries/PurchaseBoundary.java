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
            if(retVal.equals("OK")) return true ;
            return false ;
        }
        catch (IOException e)
        {
            throw new Exception("To be implemented") ;
        }
    }
}
