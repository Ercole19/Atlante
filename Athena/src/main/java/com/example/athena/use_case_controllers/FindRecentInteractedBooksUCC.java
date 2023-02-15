package com.example.athena.use_case_controllers;

import com.example.athena.beans.FindRecentBooksBean;
import com.example.athena.beans.RecentBooksSearchResultBean;
import com.example.athena.entities.RecentInteractedBooksSearchResult;
import com.example.athena.exceptions.FindException;

import java.util.ArrayList;
import java.util.List;

public class FindRecentInteractedBooksUCC {

    public int a;
    public List<RecentBooksSearchResultBean> formatPurchasesResults(FindRecentBooksBean bean) throws FindException {
        List<RecentInteractedBooksSearchResult> list = RecentInteractedBooksSearchResult.getRecentPurchasedBooks(bean.getEmail());
        return getBeanList(list);
    }
    
    public List<RecentBooksSearchResultBean> formatSoldItemsResults(FindRecentBooksBean bean) throws FindException {
        List<RecentInteractedBooksSearchResult> list = RecentInteractedBooksSearchResult.getRecentSoldBooks(bean.getEmail());
        return getBeanList(list);
    }


    private List<RecentBooksSearchResultBean> getBeanList(List<RecentInteractedBooksSearchResult> list){
        List<RecentBooksSearchResultBean> bookList = new ArrayList<>();
        for (RecentInteractedBooksSearchResult book : list) {
            RecentBooksSearchResultBean bean = new RecentBooksSearchResultBean();
            if (book.getPurchaser() == null) bean.setOwner(book.getOwner());
            else bean.setPurchaser(book.getPurchaser());
            bean.setTitle(book.getTitle());
            bean.setIsbn(book.getIsbn());
            bean.setPrice(book.getPrice());
            bookList.add(bean);
        }
        return bookList;
    }
}
