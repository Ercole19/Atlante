package com.example.athena.graphical_controller;

import com.example.athena.exceptions.SizedAlert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static javafx.fxml.FXMLLoader.load;

public class SceneSwitcher {

    private static final String FATAL_ERROR = "FATAL ERROR: The application is unable to change pages. If the problem persists after restarting, try reinstalling the application.";

    public void switcher(Stage stage, String fxml) {
        try
        {
            Parent root = load(generateUrl(fxml)) ;
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException e)
        {
            this.handleSwitcherException() ;
        }

    }

    public void switcher(Stage stage, String fxml, List<Object> params)
    {
        Parent root = preload(fxml, params);
        Scene scene = new Scene(root);
        stage.setScene(scene) ;
    }


    public void popup(String fxml, String title)
    {
        try
        {
            Parent root = load(generateUrl(fxml)) ;
            preparePopup(root, title) ;
        }
        catch (IOException e)
        {
            this.handleSwitcherException();
        }

    }

    public void popup (String fxml, String title, List<Object> params)
    {
        Parent root = preload(fxml, params);
        preparePopup(root, title) ;
    }

    private void preparePopup(Parent root, String title)
    {
        Stage stage = new Stage() ;
        stage.initModality(Modality.APPLICATION_MODAL) ;
        stage.setResizable(false) ;
        Scene scene = new Scene(root) ;
        stage.setTitle(title) ;
        stage.setScene(scene) ;
        stage.showAndWait() ;
    }

    public Parent preload(String fxml, List<Object> params)  {
        Parent root = null ;
        try {
            FXMLLoader loader = new FXMLLoader(generateUrl(fxml)) ;
            root = loader.load() ;
            PostInitialize post = loader.getController() ;
            post.postInitialize((ArrayList<Object>) params) ;
        } catch (IOException e) {
            this.handleSwitcherException();
        }

        assert root != null ;
        return root ;
    }

    public Parent preload(String fxml)
    {
        Parent root = null ;
        try {
            FXMLLoader loader = new FXMLLoader(generateUrl(fxml)) ;
            root = loader.load() ;
        } catch (IOException e) {
            this.handleSwitcherException();
        }

        assert root != null ;
        return root ;
    }


    public URL generateUrl(String fxmlToLoad)throws MalformedURLException {
        URL returnUrl = null;
            if(System.getProperty("os.name").contains("Windows"))
            {
                returnUrl = new URL("file:/" + System.getProperty("user.dir") + "/src/main/resources/com/example/athena/fxml/" + fxmlToLoad) ;
            }
            else if(System.getProperty("os.name").contains("Linux"))
            {
                returnUrl = new URL("file://" + System.getProperty("user.dir") + "/src/main/resources/com/example/athena/fxml/" + fxmlToLoad);
            }
            return returnUrl;
    }


    private void handleSwitcherException()
    {
        SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, FATAL_ERROR, 800, 600) ;
        alert.showAndWait() ;
        System.exit(1) ;
    }
}
