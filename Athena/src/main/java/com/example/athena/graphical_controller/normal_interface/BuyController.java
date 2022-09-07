package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.view.FindBooksView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BuyController implements Initializable, PostInitialize {


    @FXML
    private TextField searchInput ;

    @FXML
    private SubScene resultPanel ;
    private final SceneSwitcher switcher = SceneSwitcher.getInstance();
    private FindBooksView findBooksView ;


    @FXML
    protected void onHomeButtonClick()  {
        switcher.switcher("MainPageStudents.fxml");
    }

    @FXML
    protected void onSearchButtonClick(){
        String query = searchInput.getText() ;
        this.resultPanel.setRoot(findBooksView.getRoot(query));
    }

    @FXML
    protected void onBackButtonClick(){
        switcher.switcher("bookshop-choose-view.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.findBooksView = new FindBooksView(resultPanel.getWidth(), resultPanel.getHeight()) ;
    }

    @Override
    public void postInitialize(ArrayList<Object> params) {
        String nameOrIsbn = (String) params.get(0);
        searchInput.setText(nameOrIsbn);
        onSearchButtonClick() ;
    }
}
