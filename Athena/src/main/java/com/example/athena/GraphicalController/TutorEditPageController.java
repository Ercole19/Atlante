package com.example.athena.GraphicalController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.example.athena.View.userdao ;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.example.athena.View.coursedao;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class TutorEditPageController  implements Initializable {

    @FXML
    private TextArea aboutme;
    @FXML
    private TextArea coursesArea ;
    @FXML
    private TextArea sessioninfos ;
    @FXML
    private TextArea contactnumbers ;
    private userdao user ;
    private coursedao corso ;


    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "LoginPage.fxml");
    }

    public void onConfirmButtonClick(ActionEvent event) throws IOException {
        String[] infos = user.filltutorinfos();
        user = new userdao();
        if (infos.equals(null)) {

            user.settutorinfos(aboutme.getText(),  sessioninfos.getText(), contactnumbers.getText());
        }
        else {
            user.updatetutorinfos(aboutme.getText(),  sessioninfos.getText(), contactnumbers.getText());

        }

        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "MainPageTutor.fxml");
    }

    public void onCVButtonClick(ActionEvent event) throws IOException
    {
        AnchorPane root = new AnchorPane() ;
        root.setPrefSize(600, 800) ;
        Label CV = new Label("Lorem Ipsum Dolor Sit Amet") ;
        root.getChildren().add(CV) ;
        Scene CVScene = new Scene(root) ;
        Stage tempStage = new Stage() ;
        tempStage.setScene(CVScene) ;
        tempStage.initModality(Modality.APPLICATION_MODAL) ;
        tempStage.setResizable(false) ;
        tempStage.setTitle("CV") ;
        tempStage.showAndWait() ;
    }

    public void clickOnBackButton(ActionEvent event) throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "MainPageTutor.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = new  userdao() ;
        corso = new coursedao() ;


        String[] infos = user.filltutorinfos();
        List<String> courses = corso.fillCourses() ;
        if (infos.equals(null)) {
            aboutme.setText("");
            sessioninfos.setText("");
            contactnumbers.setText("");
        }
        else {
            aboutme.setText(infos[0]);
            sessioninfos.setText(infos[1]);
            contactnumbers.setText(infos[2]);
        }

        for (int i = 0 ; i< courses.size() ; i++) {
            coursesArea.appendText(courses.get(i) + "\n" );
        }


    }
    public void onaddcoursebuttoclick() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        SceneSwitcher switcher = new SceneSwitcher() ;
        loader.setLocation(switcher.generateUrl("addcourse.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        Scene scene = new Scene(loader.load());
        stage.setTitle("Add course");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
