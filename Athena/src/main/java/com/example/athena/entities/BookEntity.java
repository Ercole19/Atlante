package com.example.athena.entities;

import java.io.File;
import java.util.List;

public class BookEntity {

    private final String title ;
    private final String isbn  ;
    private final float price   ;
    private final  Boolean isNegotiable;
    private final List<File> image ;
    private final File file  ;
    private final String owner ;
    private final String purchaser;
    private final String saleTimestamp;



    public BookEntity(String title , String isbn, float price, Boolean isNegotiable, List<File> image, String owner){
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.isNegotiable = isNegotiable;
        this.image = image;
        this.file = null ;
        this.owner = owner ;
        this.purchaser = null;
        this.saleTimestamp = null;
    }

    public BookEntity(String title, String isbn, float price, String email,  boolean isNegotiable, List<File> image, String saleTimestamp) {
        this.title = title ;
        this.isbn = isbn ;
        this.price = price ;
        this.owner = email ;
        this.file = null ;
        this.image = image ;
        this.isNegotiable = isNegotiable ;
        this.purchaser = null;
        this.saleTimestamp = saleTimestamp;
    }

    public BookEntity (String owner, String title, String isbn, Float price) {
        this.owner = owner;
        this.title  = title;
        this.isbn = isbn ;
        this.price = price;
        this.isNegotiable = null;
        this.image = null;
        this.file = null;
        this.purchaser = null;
        this.saleTimestamp = null;

    }

    public BookEntity (String title, String isbn, Float price, String purchaser) {
        this.owner = null;
        this.title  = title;
        this.isbn = isbn ;
        this.price = price;
        this.isNegotiable = null;
        this.image = null;
        this.file = null;
        this.purchaser = purchaser;
        this.saleTimestamp = null;

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

    public List<File> getImage() {return this.image;}

    public String getOwner() {
        return this.owner;
    }

    public File getFile() {
        return this.file;
    }

    public String getPurchaser() {return this.purchaser;}

    public String getSaleTimestamp() {return this.saleTimestamp ;}

}
