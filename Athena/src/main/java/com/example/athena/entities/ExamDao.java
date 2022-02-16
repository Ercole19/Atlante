package com.example.athena.entities;

import com.example.athena.graphical_controller.ExamEntityBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamDao extends AbstractDAO {
    private String emailcurrent =  User.getUser().getEmail() ;
    private String getquery = "SELECT Nome , Voto , CFU , Data_Esame FROM esami WHERE email_utente = ? " ;
    private String deleteQuery = "DELETE FROM esami WHERE Nome = ? and email_utente = ?" ;
    private String addQuery = " INSERT INTO esami  VALUES (?,?,?,?,?); " ;
    private String updateQuery = "UPDATE athena.esami SET `Nome` = ? , `Voto` = ? , `CFU` = ? , `Data_Esame` = ? where Nome = ? and email_utente = ? " ;
    private String sortedExams = "SELECT  Voto , Data_Esame   from  esami WHERE email_utente =  ? order by Data_Esame" ;
    private String weightedsortedExams = "SELECT  Voto , Data_Esame , CFU  from  esami WHERE email_utente =  ? order by Data_Esame  " ;
    private String getaverage = "SELECT AVG(VOTO) as media  FROM esami WHERE email_utente = ?" ;
    private String getexamsdate = "SELECT (Voto) , (CFU)  FROM esami WHERE email_utente = ? ORDER BY Data_Esame ASC " ;
    private String countExams = "SELECT COUNT(Nome) as esamiDati from esami WHERE email_utente = ?" ;
    private String getExams = "SELECT (Nome)  from esami WHERE email_utente = ?" ;
    private String getcfusum = "SELECT SUM(CFU) as cfus from esami WHERE email_utente = ?" ;
    private String dataEsame = "Data_Esame" ;




    public ObservableList<EntityExam>  getExamlist()  {

        ObservableList<EntityExam> examlist = FXCollections.observableArrayList();
        try ( PreparedStatement
        statement = this.getConnection().prepareStatement(getquery) ) {
            statement.setString(1 , emailcurrent);
            ResultSet set = statement.executeQuery() ;

            while (set.next()) {

                String nome  = (set.getString("Nome"));
                int voto = (Integer.parseInt(set.getString("Voto")));
                int cfu = Integer.parseInt((set.getString("CFU")));
                String date = (set.getString(dataEsame));
                EntityExam exam = new EntityExam(nome, voto, cfu, date);
                examlist.add(exam);

            }



        } catch (SQLException  exc) {
            exc.getMessage();
        }

        return examlist;

    }

    public void addExam(EntityExam exam) {

        try (PreparedStatement stm =this.getConnection().prepareStatement(addQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)  ){

            stm.setString(1, exam.getNome());
            stm.setString(2, String.valueOf(exam.getVoto()));
            stm.setString(3, String.valueOf(exam.getCfu()));
            stm.setString(4, exam.getData());
            stm.setString(5, emailcurrent);
            stm.executeUpdate();

        } catch (Exception exc) {
            exc.getCause();
        }


    }

    public void deleteExam(String nome)  {

        try ( PreparedStatement stm = this.getConnection().prepareStatement(deleteQuery)) {
            stm.setString(1, nome);
            stm.setString(2,emailcurrent);
            stm.execute();
        }catch (SQLException exc) {
            exc.getErrorCode();
        }
    }

    public void updateExam(ExamEntityBean beanExam, String oldname) {


        try( PreparedStatement stm = this.getConnection().prepareStatement(updateQuery)) {

            stm.setString(1, beanExam.getExamName());
            stm.setString(2, String.valueOf(beanExam.getVotoEsame()));
            stm.setString(3, String.valueOf(beanExam.getCfuEsame()));
            stm.setString(4, beanExam.getDate());
            stm.setString(5,oldname);
            stm.setString(6,emailcurrent);
            stm.executeUpdate();


        } catch (SQLException exc) {
            exc.getErrorCode();
        }
    }

    public ObservableList<XYChart.Data<String, Number>> getSortedExams() {


        ObservableList<XYChart.Data<String, Number>> list = FXCollections.observableArrayList();

        try (PreparedStatement statement =this.getConnection().prepareStatement(sortedExams)){
            statement.setString(1,emailcurrent);
            Integer count = 1;
            double average = 0.0;
            double counterVoti = 0.0 ;
            String data ;
            int voto ;

            ResultSet set = statement.executeQuery() ;

            while (set.next()) {

                data = set.getString(dataEsame);
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

                String data = set.getString(dataEsame);
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


        ObservableList < PieChart.Data > piechartdata;
        piechartdata = FXCollections.observableArrayList();


        try ( PreparedStatement statement =  this.getConnection().prepareStatement(countExams)) {
            statement.setString(1,emailcurrent);
            ResultSet set = statement.executeQuery() ;
            while (set.next()) {
                piechartdata.add(new PieChart.Data("Esami dati" , set.getInt(1))) ;

            }
        } catch (Exception e) {
            e.getCause() ;

        }

        return piechartdata ;
    }


    public Number getTotalExams () {

        int count = 0 ;

        try (PreparedStatement statement = this.getConnection().prepareStatement(getExams) ) {
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

        ObservableList < PieChart.Data > piechartdata;
        piechartdata = FXCollections.observableArrayList();


        try (PreparedStatement statement = this.getConnection().prepareStatement(getcfusum)){

            statement.setString(1 , emailcurrent) ;
             ResultSet set = statement.executeQuery() ;
            while (set.next()) {
                piechartdata.add(new PieChart.Data("CFU possseduti " , set.getInt(1))) ;

            }
        } catch (Exception e) {
            e.getCause() ;

        }
        return piechartdata ;
    }



    public Number getTotalCfus () {


        try (PreparedStatement statement = this.getConnection().prepareStatement(getcfusum)){
            statement.setString(1 , emailcurrent);
         ResultSet set = statement.executeQuery() ;
            while  (set.next()) {
                return set.getInt(1) ;
            }
        }catch (SQLException exc) {
            exc.getErrorCode() ;


        }
        return null ;
    }







}










