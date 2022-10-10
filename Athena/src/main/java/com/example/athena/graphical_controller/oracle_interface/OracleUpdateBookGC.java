package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.normal.BookBean;
import com.example.athena.entities.BooksSubject;
import com.example.athena.entities.Student;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.graphical_controller.normal_interface.UpdatedShiftImageController;
import com.example.athena.graphical_controller.oracle_interface.update_book_states.OnModifyParametersState;
import com.example.athena.graphical_controller.oracle_interface.update_book_states.OnSelectWhichBookState;
import com.example.athena.graphical_controller.oracle_interface.update_book_states.UpdateBookAbstractState;
import com.example.athena.use_case_controllers.SellBooksUseCaseController;
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
                this.state = new OnModifyParametersState(this);
            }
        }
        catch (BookException | UserInfoException e) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Error in update book call, details follow: " + e.getMessage()));
        }
    }

    public void update() {

    }

    private int countBeans(String isbn) throws BookException, UserInfoException {
        ObservableList<BookBean> list = BooksSubject.getInstance().getBooksBeansList();
        int count = 0;
        for (BookBean book : list) {
            if (book.getIsbn().equals(isbn)) {
                count++;
                this.list.add(book);
            }
        }
        return count;
    }

    public void setState(UpdateBookAbstractState state) {
        this.state = state ;
    }

    public void goNext() {
        this.state.goNext(this) ;
    }
}
