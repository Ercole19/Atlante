package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.UserBean;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.UserRegistrationException;
import com.example.athena.use_case_controllers.SignUpUCC;
import com.example.athena.view.oracle_view.LabelView;
import com.example.athena.view.oracle_view.SignUpView;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.security.NoSuchAlgorithmException;

public class OracleSignUpGC {

    private final PasswordField passwordField ;
    private final PasswordField confirmTextField ;
    private final String name;
    private final String surname;
    private final String email;

    private final String type ;
    private Parent parent;

    public OracleSignUpGC(String name, String surname, String email, String type) {
        SignUpView signUpView = new SignUpView();
        this.confirmTextField = signUpView.getConfirmPassword() ;
        this.passwordField = signUpView.getPassword() ;

        Button confirmButton = signUpView.getConfirm() ;
        this.name = name ;
        this.email = email ;
        this.surname = surname;
        this.type = type ;
        if (!type.toUpperCase().matches("STUDENT|TUTOR")) {
            ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("User type must be \"STUDENT\" or \"TUTOR\""));
            return ;
        }
        this.parent = signUpView.getParent();
        confirmButton.setOnAction(event -> onConfirmButtonClick());
        ParentSubject.getInstance().setCurrentParent(this.parent) ;
    }

    public void onConfirmButtonClick() {
        UserBean bean = new UserBean();
        LabelView view = new LabelView();


        if (this.confirmTextField.getText().equals(this.passwordField.getText())) {
            bean.setPassword(passwordField.getText());
            bean.setEmail(email);
            bean.setName(name);
            bean.setSurname(surname);
            bean.setRole(type) ;
            SignUpUCC signUpUCC = new SignUpUCC();
            try {
                signUpUCC.preRegister(bean);
                this.parent = view.prepareParent("Pre Registration successful, now validate code");
            } catch (UserRegistrationException | SendEmailException | NoSuchAlgorithmException e) {
                this.parent = view.prepareParent("Error during registration, details follow: " + e.getMessage());
            }
        } else {
             this.parent = view.prepareParent("Passwords dont match");
        }
        ParentSubject.getInstance().setCurrentParent(this.parent);
    }
}
