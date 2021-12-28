package com.example.athena.GraphicalController;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import static javafx.fxml.FXMLLoader.load;

public class SceneSwitcher {

    public void switcher(ActionEvent event, String fxml) throws IOException {
        Parent root = load(generateUrl(fxml)) ;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public URL generateUrl(String fxmlToLoad) {
        URL returnUrl = null;
        try {
            returnUrl = new URL("file:/" + System.getProperty("user.dir") + "/src/main/resources/com/example/athena/fxml/" + fxmlToLoad) ;
        }
        catch(MalformedURLException e)
        {
            e.printStackTrace() ;
        }
        finally {
            return returnUrl ;
        }
    }
}
