package com.example.athena.view.oracle_view;

import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SignUpView {
    private Parent confirmParent ;

    private Button confirm ;

    private PasswordField password ;

    private PasswordField confirmPassword ;

    public SignUpView() {
        this.confirmParent = prepareConfirmPArent() ;
    }

    private Parent prepareConfirmPArent() {

        VBox mainBox = new VBox() ;

        HBox passwordBox = new HBox() ;
        Label insert = LabelBuilder.buildLabel("Insert Password") ;
        password = new PasswordField();

        passwordBox.getChildren().addAll(insert, password) ;

        HBox confirmBox = new HBox();
        Label label = LabelBuilder.buildLabel("Confirm password");
        confirmPassword = new PasswordField() ;

        confirmBox.getChildren().addAll(label, confirmPassword) ;

        confirm = new Button("Confirm");

        mainBox.getChildren().addAll(passwordBox, confirmBox, confirm);

        return mainBox;
    }

    public Parent getParent() {
        return this.confirmParent ;
    }

    public Button getConfirm() {
        return confirm;
    }

    public PasswordField getPassword() {
        return password;
    }

    public PasswordField getConfirmPassword() {
        return confirmPassword;
    }
}
