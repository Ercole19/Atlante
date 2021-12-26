package com.example.athena;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.sql.*;

public class examDao {
    private String user = "root" ;
    private String pass = "Salamandra230!" ;
    private String dbUrl = "jdbc:mysql://localhost:3306/atena" ;
    private String getQuery = "SELECT * FROM esami" ;
    private String deleteQuery = "DELETE FROM esami WHERE Nome = ?" ;
    private String addQuery = " INSERT INTO esami  VALUES (?,?,?,?); " ;
    private String updateQuery = "UPDATE `esami` SET `Nome` = ? , `Voto` = ? , `CFU` = ? , `Data` = ?  WHERE (`Nome` = ? )  " ;
    private String sortedExams = "SELECT  Voto , Data  from  esami order by Data ASC";




    public ObservableList<examEntityBean> getExamlist()  {
        Connection connection = null ;
        Statement stm = null ;
        ObservableList<examEntityBean> examlist = FXCollections.observableArrayList();
        try {
            connection = DriverManager.getConnection(dbUrl, user, pass);
            stm = connection.createStatement();
            ResultSet set = stm.executeQuery(getQuery);

            while (set.next()) {
                examEntityBean exam = new examEntityBean();
                exam.setExamName(set.getString("Nome"));
                exam.setVotoEsame(set.getString("Voto"));
                exam.setCfuEsame(set.getString("CFU"));
                exam.setDate(set.getString("Data"));
                examlist.add(exam);

            }


        } catch (SQLException  exc) {
            exc.getMessage();
        }
        finally {
            try {
                connection.close();
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return examlist;

    }

    public void addExam(examEntityBean beanExam) {
        Connection connection = null ;
        PreparedStatement stm = null ;
        try {
            connection = DriverManager.getConnection(dbUrl, user, pass);
            stm =connection.prepareStatement(addQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            stm.setString(1, beanExam.getExamName());
            stm.setString(2, String.valueOf(beanExam.getVotoEsame()));
            stm.setString(3, String.valueOf(beanExam.getCfuEsame()));
            stm.setString(4, beanExam.getDate());
            stm.executeUpdate();


        } catch (Exception exc) {
            exc.getCause();
        }
        finally {
            try {
                connection.close();
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public void deleteExam(String nome)  {
        Connection connection = null ;
        PreparedStatement stm = null ;
        try {
            connection = DriverManager.getConnection(dbUrl, user, pass) ;
            stm = connection.prepareStatement(deleteQuery) ;
            stm.setString(1, nome);
            stm.execute();
        }catch (SQLException exc) {
            exc.getErrorCode() ;
        }finally {
            try {
                connection.close() ;
                stm.close();
            } catch (SQLException ec) {
                ec.getErrorCode() ;
            }
        }
    }

    public void updateExam(examEntityBean beanExam, String oldName) {
        Connection connection = null ;
        PreparedStatement stm = null ;
        try {
             connection = DriverManager.getConnection(dbUrl, user, pass);
             stm = connection.prepareStatement(updateQuery);
            stm.setString(1, beanExam.getExamName());
            stm.setString(2, String.valueOf(beanExam.getVotoEsame()));
            stm.setString(3, String.valueOf(beanExam.getCfuEsame()));
            stm.setString(4, beanExam.getDate());
            stm.setString(5, oldName);
            stm.executeUpdate();


        } catch (SQLException exc) {
            exc.getErrorCode();
        } finally {
            try {
                connection.close();
                stm.close();
            } catch (SQLException exc ) {
                exc.getErrorCode() ;
            }
        }
    }

    public ObservableList<XYChart.Data<String, Number>> getSortedExams() {

        ObservableList<XYChart.Data<String, Number>> list = FXCollections.observableArrayList();
        Connection connection = null ;
        Statement statement = null ;
        try {
            Integer count = 1;
            double average = 0.0;
            double counterVoti = 0.0 ;
            String data ;
            int voto ;
            connection = DriverManager.getConnection(dbUrl, user, pass);
            statement = connection.createStatement();
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
        Connection connection = null ;
        Statement stm = null ;


        try {
            connection = DriverManager.getConnection(dbUrl, user, pass);
            stm = connection.createStatement();
            ResultSet set = stm.executeQuery("SELECT AVG(VOTO) as media  FROM esami");
            while (set.next()) {
                return set.getDouble("media");

            }
        } catch (SQLException exc) {
            exc.getErrorCode();
        } finally {
            try {
                connection.close();
                stm.close();
            } catch (SQLException exc)  {
                exc.getErrorCode() ;
            }
        }

        return null ;
    }

    public ObservableList<XYChart.Data<String, Number>> getSortedExamsWeighted() {
        Connection connection =  null ;
        Statement statement = null ;
        ObservableList<XYChart.Data<String, Number>> list = FXCollections.observableArrayList();
        try {
            int  cfus = 0 ;
            double average = 0.0;
            double counterVoti = 0.0;
            int voto ;
            int cfu ;
            connection = DriverManager.getConnection(dbUrl, user, pass);
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT  Voto , Data , CFU  from  esami order by Data ASC");

            while (set.next()) {

                String Data = set.getString("Data");
                voto = set.getInt("Voto");
                cfu = set.getInt("CFU") ;
                cfus = cfus + cfu ;
                counterVoti = counterVoti + (voto * cfu );

                average = (counterVoti) / cfus;
                list.add(new XYChart.Data<>(Data, average));



            }
        } catch (SQLException ex) {
            ex.getErrorCode();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException exc )  {
                exc.getErrorCode() ;
            }
        }
        return list;

    }

    public Number  getAverageWeighted  () {
        double voti = 0 ;
        double  cfus = 0 ;
        double average = 0;
        Connection connection = null ;
        Statement stm = null;

        try {
            connection = DriverManager.getConnection(dbUrl, user, pass);
            stm = connection.createStatement();
            ResultSet set = stm.executeQuery("SELECT (Voto) , (CFU)  FROM esami ORDER BY Data ASC");
            while (set.next()) {
                double voto = set.getDouble("Voto") ;
                double  cfu = set.getDouble("CFU") ;
                cfus = cfus + cfu ;
                voti = voti + (voto *cfu) ;
                average = voti / cfus ;
            }
        } catch (SQLException exc) {
            exc.getErrorCode();
        } finally {
            try {
                connection.close();
                stm.close();
            } catch (SQLException exc) {
                exc.getErrorCode() ;
            }
        }

        return average ;
    }



     public ObservableList < PieChart.Data > loadData() {
        ObservableList < PieChart.Data > piechartdata;
        piechartdata = FXCollections.observableArrayList();
        Connection connection = null ;

        try {
            connection = DriverManager.getConnection(dbUrl, user, pass) ;
            ResultSet set = connection.createStatement().executeQuery("SELECT COUNT(Nome) as esamiDati from esami");
            while (set.next()) {
                piechartdata.add(new PieChart.Data("Esami dati" , set.getInt("esamiDati"))) ;

            }
        } catch (Exception e) {
            e.getCause() ;

        } finally {
            try {
                connection.close();
            }catch (SQLException e)  {
                e.getErrorCode() ;
            }
        }
        return piechartdata ;
    }


    public Number getTotalExams () {
        int count = 0 ;
        Connection connection = null ;
        try {
            connection = DriverManager.getConnection(dbUrl, user, pass) ;
            ResultSet set = connection.createStatement().executeQuery("SELECT (Nome)  from esami") ;
            while  (set.next()) {
                count++ ;
            }
        }catch (SQLException exc) {
            exc.getErrorCode() ;

        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.getErrorCode() ;
            }
        }
        return count ;}

    public ObservableList < PieChart.Data > loadData2() {
        ObservableList < PieChart.Data > piechartdata;
        piechartdata = FXCollections.observableArrayList();
        Connection connection = null ;

        try {
            connection = DriverManager.getConnection(dbUrl, user, pass) ;
            ResultSet set = connection.createStatement().executeQuery("SELECT SUM(CFU) as cfus from esami");
            while (set.next()) {
                piechartdata.add(new PieChart.Data("CFU possseduti " , set.getInt("cfus"))) ;

            }
        } catch (Exception e) {
            e.getCause() ;

        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.getErrorCode() ;
            }
        }
        return piechartdata ;
    }



    public Number getTotalCfus () {
        Connection connection = null ;
        try {
            connection = DriverManager.getConnection(dbUrl, user, pass) ;
            ResultSet set = connection.createStatement().executeQuery("SELECT SUM(CFU) as cfus  from esami") ;
            while  (set.next()) {
                return set.getInt("cfus") ;
            }
        }catch (SQLException exc) {
            exc.getErrorCode() ;

        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.getErrorCode() ;
            }
        }
        return null ;
    }







}










