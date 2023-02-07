package com.example.athena.dao;

import com.example.athena.entities.BidEntity;
import com.example.athena.entities.BidStatusEnum;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.BidException;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BidDAO extends AbstractDAO {
    public void addBookBid(BidEntity bid) throws BidException {
        try(PreparedStatement statement = this.getConnection().prepareStatement("CALL add_new_bid (?,?,?,?,?)")){
            statement.setString(1, bid.getOwner());
            statement.setString(2,bid.getBidder());
            statement.setString(3, bid.getNewPrice());
            statement.setString(4, bid.getBookIsbn());
            statement.setTimestamp(5, Timestamp.valueOf(bid.getBookTimestamp()));

            statement.execute();
        }
        catch (SQLException | IOException e){
            throw new BidException(e.getMessage());
        }
    }

    public List<BidEntity> getBookBids(String seller, String isbn, String timeStamp) throws BidException{
        try(PreparedStatement statement = this.getConnection().prepareStatement("SELECT * FROM athena.books_bids WHERE Seller = ? AND BookIsbn = ? AND BookTimestamp = ? AND BidStatus != 'REJECTED'")){

            statement.setString(1, seller) ;
            statement.setString(2, isbn);
            statement.setTimestamp(3, Timestamp.valueOf(timeStamp)) ;
            ResultSet set = statement.executeQuery();
            return extractBidsFromResultSet(set) ;
        }
        catch (SQLException | IOException e){
            throw new BidException(e.getMessage());
        }
    }

    public List<BidEntity> getBidderBids() throws BidException{
        try(PreparedStatement statement = this.getConnection().prepareStatement("SELECT * FROM athena.books_bids WHERE Bidder = ?")){
            statement.setString(1, LoggedStudent.getInstance().getEmail().getMail()) ;
            ResultSet set = statement.executeQuery() ;
            return extractBidsFromResultSet(set) ;
        }
        catch (SQLException | IOException e){
            throw new BidException(e.getMessage());
        }
    }

    private List<BidEntity> extractBidsFromResultSet(ResultSet set) throws SQLException {

        List<BidEntity> retVal = new ArrayList<>() ;

        while (set.next()) {
            BidEntity bid = new BidEntity(set.getString(1),
                    set.getString(2),
                    set.getString(5),
                    set.getTimestamp(4).toString(),
                    set.getString(3),
                    BidStatusEnum.valueOf(set.getString(6))) ;
            retVal.add(bid) ;
        }

        return retVal ;
    }

    public void deleteBid(BidEntity entity) throws BidException {
        try(PreparedStatement statement = this.getConnection().prepareStatement("DELETE FROM athena.books_bids WHERE Seller = ? AND Bidder = ? AND BookIsbn = ? AND BookTimeStamp = ? ")){
            statement.setString(1, entity.getOwner());
            statement.setString(2, entity.getBidder());
            statement.setString(3, entity.getBookIsbn());
            statement.setTimestamp(4, Timestamp.valueOf(entity.getBookTimestamp()));
            statement.execute() ;
        }
        catch (SQLException | IOException e){
            throw new BidException(e.getMessage());
        }
    }

    public void updateBidStatus(BidEntity entity) throws BidException {
        try(PreparedStatement statement = this.getConnection().prepareStatement("call athena.update_status(?,?,?,?,?)")){
            statement.setString(1, entity.getStatus().toString());
            statement.setString(2, entity.getOwner());
            statement.setString(3, entity.getBidder());
            statement.setString(4, entity.getBookIsbn());
            statement.setTimestamp(5, Timestamp.valueOf(entity.getBookTimestamp()));
            statement.execute() ;
        }
        catch (SQLException | IOException e){
            throw new BidException(e.getMessage());
        }
    }

    public void payAcceptedBid(BidEntity bid) throws BidException {
        try(PreparedStatement statement = this.getConnection().prepareStatement("CALL finalize_accept_bid(?,?,?,?,?)")){
            statement.setString(1, bid.getOwner());
            statement.setString(2, bid.getBidder());
            statement.setString(3, bid.getBookIsbn());
            statement.setTimestamp(4, Timestamp.valueOf(bid.getBookTimestamp()));
            statement.setString(5, bid.getNewPrice());
            statement.execute() ;
        }
        catch (SQLException | IOException e){
            throw new BidException(e.getMessage());
        }
    }
}
