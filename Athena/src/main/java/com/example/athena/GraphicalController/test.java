package com.example.athena.GraphicalController;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class test {

    public static void main(String[] args){
        try{
            String url = "https://openlibrary.org/isbn/8835953588.json";
            String json = new Scanner(new URL(url).openStream(), StandardCharsets.UTF_8).useDelimiter("\\A").next();
            System.out.println(json);
        }
        catch (FileNotFoundException | MalformedURLException e)  {
            System.out.println("error");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
