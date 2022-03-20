package com.example.athena.graphical_controller;

import com.example.athena.entities.AbstractDAO;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.exceptions.UserNotFoundException;
import com.example.athena.use_case_controllers.LoginUseCaseController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




public class LoginPageController{

    private int totalAttempts = 0 ;
    private int waitTimeMultiplier = 1;

    private final SceneSwitcher switcher = new SceneSwitcher();

    private Stage stage;

    @FXML
    private Button loginButton;
    @FXML
    private TextField emailField ;
    @FXML
    private TextField passField;

    public void switchToMainPage(ActionEvent event){
        String email = emailField.getText() ;
        String password = passField.getText() ;

        UserBean bean = new UserBean();
        bean.setEmail(email);
        bean.setPassword(password);

        LoginUseCaseController controller = new LoginUseCaseController() ;


        try{
                bean = controller.findUser(bean);

                if (bean.getRole().equals("STUDENT")) {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Access granted !", ButtonType.CLOSE);
                    alert.showAndWait();
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
                    switcher.switcher(stage, "MainPageStudents.fxml");

                } else {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Access granted !", ButtonType.CLOSE);
                    alert.showAndWait();
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
                    switcher.switcher(stage, "MainPageTutor.fxml");
                }

        }
        catch (LoggedUserException exc)
        {
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, exc.getMessage());
                alert.showAndWait();
        }
        catch (UserNotFoundException exe)
        {
            if(totalAttempts < 5)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, exe.getMessage() , ButtonType.CLOSE);
                alert.showAndWait();
                totalAttempts++;
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING,  "Too many failed attempts, wait " + 10*waitTimeMultiplier + " seconds!", ButtonType.CLOSE);
                alert.showAndWait();
                new Thread(() -> {
                    Platform.runLater(() -> loginButton.setDisable(true));
                    try {
                        Thread.sleep(10000L * waitTimeMultiplier);
                    }
                    catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    Platform.runLater(() -> loginButton.setDisable(false));
                }).start();
                waitTimeMultiplier++;
            }
        }
    }


    public void switchToSignUpPage(ActionEvent event ) {

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "signUpView.fxml");

    }


    public void fillFast() {
        emailField.setText("alba@student.it");
        passField.setText("tramonto");
    }
}
