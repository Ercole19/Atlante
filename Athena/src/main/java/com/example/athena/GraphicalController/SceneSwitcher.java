package com.example.athena.GraphicalController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static javafx.fxml.FXMLLoader.NULL_KEYWORD;
import static javafx.fxml.FXMLLoader.load;

public class SceneSwitcher {

    public void switcher(ActionEvent event, String fxml) throws IOException {
        Parent root = load(generateUrl(fxml)) ;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void switcher(ActionEvent event, String fxml, ArrayList<Object> params) throws IOException {
        Parent root = preload(fxml, params) ;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene) ;
    }

    public Parent preload(String fxml, ArrayList<Object> params) throws IOException{
        FXMLLoader loader = new FXMLLoader(generateUrl(fxml)) ;
        Parent root = loader.load() ;
        PostInitialize post = loader.getController() ;
        post.postInitialize(params) ;
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
}
