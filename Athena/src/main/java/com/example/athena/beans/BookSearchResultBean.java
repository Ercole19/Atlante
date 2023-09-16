package com.example.athena.beans;

import java.io.File;

public class BookSearchResultBean {
    private String title;
    private String isbn;
    private Float price;
    private String owner ;
    private File file;

    public void setFile(File file) {
        this.file = file;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
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
