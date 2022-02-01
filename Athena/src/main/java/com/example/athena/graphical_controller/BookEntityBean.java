package com.example.athena.graphical_controller;

import com.example.athena.exceptions.BookException;

public class BookEntityBean {

    private final String title ;
    private final String isbn;
    private final String price ;
    private final Boolean isNegotiable ;

    public BookEntityBean(String title , String isbn, String price, Boolean isNegotiable)throws BookException{
        sintacticCheck(isbn, price);
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.isNegotiable = isNegotiable;
    }

    public String getBookTitle() {
        return this.title;
    }

    public String getIsbn(){
        return this.isbn;
    }

    public String getPrice(){
        return this.price;
    }

    public Boolean getNegotiable() {
        return this.isNegotiable;
    }

    public void sintacticCheck(String isbn, String price) throws BookException
    {
        if (!((isbn.matches("[0-9]{10}") || isbn.matches("[0-9]{13}")) && price.matches("[0-9]+[,.][0-9]*"))){
            throw new BookException("Error in ISBN or price formats") ;
    }
}}