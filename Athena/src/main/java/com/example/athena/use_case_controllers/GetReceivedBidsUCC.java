package com.example.athena.use_case_controllers;

import com.example.athena.beans.BidBean;
import com.example.athena.beans.BookBean;
import com.example.athena.dao.BookDao;
import com.example.athena.entities.BidEntity;
import com.example.athena.exceptions.BidException;
import com.example.athena.exceptions.BookException;

import java.util.ArrayList;
import java.util.List;

public class GetReceivedBidsUCC {

    public List<BidBean> getBids(BookBean bean) throws BookException, BidException {
        BookDao dao = new BookDao();
        List<BidBean> beanList = new ArrayList<>();
        List<BidEntity> entityList = dao.getBookBids(bean.getOwner(), bean.getIsbn(), bean.getTimeStamp());

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

    public BidBean getAcceptedBid(String seller, String isbn, String timestamp) throws BidException {
        BookDao dao = new BookDao();
        BidEntity bid = dao.getAcceptedBid(seller, isbn, timestamp);
        BidBean bean = new BidBean();
        bean.setOwner(bid.getOwner());
        bean.setBidder(bid.getBidder());
        bean.setBookTimestamp(bid.getBookTimestamp());
        bean.setNewPrice(bid.getNewPrice());
        bean.setBookIsbn(bid.getBookIsbn());
        bean.setStatus(bid.getStatus().toString());
        return bean;
    }
}
