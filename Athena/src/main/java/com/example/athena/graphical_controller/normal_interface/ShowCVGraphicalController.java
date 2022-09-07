package com.example.athena.graphical_controller.normal_interface;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowCVGraphicalController implements Initializable, PostInitialize
{
    @FXML
    private WebView cvWebView ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        cvWebView.getEngine().getLoadWorker().stateProperty().addListener((observableValue, state, t1) -> {
            if(t1 == Worker.State.SUCCEEDED)
            {
                Document doc = cvWebView.getEngine().getDocument() ;
                NodeList list = doc.getElementsByTagName("a") ;
               for(int i = 0; i < list.getLength(); i++)
                {
                    Node element = list.item(i) ;
                    NamedNodeMap map = element.getAttributes() ;
                    Node href = map.getNamedItem("href") ;
                    if(href != null) href.setNodeValue("") ;
                }
            }
        });
        cvWebView.setContextMenuEnabled(false) ;
    }

    @Override
    public void postInitialize(ArrayList<Object> params) {
        WebEngine engine = cvWebView.getEngine() ;
        engine.load("file://" + System.getProperty("user.dir") + "/src/main/resources/tutor_cv/" + params.get(0)) ;
    }
}
