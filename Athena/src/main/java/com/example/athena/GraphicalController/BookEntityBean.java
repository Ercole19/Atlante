package com.example.athena.GraphicalController;

public class BookEntityBean {

    private final String title ;
    private final String ISBN ;
    private final float price ;
    private final Boolean isNegotiable ;

    public BookEntityBean(String title , String ISBN, float price, Boolean isNegotiable){
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

    public float getPrice(){
        return this.price;
    }

    public Boolean getNegotiable() {
        return this.isNegotiable;
    }
}
