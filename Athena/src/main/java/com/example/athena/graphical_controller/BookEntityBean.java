package com.example.athena.graphical_controller;

import com.example.athena.exceptions.BookException;

public class BookEntityBean {

    private final String title ;
    private final String ISBN ;
    private final String price ;
    private final Boolean isNegotiable ;

    public BookEntityBean(String title , String ISBN, String price, Boolean isNegotiable)throws BookException{
        sintacticCheck(ISBN, price);
        this.title = title;
        this.ISBN = ISBN;
        this.price = price;
        this.isNegotiable = isNegotiable;
    }

    public String getBookTitle() {
        return this.title;
    }

    public String getISBN(){
        return this.ISBN;
    }

    public String getPrice(){
        return this.price;
    }

    public Boolean getNegotiable() {
        return this.isNegotiable;
    }

    public void sintacticCheck(String ISBN, String price) throws BookException
    {
        if (!((ISBN.matches("[0-9]{10}") || ISBN.matches("[0-9]{13}")) && price.matches("[0-9]+[,.][0-9]*"))){
            throw new BookException("Error in ISBN or price formats") ;
    }
}}
