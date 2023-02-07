package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.BookBean;
import com.example.athena.entities.PersonalBookShelf;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.graphical_controller.normal_interface.UpdatedShiftImageController;
import com.example.athena.graphical_controller.oracle_interface.update_book_states.OnModifyParametersState;
import com.example.athena.graphical_controller.oracle_interface.update_book_states.OnSelectWhichBookState;
import com.example.athena.graphical_controller.oracle_interface.update_book_states.UpdateBookAbstractState;
import com.example.athena.use_case_controllers.ManageYourSellingBooksUCC;
import com.example.athena.view.oracle_view.LabelView;
import com.example.athena.view.oracle_view.ModifyParametersView;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OracleUpdateBookGC extends UpdatedShiftImageController {

    private UpdateBookAbstractState state ;
    private final LabelView labelView = new LabelView();
    private final List<BookBean> list = new ArrayList<>();
    private BookBean selectedBean ;
    private BookBean updatedBean ;
    private String isbn;

    public void updateBook(String isbn) {
        try {
            this.isbn = isbn;
            int count = countBeans(isbn);
            if (count > 1) this.state = new OnSelectWhichBookState(this);
            else {
                setSuperParam(list.get(0));
                this.selectedBean = list.get(0);
                this.state = new OnModifyParametersState(this);
            }
        }
        catch (BookException | UserInfoException e) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Error in update book call, details follow: " + e.getMessage()));
        }
    }

    public void executeBookUpdate() throws ISBNException, BookException {
        new ManageYourSellingBooksUCC().updateProduct(selectedBean, updatedBean) ;
    }

    private int countBeans(String isbn) throws BookException, UserInfoException {
        ObservableList<BookBean> beanList = PersonalBookShelf.getInstance().getBooksBeansList();
        for (BookBean book : beanList) {
            if (book.getIsbn().equals(isbn)) {
                this.list.add(book);
            }
        }
        return list.size() ;
    }

    public void setState(UpdateBookAbstractState state) {
        this.state = state ;
    }

    public void getSelectedBook(BookBean bean) {
        this.selectedBean = bean ;
        setSuperParam(this.selectedBean);
        setState(new OnModifyParametersState(this));
    }

   

    private void setSuperParam(BookBean bean) {
        super.images = bean.getImageList();
        super.files = bean.getImage();
    }

    public void goNext() {
        this.state.goNext(this) ;
    }

    public List<BookBean> getList(){
        return this.list;
    }

    public void getUploadControls(ModifyParametersView view) {
        super.bookImage = view.getBookImageView();
        super.rightArrowImage = view.getRightArrowImage();
        super.leftArrowImage = view.getLeftArrowImage();

        if (this.selectedBean.getImageList().isEmpty()){
            super.bookImage.setImage(new Image(new File("src/main/resources/assets/upload2.jpg").toURI().toString()));
            shiftIndex(-1);
        }
        else {
            super.bookImage.setImage(this.selectedBean.getImageList().get(0));
            shiftIndex(0);
        }
    }

    public void getUpdatedInformation(ModifyParametersView view) {
        this.updatedBean = new BookBean();
        try {
            this.updatedBean.setIsbn(this.isbn);
            this.updatedBean.setPrice(view.getPrice());
        } catch (BookException e) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Error in updating book, details follow: " + e.getMessage()));
        }
        this.updatedBean.setBookTitle(view.getBookTitle());
        this.updatedBean.setIsNegotiable(view.getNegotiability());
        this.updatedBean.setImage(super.files);
        this.updatedBean.setTimeStamp(selectedBean.getTimeStamp());
        this.updatedBean.setOwner(LoggedStudent.getInstance().getEmail().getMail());

        this.goNext();
    }


    public BookBean getSelectedBean() {return this.selectedBean;}

    @Override
    public void rightArrowClick() {
        super.onRightArrowClick();
    }

    @Override
    public void leftArrowClick() {
        super.onLeftArrowClick();
    }
}
