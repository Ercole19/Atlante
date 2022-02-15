package com.example.athena.use_case_controllers;

import com.example.athena.boundaries.SendRegistrationBean;
import com.example.athena.boundaries.SendRegistrationCodeBoundary;
import com.example.athena.entities.TutorReviewCodesGenerator;
import com.example.athena.entities.UserDao;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.graphical_controller.UserBean;

import java.security.NoSuchAlgorithmException;

public class SignUpUCC {

    public boolean register(UserBean bean) {

        try {
            SendRegistrationBean params = new SendRegistrationBean(bean.getEmail(), TutorReviewCodesGenerator.generateReviewCode(5));
            SendRegistrationCodeBoundary.sendCode(params) ;
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (SendEmailException e) //Separated because they will have different implementations
        {
            e.printStackTrace();
        }

        UserDao dao = new UserDao();
        if ( dao.registerUser(bean.getEmail(), bean.getPassword(), bean.getRole(), bean.getName(), bean.getSurname()) ) {
            return true ;
        }
        return false ;


    }

}
