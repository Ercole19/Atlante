package com.example.athena.View;

public class LastCodeRememberer
{
    private static String lastCode ;

    private LastCodeRememberer()
    {

    }

    public static void writeLastCode(String code)
    {
        lastCode = code ;
    }

    public static String getLastCode()
    {
        return lastCode ;
    }
}
