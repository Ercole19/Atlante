package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.normal.BookBean;
import com.example.athena.entities.BooksSubject;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.graphical_controller.oracle_interface.update_book_states.OnModifyParametersState;
import com.example.athena.graphical_controller.oracle_interface.update_book_states.OnSelectWhichBookState;
import com.example.athena.graphical_controller.oracle_interface.update_book_states.UpdateBookAbstractState;
import javafx.collections.ObservableList;

public class OracleUpdateBookGC {

    private UpdateBookAbstractState state ;
    private String isbn;

    public void updateBook(String isbn) {
        try {
            this.isbn = isbn;
            int count = countBeans(isbn);
            if (count > 1) this.state = new OnSelectWhichBookState(this);
            else this.state = new OnModifyParametersState(this);
        }
        catch (BookException | UserInfoException e) {

        }
    }

    public void update() {

    }

    private int countBeans(String isbn) throws BookException, UserInfoException {
        ObservableList<BookBean> list = BooksSubject.getInstance().getBooksBeansList();
        int count = 0;
        for (BookBean book : list) {
            if (book.getIsbn().equals(isbn)) count++;
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
