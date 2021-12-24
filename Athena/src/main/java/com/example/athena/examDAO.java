package com.example.athena;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.sql.*;

public class examDAO {
    private String USER = "root";
    private String PASS = "Salamandra230!";
    private String DB_URL = "jdbc:mysql://localhost:3306/atena";
    private String getQuery = "SELECT * FROM esami";
    private String deleteQuery = "DELETE FROM esami WHERE Nome = ?";
    private String addQuery = " INSERT INTO esami  VALUES (?,?,?,?); ";
    private String updateQuery = "UPDATE `esami` SET `Nome` = ? , `Voto` = ? , `CFU` = ? , `Data` = ?  WHERE (`Nome` = ? )  ";
    private String sortedExams = "SELECT  Voto , Data  from  esami order by Data ASC";
    private ObservableList<examEntityBean> examlist;
    private examEntityBean exam;




    public ObservableList<examEntityBean> getExamlist() {
        examlist = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stm = connection.createStatement();
            ResultSet set = stm.executeQuery(getQuery);

            while (set.next()) {
                exam = new examEntityBean();
                exam.setExamName(set.getString("Nome"));
                exam.setVotoEsame(set.getString("Voto"));
                exam.setCfuEsame(set.getString("CFU"));
                exam.setDate(set.getString("Data"));
                examlist.add(exam);

            }


        } catch (SQLException | NullPointerException exc) {
            exc.getMessage();
        }
        return examlist;

    }

    public void addExam(examEntityBean beanExam) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stm = connection.prepareStatement(addQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            stm.setString(1, beanExam.getExamName());
            stm.setString(2, String.valueOf(beanExam.getVotoEsame()));
            stm.setString(3, String.valueOf(beanExam.getCfuEsame()));
            stm.setString(4, beanExam.getDate());
            stm.executeUpdate();


        } catch (Exception exc) {
            exc.getCause();
        }

    }

    public void deleteExam(String nome) throws SQLException {

        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stm = connection.prepareStatement(deleteQuery);
        stm.setString(1, nome);
        stm.execute();
        System.out.println("Eliminato");


    }

    public void updateExam(examEntityBean beanExam, String oldName) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stm = connection.prepareStatement(updateQuery);
            stm.setString(1, beanExam.getExamName());
            stm.setString(2, String.valueOf(beanExam.getVotoEsame()));
            stm.setString(3, String.valueOf(beanExam.getCfuEsame()));
            stm.setString(4, beanExam.getDate());
            stm.setString(5, oldName);
            stm.executeUpdate();


        } catch (SQLException exc) {
            exc.getErrorCode();
        }
    }

    public ObservableList<XYChart.Data<String, Number>> getSortedExams() {
        Integer count = 1;
        double average = 0.0;
        double counterVoti = 0.0;

        ObservableList<XYChart.Data<String, Number>> list = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(sortedExams);

            while (set.next()) {

                String Data = set.getString("Data");
                int voto = set.getInt("Voto");
                counterVoti = counterVoti + voto;

                average = (counterVoti) / count;
                list.add(new XYChart.Data<>(Data, average));
                count++;


            }
        } catch (SQLException ex) {
            ex.getErrorCode();
        }
        return list;

    }


    public Number  getAverage () {


        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stm = connection.createStatement();
            ResultSet set = stm.executeQuery("SELECT AVG(VOTO) as media  FROM esami");
            while (set.next()) {
                return set.getDouble("media");

            }
        } catch (SQLException exc) {
            exc.getErrorCode();
        }

        return null ;
    }

    public ObservableList<XYChart.Data<String, Number>> getSortedExamsWeighted() {
        Integer cfus = 0 ;
        double average = 0.0;
        double counterVoti = 0.0;

        ObservableList<XYChart.Data<String, Number>> list = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT  Voto , Data , CFU  from  esami order by Data ASC");

            while (set.next()) {

                String Data = set.getString("Data");
                int voto = set.getInt("Voto");
                int cfu = set.getInt("CFU") ;
                cfus = cfus + cfu ;
                counterVoti = counterVoti + (voto * cfu );

                average = (counterVoti) / cfus;
                list.add(new XYChart.Data<>(Data, average));



            }
        } catch (SQLException ex) {
            ex.getErrorCode();
        }
        return list;

    }

    public Number  getAverageWeighted  () {
        double voti = 0 ;
        double  cfus = 0 ;
        double average = 0;


        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stm = connection.createStatement();
            ResultSet set = stm.executeQuery("SELECT (Voto) , (CFU)  FROM esami ORDER BY Data ASC");
            while (set.next()) {
                double voto = set.getDouble("Voto") ;
                double  cfu = set.getDouble("CFU") ;
                cfus = cfus + cfu ;
                voti = voti + (voto *cfu) ;
                average = voti / cfus ;
                System.out.println(average);



            }

        } catch (SQLException exc) {
            exc.getErrorCode();
        }

        return average ;
    }



     public ObservableList < PieChart.Data > loadData() {
        String query = "select P, C From PIE "; //ORDER BY P asc
        ObservableList < PieChart.Data > piechartdata;
        piechartdata = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(DB_URL , USER ,PASS ) ;
            ResultSet set = connection.createStatement().executeQuery("SELECT COUNT(Nome) as esamiDati from esami");
            while (set.next()) {
                piechartdata.add(new PieChart.Data("Esami dati" , set.getInt("esamiDati"))) ;

            }
        } catch (Exception e) {
            e.getCause() ;

        }
        return piechartdata ;
    }


    public Number getTotalExams () {
        int count = 0 ;
        try {
            Connection connection = DriverManager.getConnection(DB_URL , USER , PASS ) ;
            ResultSet set = connection.createStatement().executeQuery("SELECT (Nome)  from esami") ;
            while  (set.next()) {
                count++ ;
            }
        }catch (SQLException exc) {
            exc.getErrorCode() ;

        }return count ;
    }


    public ObservableList < PieChart.Data > loadData2() {
        ObservableList < PieChart.Data > piechartdata;
        piechartdata = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection(DB_URL , USER ,PASS ) ;
            ResultSet set = connection.createStatement().executeQuery("SELECT SUM(CFU) as cfus from esami");
            while (set.next()) {
                piechartdata.add(new PieChart.Data("CFU possseduti " , set.getInt("cfus"))) ;

            }
        } catch (Exception e) {
            e.getCause() ;

        }
        return piechartdata ;
    }



    public Number getTotalCfus () {
        try {
            Connection connection = DriverManager.getConnection(DB_URL , USER , PASS ) ;
            ResultSet set = connection.createStatement().executeQuery("SELECT SUM(CFU) as cfus  from esami") ;
            while  (set.next()) {
                return set.getInt("cfus") ;
            }
        }catch (SQLException exc) {
            exc.getErrorCode() ;

        }return null ;
    }







}










