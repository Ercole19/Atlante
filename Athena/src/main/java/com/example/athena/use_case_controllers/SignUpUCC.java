package com.example.athena.use_case_controllers;

import com.example.athena.boundaries.SendCodeMailBean;
import com.example.athena.boundaries.SendRegistrationCodeBoundary;
import com.example.athena.entities.TutorReviewCodesGenerator;
import com.example.athena.entities.UserDao;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.UserRegistrationException;
import com.example.athena.beans.normal.RegistrationBean;
import com.example.athena.beans.normal.UserBean;

import java.security.NoSuchAlgorithmException;

public class SignUpUCC {

    private final UserDao dao = new UserDao();

    public void preRegister(UserBean bean) throws UserRegistrationException , SendEmailException, NoSuchAlgorithmException{
        String code;
        code = TutorReviewCodesGenerator.generateReviewCode(5);
        SendCodeMailBean params = new SendCodeMailBean(bean.getEmail(), code);
        SendRegistrationCodeBoundary.getInstance().sendCode(params);

        dao.preRegistration(bean.getEmail(), bean.getPassword(), bean.getRole(), bean.getName(), bean.getSurname(), code);
    }

    public boolean register(RegistrationBean bean) throws UserRegistrationException {

        return dao.registerUser(bean.getEmail(), bean.getCode());
    }
}
