package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.BookBean;
import com.example.athena.entities.BooksSubject;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.use_case_controllers.SellBooksUseCaseController;
import com.example.athena.view.oracle_view.DeleteWhichBookView;
import com.example.athena.view.oracle_view.LabelView;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;

public class OracleDeleteBookGC {
    private final LabelView labelView = new LabelView();
    private final List<BookBean> list = new ArrayList<>();

    private int countBeans(String isbn) throws BookException, UserInfoException {
        ObservableList<BookBean> beanList = BooksSubject.getInstance().getBooksBeansList();
        for (BookBean book : beanList) {
            if (book.getIsbn().equals(isbn)) {
                this.list.add(book);
            }
        }
        return list.size() ;
    }

    public void deleteBook(String isbn) {
        try {
            int count = countBeans(isbn);
            if (count == 0) {
                ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("You are not selling a book with this ISBN"));
                return;
            }
            if (count > 1) ParentSubject.getInstance().setCurrentParent(new DeleteWhichBookView(this).getRoot());
            else {
                deleteBookUseCase(this.list.get(0));
            }
        }
        catch (BookException | UserInfoException e) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Error in delete book call, details follow: " + e.getMessage()));
        }
    }

    public void deleteBookUseCase(BookBean bean) {
        try {
            new SellBooksUseCaseController().deleteProduct(bean);
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Book deleted successfully"));
        }catch (BookException e) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Error in deleting book, details follow: " + e.getMessage()));
        }
    }


    public List<BookBean> getList(){return this.list;}
}
