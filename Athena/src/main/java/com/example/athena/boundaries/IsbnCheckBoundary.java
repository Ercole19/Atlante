package com.example.athena.boundaries;

import com.example.athena.beans.ISBNBean;
import com.example.athena.beans.ISBNCheckResultBean;
import com.example.athena.exceptions.ISBNException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class IsbnCheckBoundary {


    private IsbnCheckBoundary() {

    }

    public static ISBNCheckResultBean isbnCheck(ISBNBean bean) throws ISBNException {
        String url = "https://openlibrary.org/isbn/"+ bean.getISBN() + ".json" ;
        ISBNCheckResultBean checkResultBean = new ISBNCheckResultBean();
        try(InputStream stream = new URL (url).openStream()){
            new Scanner( stream , StandardCharsets.UTF_8).useDelimiter("\\A").next();
            checkResultBean.setResult(true);
        }
        catch (FileNotFoundException e)  {
            checkResultBean.setResult(false) ;
        }
        catch (IOException e) {
            throw new ISBNException("Unable to connect to server") ;
        }
        return checkResultBean;
    }
}