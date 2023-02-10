package com.example.athena.dao;


import com.example.athena.entities.RecentInteractedBooksSearchResult;
import com.example.athena.exceptions.FindException;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecentInteractedItemsDAO extends AbstractDAO {

    public List<RecentInteractedBooksSearchResult> getRecentPurchasesResults(String email) throws FindException {
        List<RecentInteractedBooksSearchResult> searchResultList = new ArrayList<>();
        try(PreparedStatement statement = this.getConnection().prepareStatement("SELECT vendorEmail, bookTitle, bookIsbn, bookPrice from athena.recent_purchases where purchaserEmail = ?")) {

            statement.setString(1, email);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                RecentInteractedBooksSearchResult book = new RecentInteractedBooksSearchResult(set.getString(2), set.getString(3), set.getString(4), set.getString(1), true);
                searchResultList.add(book);
            }

        }catch (SQLException | IOException exc) {
            throw new FindException(exc.getMessage()) ;
        }
        return searchResultList;
    }


    public List<RecentInteractedBooksSearchResult> getRecentSoldItemsFromDB(String vendorEmail) throws FindException{
        List<RecentInteractedBooksSearchResult> searchResultList = new ArrayList<>();
        try(PreparedStatement statement = this.getConnection().prepareStatement("SELECT purchaserEmail, bookTitle, bookIsbn, bookPrice from athena.recent_purchases where vendorEmail = ?")) {

            statement.setString(1, vendorEmail);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                RecentInteractedBooksSearchResult book = new RecentInteractedBooksSearchResult(set.getString(2), set.getString(3), set.getString(4), set.getString(1), false);
                searchResultList.add(book);
            }

        }catch (SQLException | IOException exc) {
            throw new FindException(exc.getMessage()) ;
        }
        return searchResultList;
    }
}
