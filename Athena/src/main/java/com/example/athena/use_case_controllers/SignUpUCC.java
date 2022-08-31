package com.example.athena.use_case_controllers;

import com.example.athena.boundaries.SendCodeMailBean;
import com.example.athena.boundaries.SendRegistrationCodeBoundary;
import com.example.athena.entities.TutorReviewCodesGenerator;
import com.example.athena.entities.UserDao;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.UserRegistrationException;
import com.example.athena.graphical_controller.RegistrationBean;
import com.example.athena.graphical_controller.UserBean;

import java.security.NoSuchAlgorithmException;

public class SignUpUCC {

    private final UserDao dao = new UserDao();

    public void preRegister(UserBean bean) throws UserRegistrationException {
        String code = null;
        try {
            code = TutorReviewCodesGenerator.generateReviewCode(5) ;
            SendCodeMailBean params = new SendCodeMailBean(bean.getEmail(), code);
            SendRegistrationCodeBoundary.getInstance().sendCode(params) ;
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (SendEmailException e) //Separated because they will have different implementations
        {
            e.printStackTrace();
        }

        dao.preRegistration(bean.getEmail(), bean.getPassword(), bean.getRole(), bean.getName(), bean.getSurname(), code);
    }

    public boolean register(RegistrationBean bean) throws UserRegistrationException {

        return dao.registerUser(bean.getEmail(), bean.getCode());
    }
}
