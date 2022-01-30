package com.example.athena.Boundaries;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class IsbnCheckBoundary {

    public static void check(String isbn){
        try{
                String url = "https://openlibrary.org/isbn/"+ isbn + ".json";
                String json = new Scanner(new URL(url).openStream(), StandardCharsets.UTF_8).useDelimiter("\\A").next();
                System.out.println(json);
        }
        catch (FileNotFoundException | MalformedURLException e)  {
                System.out.println("error");
        }
        catch (IOException e) {
                e.printStackTrace();
        }
    }
}
