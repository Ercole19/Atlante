package com.example.athena.use_case_controllers;

import com.example.athena.beans.BidBean;
import com.example.athena.entities.BidEntity;
import com.example.athena.exceptions.BidException;

import java.util.ArrayList;
import java.util.List;

public class SeePlacedBidsUCC {
    public List<BidBean> getPlacedBids() throws BidException {
        List<BidEntity> entityBids = new BidEntity().getPlacedBids();
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
}
