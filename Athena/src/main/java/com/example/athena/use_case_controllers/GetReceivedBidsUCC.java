package com.example.athena.use_case_controllers;

import com.example.athena.beans.BidBean;
import com.example.athena.entities.BidEntity;
import com.example.athena.exceptions.BidException;

import java.util.ArrayList;
import java.util.List;

public class GetReceivedBidsUCC {

    public List<BidBean> getBids(BidBean bean) throws BidException {
        List<BidBean> beanList = new ArrayList<>();
        List<BidEntity> entityList = new BidEntity().getBidsOfABook(bean.getOwner(), bean.getBookIsbn(), bean.getBookTimestamp());

        for (BidEntity bid : entityList) {
            BidBean bidBean = new BidBean() ;
            bidBean.setBookTimestamp(bid.getBookTimestamp());
            bidBean.setOwner(bid.getOwner());
            bidBean.setBookIsbn(bid.getBookIsbn());
            bidBean.setBidder(bid.getBidder());
            bidBean.setStatus(bid.getStatus().toString().charAt(0) + bid.getStatus().toString().substring(1).toLowerCase().replace('_', ' '));
            bidBean.setNewPrice(bid.getNewPrice());

            beanList.add(bidBean) ;
        }

        return beanList ;
    }

}