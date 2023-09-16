package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.BidBean;
import com.example.athena.beans.BookBean;
import com.example.athena.entities.PersonalBookShelf;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;
import com.example.athena.graphical_controller.oracle_interface.show_received_bids_states.OnSelectWhichBookToShowState;
import com.example.athena.graphical_controller.oracle_interface.show_received_bids_states.ShowReceivedAbstractState;
import com.example.athena.view.oracle_view.LabelView;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class OracleShowReceivedBidsGC {
    private final List<BookBean> list = new ArrayList<>();
    private ShowReceivedAbstractState state;
    private BookBean selectedBean;

    public void getBids(String isbn) {
        try {
            int count = countBeans(isbn);
            if (count > 1) this.state = new OnSelectWhichBookToShowState(this);
            else {
                showBids(this.list.get(0));
            }
        }
        catch (BookException | UserInfoException e) {
            ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Error in show received bids call, details follow: " + e.getMessage()));
        }

    }

    public List<BookBean> getList() {
        return this.list;
    }

    public void setSelectedBean(BookBean bean) {
       this.selectedBean = bean;
       this.state.goNext(this);
    }

    public BookBean getSelectedBean() {return this.selectedBean;}

    public void setState(ShowReceivedAbstractState state) {
        this.state = state ;
    }

    public void showBids(BookBean bookBean) {
        ArrayList<Object> params = new ArrayList<>();
        BidBean bean = new BidBean();
        bean.setOwner(bookBean.getOwner());
        bean.setBookIsbn(bookBean.getIsbn());
        bean.setBookTimestamp(bookBean.getTimeStamp());
        params.add(bean);
        ParentSubject.getInstance().setCurrentParent(SceneSwitcher.getInstance().preload("OracleManageBids.fxml", params));
    }


    private int countBeans(String isbn) throws BookException, UserInfoException {
        ObservableList<BookBean> beanList = PersonalBookShelf.getInstance().getBooksBeansList();
        for (BookBean book : beanList) {
            if (book.getIsbn().equals(isbn) && book.getNegotiable()) {
                this.list.add(book);
            }
        }
        return list.size() ;
    }
}
