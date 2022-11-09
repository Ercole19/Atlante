package com.example.athena.use_case_controllers;

import com.example.athena.beans.normal.BidBean;
import com.example.athena.entities.BidEntity;
import com.example.athena.entities.BidStatusEnum;
import com.example.athena.entities.BookDao;
import com.example.athena.exceptions.BidException;

public class ManageBidsUCC {
    
    public void updateBid(BidBean bean) throws BidException {
        BookDao dao = new BookDao();
        BidEntity entity = new BidEntity(bean.getOwner(), bean.getBidder(), bean.getNewPrice(), bean.getBookTimestamp(), bean.getBookIsbn(), BidStatusEnum.valueOf(bean.getStatus()));
        dao.updateBidStatus(entity);
    }
}
