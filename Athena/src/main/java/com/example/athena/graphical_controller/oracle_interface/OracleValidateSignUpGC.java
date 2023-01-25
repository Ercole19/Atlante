package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.RegistrationBean;
import com.example.athena.exceptions.UserRegistrationException;
import com.example.athena.use_case_controllers.SignUpUCC;
import com.example.athena.view.oracle_view.LabelView;

public class OracleValidateSignUpGC {

    public void createParent(String email, String code) {
        SignUpUCC controller = new SignUpUCC();
        LabelView view = new LabelView();
        RegistrationBean bean = new RegistrationBean();
        bean.setEmail(email);
        bean.setCode(code);
        try {
            controller.register(bean);
            ParentSubject.getInstance().setCurrentParent( view.prepareParent("Registration successful"));
        } catch (UserRegistrationException e) {
            ParentSubject.getInstance().setCurrentParent( view.prepareParent("Code inserted not valid"));
        }
    }
}
