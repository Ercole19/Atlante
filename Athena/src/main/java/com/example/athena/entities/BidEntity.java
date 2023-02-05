package com.example.athena.entities;

import com.example.athena.dao.BidDAO;
import com.example.athena.exceptions.BidException;

import java.util.List;

public class BidEntity {

    private String owner;
    private String bidder;
    private String newPrice;
    private String bookTimestamp;
    private String bookIsbn;
    private BidStatusEnum status;

    public BidEntity() {

    }

    public BidEntity (String owner, String bidder, String newPrice, String bookTimestamp, String bookIsbn, BidStatusEnum status) {
        this.owner = owner;
        this.bidder = bidder;
        this.newPrice = newPrice;
        this.bookTimestamp = bookTimestamp;
        this.bookIsbn = bookIsbn;
        this.status = status;
    }

    public void placeBid() throws BidException {
        new BidDAO().addBookBid(this);
    }

    public List<BidEntity> getPlacedBids() throws BidException {
        return new BidDAO().getBidderBids();
    }

    public void updateBidStatus() throws BidException {
        new BidDAO().updateBidStatus(this);
    }

    public void deleteBid() throws BidException {
        new BidDAO().deleteBid(this);
    }

    public void payBid() throws BidException {
        new BidDAO().payAcceptedBid(this);
    }

    public List<BidEntity> getBidsOfABook(String seller, String isbn, String timestamp) throws BidException {
       return new BidDAO().getBookBids(seller, isbn, timestamp);
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