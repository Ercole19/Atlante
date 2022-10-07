package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.normal.BookBean;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.graphical_controller.normal_interface.UpdatedShiftImageController;
import com.example.athena.graphical_controller.oracle_interface.sell_book_states.OnSelectNegotiabilityState;
import com.example.athena.graphical_controller.oracle_interface.sell_book_states.SellBookAbstractState;
import com.example.athena.use_case_controllers.SellBooksUseCaseController;
import com.example.athena.view.oracle_view.UploadImageView;

public class OracleSellBookGC extends UpdatedShiftImageController implements OnYesOrNoController {

    private String bookName;
    private String bookIsbn;
    private String bookPrice;
    private boolean negotiability ;

    private SellBookAbstractState current;

    private boolean decision ;

    public OracleSellBookGC () {

    }

    public void sellBook(String name, String isbn, String price)  {
        this.bookName = name;
        this.bookIsbn = isbn;
        this.bookPrice = price;
        this.current = new OnSelectNegotiabilityState(this);

    }

    public void receiveNegotiability(boolean setting) {
        this.negotiability = setting ;
        this.goNext() ;
    }

    public void getUploadControls(UploadImageView view) {
        super.bookImage = view.getBookImageView() ;
        super.leftArrow = view.getLeftArrowButton() ;
        super.leftArrowImage = view.getLeftArrowImage() ;
        super.rightArrow = view.getRightArrowButton() ;
        super.leftArrowImage = view.getRightArrowImage() ;
    }

    public void deleteImage() {super.deleteImageOnScreen();}
    public void uploadImage() {super.onUploadBtnClick();}

    public void putBookOnSale() throws BookException, ISBNException {
        BookBean bean = new BookBean() ;
        bean.setBookTitle(this.bookName) ;
        bean.setPrice(this.bookPrice) ;
        bean.setIsbn(this.bookIsbn) ;
        bean.setIsNegotiable(this.negotiability);
        bean.setImage(super.files) ;

        new SellBooksUseCaseController().putOnSale(bean) ;
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
