package com.example.athena;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;

public class examDAO {
    private  String USER = "root";
    private  String PASS = "Salamandra230!";
    private  String DB_URL = "jdbc:mysql://localhost:3306/atena";
    private String query = "SELECT * FROM esami";
    private ObservableList<examEntityBean> examlist ;
    private examEntityBean exam ;


    public ObservableList<examEntityBean> getExamlist () {
        examlist = FXCollections.observableArrayList() ;
        try {
            Connection connection = DriverManager.getConnection(DB_URL , USER , PASS) ;
            Statement stm = connection.createStatement() ;
            ResultSet set = stm.executeQuery(query) ;

            while (set.next() ) {
                exam =  new examEntityBean() ;
                exam.setExamName(set.getString("Nome"));
                exam.setVotoEsame(set.getString("Voto"));
                exam.setCfuEsame(set.getString("CFU"));
                exam.setDate(set.getString("Data")) ;
                examlist.add(exam) ;

            }


        }
        catch (SQLException exc ){
            exc.getErrorCode() ;
        }
        return examlist ;

    }






}
