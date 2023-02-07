package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.BidBean;
import com.example.athena.beans.BookBean;
import com.example.athena.beans.PurchaseResultBean;
import com.example.athena.beans.StudentInfosBean;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.BidException;
import com.example.athena.exceptions.PurchaseException;
import com.example.athena.exceptions.StudentInfoException;
import com.example.athena.graphical_controller.normal_interface.ShiftImageController;
import com.example.athena.graphical_controller.oracle_interface.place_bid_states.OnBookPageBid;
import com.example.athena.graphical_controller.oracle_interface.place_bid_states.PlaceBidAbstractState;
import com.example.athena.use_case_controllers.GetStudentInfosUCC;
import com.example.athena.use_case_controllers.PurchaseUCC;
import com.example.athena.use_case_controllers.ManageBidsUCC;
import com.example.athena.view.oracle_view.BookPageView;
import com.example.athena.view.oracle_view.LabelView;
import com.example.athena.view.oracle_view.OnPlaceBidView;

public class OracleBookPageGC extends ShiftImageController {
    private BookBean bean;
    private PlaceBidAbstractState state ;

    public OracleBookPageGC(BookBean bean) {
        this.bean = bean;
        if (this.bean.getNegotiable()) this.state = new OnBookPageBid();
    }

    public void populateView(BookPageView view) {
        try {
            StudentInfosBean infosBean = new StudentInfosBean();
            GetStudentInfosUCC bookPageUCC = new GetStudentInfosUCC();
            infosBean.setStudent(bean.getOwner());
            bookPageUCC.getStudentInfos(infosBean);

            view.getSellerName().setText("Seller name :   " + infosBean.getFullName()[0]);
            view.getSellerSurname().setText("Seller surname :   " + infosBean.getFullName()[1]);
            view.getSellerEmail().setText("Seller email :    " + infosBean.getStudent());
            view.getTotalRepNumber().setText("Seller report number :    " + infosBean.getRepNum());
            view.getPrice().setText("Book price :   "  +  bean.getPrice());
            view.getNegotiability().setText("Negotiable :    " + bean.getNegotiable());
            view.getTitle().setText("Book title :   " + bean.getBookTitle());

            if (bean.getNegotiable()) {
                view.getButton().setText("Place bid");
                view.getButton().setOnAction(event -> this.state.goNext(this));
            }
            else {
                view.getButton().setText("Buy");
                view.getButton().setOnAction(event -> purchaseBook());
            }


        }
        catch (StudentInfoException e ) {
            ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Error in connecting to server"));
        }

    }

    public void goNext() {
        this.state.goNext(this) ;
    }

    public void intiGraphicElem(BookPageView view) {
        super.bookImage = view.getBookImageView() ;
        super.leftArrowImage = view.getLeftArrowImage() ;
        super.rightArrowImage = view.getRightArrowImage() ;
        super.images = bean.getImageList();
        if(!(this.bean.getImageList().isEmpty())){
            super.bookImage.setImage(this.bean.getImageList().get(0));
        }
        super.shiftIndex(-1);
    }

    public void sendBid(OnPlaceBidView view) {
        BidBean bidBean = new BidBean();
        bidBean.setNewPrice(view.getField().getText());
        bidBean.setOwner(bean.getOwner());
        bidBean.setBidder(LoggedStudent.getInstance().getEmail().getMail());
        bidBean.setBookIsbn(bean.getIsbn());
        bidBean.setBookTimestamp(bean.getTimeStamp());
        bidBean.setStatus("PENDING");
        try {
            new ManageBidsUCC().placeBid(bidBean);
            ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Bid placed"));
        }
        catch (BidException e) {
            ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Error in placing bid, details follow :" + e.getMessage()));
        }

    }

    public void purchaseBook() {
            PurchaseUCC controller = new PurchaseUCC();
            try {
                PurchaseResultBean purchaseResultBean = controller.purchase(this.bean);
                if (purchaseResultBean.getPurchaseResult()) {
                    ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Your purchase has been made! You can see it in recent purchase window."));
                } else {
                    ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Your purchase has been declined"));
                }
            } catch (PurchaseException e) {
                ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent(e.getMessage()));
            }
        }

    public void setState(PlaceBidAbstractState state) {
        this.state = state ;
    }


    @Override
    public void rightArrowClick() {
        super.onRightArrowClick();
    }

    @Override
    public void leftArrowClick() {
        super.onLeftArrowClick();
    }
}
