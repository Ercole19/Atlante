package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.normal.UserBean;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.UserRegistrationException;
import com.example.athena.use_case_controllers.SignUpUCC;
import com.example.athena.view.oracle_view.LabelView;
import com.example.athena.view.oracle_view.SignUpView;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.security.NoSuchAlgorithmException;

public class OracleSignUpGC {

    private final TextField confirmTextField ;
    private final String name;
    private final String surname;
    private final String email;
    private final String pass;
    private Parent parent;

    public OracleSignUpGC(String name, String surname, String email, String password) {
        SignUpView signUpView = new SignUpView();
        this.confirmTextField = (TextField) signUpView.lookup("#confirmTextfield") ;
        Button confirmButton = (Button) signUpView.lookup("#confirmButton");
        this.name = name ;
        this.pass = password ;
        this.email = email;
        this.surname = surname;
        this.parent = signUpView.getParent();
        confirmButton.setOnAction(event -> onConfirmButtonClick());
        ParentSubject.getInstance().setCurrentParent(this.parent) ;
    }

    public void onConfirmButtonClick() {
        UserBean bean = new UserBean();
        LabelView view = new LabelView();


        if (this.confirmTextField.getText().equals(this.pass)) {
            bean.setPassword(pass);
            bean.setEmail(email);
            bean.setName(name);
            bean.setSurname(surname);
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
