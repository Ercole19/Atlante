package com.example.athena.Boundaries;

import com.example.athena.Exceptions.ISBNException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class IsbnCheckBoundary {

    public static void ISBNcheck(String isbn) throws ISBNException {
        try{
                String url = "https://openlibrary.org/isbn/"+ isbn + ".json";
                String json = new Scanner(new URL(url).openStream(), StandardCharsets.UTF_8).useDelimiter("\\A").next();
                System.out.println(json);
        }
        catch (FileNotFoundException | MalformedURLException e)  {
            System.out.println(e.getMessage()) ;
            throw new ISBNException("Submitted ISBN does not exist");
        }
        catch (IOException e) {
                e.printStackTrace();
        }
    }
}
