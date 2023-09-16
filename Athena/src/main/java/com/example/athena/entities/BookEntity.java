package com.example.athena.entities;

import com.example.athena.dao.BookDao;
import com.example.athena.exceptions.FindException;

import java.io.File;
import java.util.List;

public class BookEntity {

    private String title ;
    private String isbn  ;
    private String price   ;
    private Boolean isNegotiable;
    private List<File> image ;
    private String owner ;
    private String saleTimestamp;

    public BookEntity () {}

    public BookEntity(String title , String isbn, String price, String owner, String saleTimestamp){
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.owner = owner ;
        this.saleTimestamp = saleTimestamp;
    }

    public BookEntity(String title , String isbn, String price, Boolean isNegotiable, List<File> image, String owner, String saleTimestamp){
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.isNegotiable = isNegotiable;
        this.image = image;
        this.owner = owner ;
        this.saleTimestamp = saleTimestamp;
    }

    public BookEntity(String bookTitle, String isbn, String price, Boolean negotiable, List<File> image, String saleTimestamp) {
        this.title = bookTitle;
        this.isbn = isbn;
        this.price = price;
        this.isNegotiable = negotiable;
        this.image = image;
        this.saleTimestamp = saleTimestamp;
    }


    public List<BookEntity> getBooksFromQuery(String query) throws FindException {
        return new BookDao().findBooksWImages(query);
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

    public String getSaleTimestamp() {return this.saleTimestamp ;}

}
