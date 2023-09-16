package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.BookBean;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.graphical_controller.normal_interface.UpdatedShiftImageController;
import com.example.athena.graphical_controller.oracle_interface.sell_book_states.OnSelectNegotiabilityState;
import com.example.athena.graphical_controller.oracle_interface.sell_book_states.SellBookAbstractState;
import com.example.athena.use_case_controllers.ManageYourSellingBooksUCC;
import com.example.athena.view.oracle_view.SelectNegotiabilityView;
import com.example.athena.view.oracle_view.UploadImageView;

import java.util.ArrayList;

public class OracleSellBookGC extends UpdatedShiftImageController implements OnYesOrNoController {

    private String bookName;
    private String bookIsbn;
    private String bookPrice;
    private boolean negotiability ;

    private SellBookAbstractState current;

    private boolean decision ;

    public void sellBook(String name, String isbn, String price)  {
        this.bookName = name;
        this.bookIsbn = isbn;
        this.bookPrice = price;
        super.files = new ArrayList<>() ;
        super.images = new ArrayList<>() ;
        this.current = new OnSelectNegotiabilityState(this);

    }

    public void receiveNegotiability( SelectNegotiabilityView view) {
        this.negotiability = view.getCheckBoxResult();
        this.goNext() ;
    }

    public void getUploadControls(UploadImageView view) {
        super.bookImage = view.getBookImageView() ;
        super.leftArrowImage = view.getLeftArrowImage() ;
        super.rightArrowImage = view.getRightArrowImage() ;
        super.shiftIndex(-1);
    }

    public void putBookOnSale() throws BookException, ISBNException {
        BookBean bean = new BookBean() ;
        bean.setBookTitle(this.bookName) ;
        bean.setPrice(this.bookPrice) ;
        bean.setIsbn(this.bookIsbn) ;
        bean.setIsNegotiable(this.negotiability);
        bean.setImage(super.files) ;

        new ManageYourSellingBooksUCC().putOnSale(bean) ;
    }

    public void setState(SellBookAbstractState nextState) {
        this.current = nextState ;
    }

    public boolean getDecision() {
        return this.decision ;
    }

    public void goNext() {
        this.current.goNext(this) ;
    }

    public void leftArrowClick() {super.onLeftArrowClick();}
    public void rightArrowClick(){super.onRightArrowClick();}

    @Override
    public void onYes() {
        this.decision = true ;
        this.goNext() ;
    }

    @Override
    public void onNo() {
        this.decision = false ;
        this.goNext() ;
    }
}
