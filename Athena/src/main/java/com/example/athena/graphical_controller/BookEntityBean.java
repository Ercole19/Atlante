package com.example.athena.graphical_controller;

import com.example.athena.exceptions.BookException;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class BookEntityBean {

    private final String title ;
    private final String isbn;
    private final String price ;
    private final Boolean isNegotiable ;
    private final List<File> image;
    private String owner;

    public BookEntityBean(String title , String isbn, String price, Boolean isNegotiable, List<File> image, String owner)throws BookException{
        sintacticCheck(isbn, price);
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.isNegotiable = isNegotiable;
        this.image = image;
        this.owner = owner ;
    }

    public List<Image> getImageList(){
        List<Image> imageList = new ArrayList<>();
        for (File bookImage : this.image){
            imageList.add(new Image(new File(bookImage.getAbsolutePath()).toURI().toString()));
        }
        return imageList;
    }


    public String getBookTitle() {
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

    public List<File> getImage() {return this.image; }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void sintacticCheck(String isbn, String price) throws BookException
    {
        if (!((isbn.matches("[0-9]{10}") || isbn.matches("[0-9]{13}")) && price.matches("[0-9]+[,.][0-9]*"))){
            throw new BookException("Error in ISBN or price formats") ;
    }
}}
