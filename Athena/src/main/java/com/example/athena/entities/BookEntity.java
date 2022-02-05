package com.example.athena.entities;

import java.io.File;

public class BookEntity {

    private final String title ;
    private final String isbn;
    private final float price ;
    private final Boolean isNegotiable ;

    public BookEntity(String title , String isbn, float price, Boolean isNegotiable){
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

    public float getPrice(){
        return this.price;
    }

    public Boolean getNegotiable() {
        return this.isNegotiable;
    }

    public void toDB()
    {
        BookDao dao = new BookDao() ;
        dao.insertBook(this.getBookTitle(), this.getIsbn(), this.getPrice(), this.getNegotiable()) ;
    }
}
