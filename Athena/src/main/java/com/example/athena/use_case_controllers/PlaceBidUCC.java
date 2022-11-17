package com.example.athena.use_case_controllers;

import com.example.athena.beans.normal.BidBean;
import com.example.athena.entities.BidEntity;
import com.example.athena.entities.BidStatusEnum;
import com.example.athena.entities.BooksSubject;
import com.example.athena.exceptions.BidException;

public class PlaceBidUCC {
    public void placeBid(BidBean bidBean) throws BidException {
        BidEntity bid = new BidEntity(bidBean.getOwner(), bidBean.getBidder(), bidBean.getNewPrice(), bidBean.getBookTimestamp(), bidBean.getBookIsbn(), BidStatusEnum.valueOf(bidBean.getStatus()));
        BooksSubject.getInstance().addBid(bid);
    }
}
