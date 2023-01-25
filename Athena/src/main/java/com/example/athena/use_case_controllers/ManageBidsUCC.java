package com.example.athena.use_case_controllers;

import com.example.athena.beans.BidBean;
import com.example.athena.beans.PurchaseResultBean;
import com.example.athena.boundaries.PurchaseBoundary;
import com.example.athena.entities.BidEntity;
import com.example.athena.entities.BidStatusEnum;
import com.example.athena.entities.BookDao;
import com.example.athena.exceptions.BidException;
import com.example.athena.exceptions.PurchaseException;

import java.util.ArrayList;
import java.util.List;

public class ManageBidsUCC {
    private final BookDao dao = new BookDao();

    public void updateBid(BidBean bean) throws BidException {
        BidEntity entity = new BidEntity(bean.getOwner(), bean.getBidder(), bean.getNewPrice(), bean.getBookTimestamp(), bean.getBookIsbn(), BidStatusEnum.valueOf(bean.getStatus()));
        dao.updateBidStatus(entity);
    }

    public List<BidBean> getPlacedBids() throws BidException {
        List<BidEntity> entityBids = dao.getBidderBids();
        List<BidBean> beanBids = new ArrayList<>();
        for (BidEntity entity : entityBids) {
            BidBean bean = new BidBean();
            bean.setOwner(entity.getOwner());
            bean.setStatus(entity.getStatus().toString());
            bean.setBookTimestamp(entity.getBookTimestamp());
            bean.setBookIsbn(entity.getBookIsbn());
            bean.setBidder(entity.getBidder());
            bean.setNewPrice(entity.getNewPrice());
            beanBids.add(bean) ;
        }

        return beanBids ;
    }

    public void payBid(BidBean bean) throws BidException {
        try {
            PurchaseResultBean resultBean = PurchaseBoundary.purchase() ;
            if(!resultBean.getPurchaseResult()) throw new BidException("Purchase rejected") ;
        } catch (PurchaseException e) {
            throw new BidException("Purchase failed") ;
        }

        BidEntity entity = new BidEntity(bean.getOwner(), bean.getBidder(), bean.getNewPrice(), bean.getBookTimestamp(), bean.getBookIsbn(), BidStatusEnum.valueOf(bean.getStatus()));
        dao.payAcceptedBid(entity);
    }

    public void cancelBid(BidBean bean) throws BidException{
        BidEntity entity = new BidEntity(bean.getOwner(), bean.getBidder(), bean.getNewPrice(), bean.getBookTimestamp(), bean.getBookIsbn(), BidStatusEnum.valueOf(bean.getStatus()));
        dao.deleteBid(entity);
    }
}
