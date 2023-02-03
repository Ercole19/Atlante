package com.example.athena.entities;

import com.example.athena.dao.RecentInteractedItemsDAO;
import com.example.athena.exceptions.FindException;
import java.util.List;

public class RecentInteractedBooksSearchResult {
    private String isbn ;
    private String title;
    private String price ;
    private String owner ;
    private String purchaser ;

    public RecentInteractedBooksSearchResult(String title, String isbn, String price, String email, boolean cond) {
        this.isbn = isbn;
        this.title = title;
        if (cond) this.owner = email;
        else this.purchaser = email;
        this.price = price;
    }

    public static List<RecentInteractedBooksSearchResult> getRecentSoldBooks(String email) throws FindException {
        return new RecentInteractedItemsDAO().getRecentSoldItemsFromDB(email);
    }

    public static List<RecentInteractedBooksSearchResult> getRecentPurchasedBooks(String email) throws FindException{
        return new RecentInteractedItemsDAO().getRecentPurchasesResults(email);
    }

    public String getOwner() {
        return owner;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public String getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

}
