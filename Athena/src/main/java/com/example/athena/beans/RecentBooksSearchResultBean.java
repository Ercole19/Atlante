package com.example.athena.beans;

public class RecentBooksSearchResultBean {
    private String isbn ;
    private String title;
    private String price ;
    private String owner ;
    private String purchaser ;

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public String getPrice() {
        return price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
