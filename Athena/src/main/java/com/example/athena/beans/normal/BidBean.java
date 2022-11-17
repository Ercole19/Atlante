package com.example.athena.beans.normal;

import com.example.athena.entities.BidStatusEnum;
import com.example.athena.exceptions.BidException;

public class BidBean {
    private String owner;
    private String bidder;
    private String newPrice;
    private String bookTimestamp;
    private String bookIsbn;
    private String status;

    private void syntacticCheckPrice(String price)throws BidException {
        if (!(price.matches("\\d{1,3}\\.\\d{2}"))){
            throw new BidException("Error in price format");
        }
    }


    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public void setBookTimestamp(String bookTimestamp) {
        this.bookTimestamp = bookTimestamp;
    }

    public void setNewPrice(String newPrice) throws BidException{
        syntacticCheckPrice(newPrice);
        this.newPrice = newPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public String getBidder() {
        return bidder;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public String getBookTimestamp() {
        return bookTimestamp;
    }

    public String getStatus() {
        return status;
    }
}
