package com.example.athena.graphical_controller;

import java.io.File;

public class BookSearchResultBean {
    private String title;
    private String isbn;
    private Float price;
    private String owner ;
    private File file;

    public BookSearchResultBean(String title, String isbn, Float price, String owner, File image) {
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.owner = owner ;
        this.file = image;
    }


    public File getFile() {
        return file;
    }

    public Float getPrice() {
        return price;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getOwner()
    {
        return this.owner ;
    }

}
