package com.example.athena.entities;

import com.example.athena.graphical_controller.ExamEntityBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.sql.*;

public class ExamDao extends AbstractDAO {
    private String emailcurrent =  User.getUser().getEmail() ;
    private String getquery = "SELECT Nome , Voto , CFU , Data FROM esami WHERE email = ? " ;
    private String deleteQuery = "DELETE FROM esami WHERE Nome = ? and email = ?" ;
    private String addQuery = " INSERT INTO esami  VALUES (?,?,?,?,?); " ;
    private String updateQuery = "UPDATE `esami` SET `Nome` = ? , `Voto` = ? , `CFU` = ? , `Data` = ?  WHERE (`Nome` = ? ) and email = ? " ;
    private String sortedExams = "SELECT  Voto , Data  from  esami order by Data ASC where email = ?" ;
    private String weightedsortedExams = "SELECT  Voto , Data , CFU  from  esami WHERE email =  ? order by Data ASC " ;
    private String getaverage = "SELECT AVG(VOTO) as media  FROM esami WHERE email = ?" ;
    private String getexamsdate = "SELECT (Voto) , (CFU)  FROM esami WHERE email = ? ORDER BY Data ASC " ;
    private String countExams = "SELECT COUNT(Nome) as esamiDati from esami WHERE email = ?" ;
    private String getExams = "SELECT (Nome)  from esami WHERE email = ?" ;
    private String getcfusum = "SELECT SUM(CFU) as cfus from esami WHERE email = ?" ;
    private static String driver  = "com.mysql.jdbc.Driver" ;




    public ObservableList<ExamEntityBean> getExamlist()  {
        try {
            Class.forName(driver) ;
        } catch (ClassNotFoundException e ) {
            e.getMessage() ;
        }
        ObservableList<ExamEntityBean> examlist = FXCollections.observableArrayList();
        try ( PreparedStatement
        statement = this.getConnection().prepareStatement(getquery) ) {
            statement.setString(1 , emailcurrent);
            ResultSet set = statement.executeQuery() ;

            while (set.next()) {
                ExamEntityBean exam = new ExamEntityBean();
                exam.setExamName(set.getString("Nome"));
                exam.setVotoEsame(set.getString("Voto"));
                exam.setCfuEsame(set.getString("CFU"));
                exam.setDate(set.getString("Data"));
                examlist.add(exam);

            }



        } catch (SQLException  exc) {
            exc.getMessage();
        }

        return examlist;

    }

    public void addExam(ExamEntityBean beanExam) {
        try {
            Class.forName(driver) ;
        } catch (ClassNotFoundException e ) {
            e.getMessage() ;
        }

        try (PreparedStatement stm =this.getConnection().prepareStatement(addQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)  ){

            stm.setString(1, beanExam.getExamName());
            stm.setString(2, String.valueOf(beanExam.getVotoEsame()));
            stm.setString(3, String.valueOf(beanExam.getCfuEsame()));
            stm.setString(4, beanExam.getDate());
            stm.setString(5, emailcurrent);
            stm.executeUpdate();

        } catch (Exception exc) {
            exc.getCause();
        }


    }

    public void deleteExam(String nome)  {
        try {
            Class.forName(driver) ;
        } catch (ClassNotFoundException e ) {
            e.getMessage() ;
        }

        try ( PreparedStatement stm = this.getConnection().prepareStatement(deleteQuery)) {
            stm.setString(1, nome);
            stm.setString(2,emailcurrent);
            stm.execute();
        }catch (SQLException exc) {
            exc.getErrorCode();
        }
    }

    public void updateExam(ExamEntityBean beanExam, String oldName) {
        try {
            Class.forName(driver) ;
        } catch (ClassNotFoundException e ) {
            e.getMessage() ;
        }

        try( PreparedStatement stm = this.getConnection().prepareStatement(updateQuery)) {

            stm.setString(1, beanExam.getExamName());
            stm.setString(2, String.valueOf(beanExam.getVotoEsame()));
            stm.setString(3, String.valueOf(beanExam.getCfuEsame()));
            stm.setString(4, beanExam.getDate());
            stm.setString(5, oldName);
            stm.setString(6,emailcurrent);
            stm.executeUpdate();


        } catch (SQLException exc) {
            exc.getErrorCode();
        }
    }

    public ObservableList<XYChart.Data<String, Number>> getSortedExams() {
        try {
            Class.forName(driver) ;
        } catch (ClassNotFoundException e ) {
            e.getMessage() ;
        }

        ObservableList<XYChart.Data<String, Number>> list = FXCollections.observableArrayList();

        try (PreparedStatement statement =this.getConnection().prepareStatement(sortedExams)){
            statement.setString(1,emailcurrent);
            Integer count = 1;
            double average = 0.0;
            double counterVoti = 0.0 ;
            String data ;
            int voto ;

            ResultSet set = statement.executeQuery(sortedExams) ;

            while (set.next()) {

                data = set.getString("Data");
                voto = set.getInt("Voto");
                counterVoti = counterVoti + voto;

                average = (counterVoti) / count;
                list.add(new XYChart.Data<>(data, average));
                count++;


            }
        } catch (SQLException ex) {
            ex.getErrorCode();
        }
        return list;
    }


    public Number  getAverage () {
        try {
            Class.forName(driver) ;
        } catch (ClassNotFoundException e ) {
            e.getMessage() ;
        }

        try ( PreparedStatement stm = this.getConnection().prepareStatement(getaverage)){
            stm.setString(1,emailcurrent);
            ResultSet set = stm.executeQuery();
            while (set.next()) {
                return set.getDouble("media");

            }
        } catch (SQLException exc) {
            exc.getErrorCode();
        }

        return null ;
    }

    public ObservableList<XYChart.Data<String, Number>> getSortedExamsWeighted() {
        try {
            Class.forName(driver) ;
        } catch (ClassNotFoundException e ) {
            e.getMessage() ;
        }

        ObservableList<XYChart.Data<String, Number>> list = FXCollections.observableArrayList();
        try ( PreparedStatement statement = this.getConnection().prepareStatement(weightedsortedExams)){
            statement.setString(1,emailcurrent);
            int  cfus = 0 ;
            double average = 0.0;
            double counterVoti = 0.0;
            int voto ;
            int cfu ;

            ResultSet set = statement.executeQuery();

            while (set.next()) {

                String data = set.getString("Data");
                voto = set.getInt("Voto");
                cfu = set.getInt("CFU") ;
                cfus = cfus + cfu ;
                counterVoti = counterVoti + (voto * cfu );

                average = (counterVoti) / cfus;
                list.add(new XYChart.Data<>(data, average));



            }
        } catch (SQLException ex) {
            ex.getErrorCode();
        }

        return list;

    }

    public Number  getAverageWeighted  () {
        try {
            Class.forName(driver) ;
        } catch (ClassNotFoundException e ) {
            e.getMessage() ;
        }
        double voti = 0 ;
        double  cfus = 0 ;
        double average = 0;

        try( PreparedStatement stm = this.getConnection().prepareStatement(getexamsdate)) {
            stm.setString(1,emailcurrent);
            ResultSet set = stm.executeQuery();
            while (set.next()) {
                double voto = set.getDouble("Voto") ;
                double  cfu = set.getDouble("CFU") ;
                cfus = cfus + cfu ;
                voti = voti + (voto *cfu) ;
                average = voti / cfus ;
            }
        } catch (SQLException exc) {
            exc.getErrorCode();
        }

        return average ;
    }



     public ObservableList < PieChart.Data > loadData() {
         try {
             Class.forName(driver) ;
         } catch (ClassNotFoundException e ) {
             e.getMessage() ;
         }
        ObservableList < PieChart.Data > piechartdata;
        piechartdata = FXCollections.observableArrayList();


        try ( PreparedStatement statement =  this.getConnection().prepareStatement(getExams)) {
            statement.setString(1,emailcurrent);
            ResultSet set = statement.executeQuery() ;
            while (set.next()) {
                piechartdata.add(new PieChart.Data("Esami dati" , set.getInt("esamiDati"))) ;

            }
        } catch (Exception e) {
            e.getCause() ;

        }

        return piechartdata ;
    }


    public Number getTotalExams () {
        try {
            Class.forName(driver) ;
        } catch (ClassNotFoundException e ) {
            e.getMessage() ;
        }
        int count = 0 ;

        try (PreparedStatement statement = this.getConnection().prepareStatement(countExams) ;) {
            statement.setString(1,emailcurrent);
            ResultSet set = statement.executeQuery() ;

            while  (set.next()) {
                count++ ;
            }
        }catch (SQLException exc) {
            exc.getErrorCode() ;


        }
        return count ;}

    public ObservableList < PieChart.Data > loadData2() {
        try {
            Class.forName(driver) ;
        } catch (ClassNotFoundException e ) {
            e.getMessage() ;
        }
        ObservableList < PieChart.Data > piechartdata;
        piechartdata = FXCollections.observableArrayList();


        try (PreparedStatement statement = this.getConnection().prepareStatement(getcfusum)){
             ResultSet set = statement.executeQuery() ;
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
            Class.forName(driver) ;
        } catch (ClassNotFoundException e ) {
            e.getMessage() ;
        }

        try (PreparedStatement statement = this.getConnection().prepareStatement(getcfusum)){
            statement.setString(1 , emailcurrent);
         ResultSet set = statement.executeQuery() ;
            while  (set.next()) {
                return set.getInt("cfus") ;
            }
        }catch (SQLException exc) {
            exc.getErrorCode() ;


        }
        return null ;
    }







}










