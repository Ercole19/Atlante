package com.example.athena.entities;

import java.io.File;
import java.util.List;

public class BookEntity {

    private final String title ;
    private final String isbn  ;
    private final float price   ;
    private final  Boolean isNegotiable;
    private final List<File> image  ;
    private final File file  ;
    private final String owner ;





    public BookEntity(String title , String isbn, float price, Boolean isNegotiable, List<File> image, String owner){
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.isNegotiable = isNegotiable;
        this.image = image;
        this.file = null ;
        this.owner = owner ;
    }

    public BookEntity(String title, String isbn, float price, String email, File image) {
        this.title = title ;
        this.isbn = isbn ;
        this.price = price ;
        this.owner = email ;
        this.file = image ;
        this.image = null ;
        this.isNegotiable = null ;

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

    public void toDB()
    {
        BookDao dao = new BookDao() ;
        int i = 1;
       dao.insertBook(this.getBookTitle(), this.getIsbn(), this.getPrice(), this.getNegotiable(), this.getImage(), i) ;
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
