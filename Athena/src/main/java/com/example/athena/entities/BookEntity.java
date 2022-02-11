package com.example.athena.entities;

import java.io.File;
import java.util.List;

public class BookEntity {

    private final String title ;
    private final String isbn;
    private final float price ;
    private final Boolean isNegotiable ;
    private final List<File> image;

    public BookEntity(String title , String isbn, float price, Boolean isNegotiable, List<File> image){
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.isNegotiable = isNegotiable;
        this.image = image;
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


    public void toDB()
    {
        BookDao dao = new BookDao() ;
       dao.insertBook(this.getBookTitle(), this.getIsbn(), this.getPrice(), this.getNegotiable(), this.getImage()) ;
    }

    public void updateInDB()
    {
        BookDao dao = new BookDao() ;
        dao.updateBookInfos(this.getBookTitle(), this.getIsbn(), this.getPrice(), this.getNegotiable() , this.getImage());
    }

    public void removeFromDB()
    {
        BookDao dao = new BookDao() ;
        dao.deleteBook(this.getIsbn()) ;
    }
}
