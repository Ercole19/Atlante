package com.example.athena.use_case_controllers;

import com.example.athena.beans.BookBean;
import com.example.athena.beans.FindBookQueryBean;
import com.example.athena.entities.BookEntity;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.FindBookException;
import com.example.athena.exceptions.FindException;

import java.util.ArrayList;
import java.util.List;

public class FindBooksUCC {
    public List<BookBean> formatSearchResults(FindBookQueryBean bean) throws FindBookException, BookException {

        ArrayList<BookBean> result = new ArrayList<>();
        try {
            List<BookEntity> bookInfos = new BookEntity().getBooksFromQuery(bean.getQuery());
            for (BookEntity entity : bookInfos)
            {
                BookBean book = new BookBean();
                book.setBookTitle(entity.getTitle());
                book.setIsbn(entity.getIsbn());
                book.setPrice(String.valueOf(entity.getPrice()));
                book.setIsNegotiable(entity.getNegotiable());
                book.setOwner(entity.getOwner());
                book.setImage(entity.getImage());
                book.setTimeStamp(entity.getSaleTimestamp());
                result.add(book);
            }
        }
        catch (FindException e) {
            throw new FindBookException(e.getMessage());
        }
        return result;
    }
}
