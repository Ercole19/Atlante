package com.example.athena.entities;

import java.io.File;
import java.util.List;

public class BookEntity {

    private final String title ;
    private final String isbn  ;
    private final String price   ;
    private final  Boolean isNegotiable;
    private final List<File> image ;
    private final File file  ;
    private String owner ;
    private String purchaser;
    private String saleTimestamp;
    boolean type;



    public BookEntity(String title , String isbn, String price, Boolean isNegotiable, List<File> image, String owner){
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.isNegotiable = isNegotiable;
        this.image = image;
        this.file = null ;
        this.owner = owner ;
        this.purchaser = null;
        this.saleTimestamp = null;
        this.type = true;
    }

    public BookEntity(String title , String isbn, String price, Boolean isNegotiable, List<File> image, String owner, String saleTimestamp){
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.isNegotiable = isNegotiable;
        this.image = image;
        this.file = null ;
        this.owner = owner ;
        this.purchaser = null;
        this.saleTimestamp = saleTimestamp;
        this.type = true;
    }

    public BookEntity(String title, String isbn, String price, String email,  boolean isNegotiable, List<File> image, String saleTimestamp) {
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



    public BookEntity (String title, String isbn, String price, String purchaser, int type) {
        this.owner = null;
        this.purchaser = null;
        this.title  = title;
        this.isbn = isbn ;
        this.price = price;
        this.isNegotiable = null;
        this.image = null;
        this.file = null;
        if (type == 1) this.owner = purchaser;
        else this.purchaser = purchaser;
        this.saleTimestamp = null;

    }



    public String getTitle() {
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

    public List<File> getImage() {return this.image;}

    public String getOwner() {
        return this.owner;
    }

    public File getFile() {
        return this.file;
    }

    public String getPurchaser() {return this.purchaser;}

    public String getSaleTimestamp() {return this.saleTimestamp ;}

    public void setSaleTimestamp(String timestamp) {
        this.saleTimestamp = timestamp;
    }

    public void setPurchaser(String purchaser) {this.purchaser = purchaser;}
}
