package com.example.athena.graphical_controller;

import com.example.athena.exceptions.BookException;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BookBean {

    private String title ;
    private String isbn ;
    private String price ;
    private Boolean isNegotiable ;
    private List<File> image ;
    private String owner ;
    private int index ;
    private String purchaser;



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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn)throws BookException {
        syntacticCheckIsbn(isbn);
        this.isbn = isbn;
    }

    public void setPrice(String price)throws BookException {
        syntacticCheckPrice(price);
        this.price = price;
    }

    public void setIsNegotiable(Boolean isNegotiable) {
        this.isNegotiable = isNegotiable;
    }

    public void setImage(List<File> image) {
        this.image = image;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void syntacticCheckIsbn(String isbn) throws BookException
    {
        if (!(isbn.matches("\\d{9}[\\dX]") || isbn.matches("\\d{13}"))){
            throw new BookException("Error in ISBN format") ;
        }
    }

    public void syntacticCheckPrice(String price)throws BookException{
        if (!(price.matches("\\d+\\.*\\d*"))){
            throw new BookException("Error in price format");
        }
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setNegotiable(Boolean negotiable) {
        isNegotiable = negotiable;
    }

    public String getTitle() {
        return title;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public String getPurchaser() {
        return purchaser;
    }
}
