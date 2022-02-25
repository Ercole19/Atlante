package com.example.athena.use_case_controllers;

import java.io.File;

public class ExitSystem {

    public void exitFromApplication() {
        String pathnameBook = System.getProperty("user.dir") + "/src/main/resources/book_images/" ;
        String pathnameCV = System.getProperty("user.dir") + "/src/main/resources/tutor_cv/" ;
        File book = new File(pathnameBook) ;
        File cv = new File(pathnameCV) ;
        String[] pathnamesBook = book.list() ;
        String[] pathnamesCV = cv.list() ;
        for (String bookName : pathnamesBook) {
            File bookToDelete = new File(pathnameBook + bookName);
            bookToDelete.delete();
        }
        for (String cvName : pathnamesCV) {
            File cvToDelete = new File(pathnameCV + cvName) ;
            cvToDelete.delete() ;
        }
        System.exit(0);
    }

}
