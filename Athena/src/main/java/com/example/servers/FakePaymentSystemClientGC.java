package com.example.servers;

import com.example.athena.graphical_controller.normal_interface.PostInitialize;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FakePaymentSystemClientGC implements PostInitialize {

    @FXML
    private TextField owner ;

    @FXML
    private TextField number ;

    @FXML
    private TextField cvv ;

    private Response response ;

    public void execPayment(ActionEvent event) {
        try {
            checkParams();
        }catch (FormCompilingException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE) ;
            alert.showAndWait() ;
            return ;
        }
        response.setResponse(new FakePaymentSystemClientController().pay().getServerResponse());
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow() ;
        stage.close();
    }

    private void checkParams() throws FormCompilingException{
        if(owner.getText().equals("")) throw new FormCompilingException("Insert the owner's name!");
        if(!(number.getText().matches("\\d{16}"))) throw new FormCompilingException("Wrong credit card number format") ;
        if(!(cvv.getText().matches("\\d{3,4}"))) throw new FormCompilingException("Wrong cvv format!") ;
    }

    private static class FormCompilingException extends Exception {
        public FormCompilingException(String message) {
            super(message) ;
        }

    }

    @Override
    public void postInitialize(ArrayList<Object> params) {
        response = (Response) params.get(0) ;
    }
}
