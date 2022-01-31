package com.example.athena.boundaries;

import com.example.athena.exceptions.ISBNException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class IsbnCheckBoundary {


    private IsbnCheckBoundary() {

    }

    public static void isbnCheck(String isbn) throws ISBNException {
        String url = "https://openlibrary.org/isbn/"+ isbn + ".json" ;
        try(InputStream stream = new URL (url).openStream()){
            String json = new Scanner( stream , StandardCharsets.UTF_8).useDelimiter("\\A").next();
        }
        catch (FileNotFoundException | MalformedURLException e)  {
            e.getMessage() ;
            throw new ISBNException("Submitted ISBN does not exist");
        }
        catch (IOException e) {
           e.printStackTrace();
        }
    }
}
