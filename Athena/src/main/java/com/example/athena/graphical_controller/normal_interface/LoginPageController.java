package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.UserBean;
import com.example.athena.exceptions.*;
import com.example.athena.use_case_controllers.LoginUseCaseController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




public class LoginPageController{


    private final SceneSwitcher switcher = SceneSwitcher.getInstance() ;

    protected Stage stage;

    @FXML
    protected Button loginButton;
    @FXML
    protected TextField emailField ;
    @FXML
    protected TextField passField;
    private int totalAttempts = 0;
    private int waitTimeMultiplier = 1;

    public void switchToMainPage(){

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
                    switcher.switcher("MainPageStudents.fxml");

                } else {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Access granted !", ButtonType.CLOSE);
                    alert.showAndWait();
                    switcher.switcher("MainPageTutor.fxml");
                }

        }
        catch (LoggedUserException | UserInfoException | FindException | StudentInfoException exc)
        {
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, exc.getMessage());
                alert.showAndWait();
        }
        catch (UserNotFoundException exe)
        {
            if( totalAttempts < 5)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, exe.getMessage() , ButtonType.CLOSE);
                alert.showAndWait();
                incrementAttempts();
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
                incrementMultiplier();
                this.totalAttempts = 0;
            }
        }
    }

    private void incrementAttempts(){
        this.totalAttempts++;
    }

    private void incrementMultiplier() {
        this.waitTimeMultiplier++ ;
    }


    public void switchToSignUpPage() {
        switcher.switcher("signUpView.fxml");
    }
}
