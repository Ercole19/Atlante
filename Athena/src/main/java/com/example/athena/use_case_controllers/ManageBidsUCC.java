package com.example.athena.use_case_controllers;

import com.example.athena.beans.BidBean;
import com.example.athena.beans.PurchaseResultBean;
import com.example.athena.boundaries.PurchaseBoundary;
import com.example.athena.entities.BidEntity;
import com.example.athena.entities.BidStatusEnum;
import com.example.athena.exceptions.BidException;
import com.example.athena.exceptions.PurchaseException;

public class ManageBidsUCC {


    public void placeBid(BidBean bidBean) throws BidException {
        BidEntity bid = new BidEntity(bidBean.getOwner(), bidBean.getBidder(), bidBean.getNewPrice(), bidBean.getBookTimestamp(), bidBean.getBookIsbn(), BidStatusEnum.valueOf(bidBean.getStatus()));
        bid.placeBid();
    }

    public void cancelBid(BidBean bean) throws BidException{
        BidEntity entity = new BidEntity(bean.getOwner(), bean.getBidder(), bean.getNewPrice(), bean.getBookTimestamp(), bean.getBookIsbn(), BidStatusEnum.valueOf(bean.getStatus()));
        entity.deleteBid();
    }

    public void updateBidStatus(BidBean bean) throws BidException {
        BidEntity entity = new BidEntity(bean.getOwner(), bean.getBidder(), bean.getNewPrice(), bean.getBookTimestamp(), bean.getBookIsbn(), BidStatusEnum.valueOf(bean.getStatus()));
        entity.updateBidStatus();
    }

    public void payBid(BidBean bean) throws BidException {
        try {
            PurchaseResultBean resultBean = PurchaseBoundary.purchase() ;
            if(!resultBean.getPurchaseResult()) throw new BidException("Purchase rejected") ;
        } catch (PurchaseException e) {
            throw new BidException("Purchase failed") ;
        }

        BidEntity entity = new BidEntity(bean.getOwner(), bean.getBidder(), bean.getNewPrice(), bean.getBookTimestamp(), bean.getBookIsbn(), BidStatusEnum.valueOf(bean.getStatus()));
        entity.payBid();
    }

}