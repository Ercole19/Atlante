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
    private String getQuery = "SELECT * FROM esami";
    private String deleteQuery = "DELETE FROM esami WHERE Nome = ?" ;
    private String addQuery =" INSERT INTO esami  VALUES (?,?,?,?); " ;
    private String updateQuery ="UPDATE `esami` SET `Nome` = ? , `Voto` = ? , `CFU` = ? , `Data` = ?  WHERE (`Nome` = ? )  " ;
    private ObservableList<examEntityBean> examlist ;
    private examEntityBean exam ;


    public ObservableList<examEntityBean> getExamlist () {
        examlist = FXCollections.observableArrayList() ;
        try {
            Connection connection = DriverManager.getConnection(DB_URL , USER , PASS) ;
            Statement stm = connection.createStatement() ;
            ResultSet set = stm.executeQuery(getQuery) ;

            while (set.next() ) {
                exam =  new examEntityBean() ;
                exam.setExamName(set.getString("Nome"));
                exam.setVotoEsame(set.getString("Voto"));
                exam.setCfuEsame(set.getString("CFU"));
                exam.setDate(set.getString("Data")) ;
                examlist.add(exam) ;

            }


        }
        catch (SQLException | NullPointerException exc ){
            exc.getMessage() ;
        }
        return examlist ;

    }

    public void addExam (examEntityBean beanExam) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stm = connection.prepareStatement(addQuery , ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            stm.setString(1, beanExam.getExamName());
            stm.setString(2, String.valueOf(beanExam.getVotoEsame()));
            stm.setString(3, String.valueOf(beanExam.getCfuEsame()));
            stm.setString(4, beanExam.getDate());
            stm.executeUpdate() ;


        } catch (Exception  exc) {
            exc.getCause();
        }

    }

    public void deleteExam (String nome ) throws SQLException {

        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stm = connection.prepareStatement(deleteQuery);
        stm.setString(1 , nome);
        stm.execute() ;
        System.out.println("Eliminato");












    }

    public void updateExam (examEntityBean beanExam ) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stm = connection.prepareStatement(updateQuery) ;
            stm.setString(1, beanExam.getExamName());
            stm.setString(2, String.valueOf(beanExam.getVotoEsame()));
            stm.setString(3, String.valueOf(beanExam.getCfuEsame()));
            stm.setString(4, beanExam.getDate());
            stm.setString(5 , beanExam.getExamName());
            stm.executeUpdate() ;


        }catch (SQLException exc) {
            exc.getErrorCode();
        }
    }






}
