package com.example.athena.entities;

public class BidEntity {

    private String owner;
    private String bidder;
    private String newPrice;
    private String bookTimestamp;
    private String bookIsbn;
    private BidStatusEnum status;

    public BidEntity (String owner, String bidder, String newPrice, String bookTimestamp, String bookIsbn, BidStatusEnum status) {
        this.owner = owner;
        this.bidder = bidder;
        this.newPrice = newPrice;
        this.bookTimestamp = bookTimestamp;
        this.bookIsbn = bookIsbn;
        this.status = status;
    }

    public String getOwner(){
        return owner;
    }

    public String getBidder() {
        return bidder;
    }

    public String getBookTimestamp() {
        return bookTimestamp;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public BidStatusEnum getStatus() {
        return status;
    }
}