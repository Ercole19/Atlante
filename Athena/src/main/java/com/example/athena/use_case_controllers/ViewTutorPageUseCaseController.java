package com.example.athena.use_case_controllers;

import com.example.athena.beans.UserBean;
import com.example.athena.dao.UserDao;
import com.example.athena.entities.TutorPersonalPageSubject;
import com.example.athena.exceptions.NoCvException;
import com.example.athena.exceptions.UserInfoException;

public class ViewTutorPageUseCaseController {

    public void exitPage() {
        TutorPersonalPageSubject.getInstance().resetEntity() ;
    }

    public void getCV() throws UserInfoException, NoCvException {
        TutorPersonalPageSubject.getInstance().getCV() ;
    }
}
