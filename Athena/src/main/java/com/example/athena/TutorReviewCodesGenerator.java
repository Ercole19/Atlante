package com.example.athena;

import java.util.Random;

public class TutorReviewCodesGenerator
{
    public static String generateReviewCode(int length)
    {
        Random random = new Random() ;
        String charsPool = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789" ;
        char[] code = new char[length] ;
        for(int i = 0; i < length; i++)
        {
            code[i] = charsPool.charAt(random.nextInt(charsPool.length())) ;
        }

        return new String(code) ;
    }
}
