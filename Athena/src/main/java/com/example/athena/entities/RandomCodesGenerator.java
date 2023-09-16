package com.example.athena.entities;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class RandomCodesGenerator
{
    private RandomCodesGenerator(){

    }


    public static String generateRandomCode(int length) throws NoSuchAlgorithmException
    {
        Random random = SecureRandom.getInstanceStrong();
        String charsPool = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789" ;
        char[] code = new char[length] ;
        for(int i = 0; i < length; i++)
        {
            code[i] = charsPool.charAt(random.nextInt(charsPool.length())) ;
        }

        return new String(code) ;
    }
}
