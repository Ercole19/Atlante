package com.example.athena.Entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class coursedao {
    private final String emailcurrent =  com.example.athena.Entities.user.getUser().getEmail() ;
    private String user = "test" ;
    private String pass = "test" ;
    private String dbUrl = "jdbc:mysql://78.13.194.135:3306/athena" ;
    private String add = "INSERT INTO corsi (Nome , emailtutor) VALUES (?,?)" ;
    private String delete = "DELETE FROM corsi WHERE Nome = ? and emailtutor = ?" ;
    private String getCourses = "SELECT Nome FROM corsi WHERE emailtutor = ?" ;



    public  void addCourse (String course) {
        try (Connection connection = DriverManager.getConnection(dbUrl , user ,pass); PreparedStatement statement =connection.prepareStatement(add)) {
            statement.setString(1 , course);
            statement.setString(2 , emailcurrent);
            statement.executeUpdate() ;
        }catch (SQLException e ) {
            e.getMessage() ;
        }

    }
    public  void deleteCourse (String course) {
        try (Connection connection = DriverManager.getConnection(dbUrl , user ,pass); PreparedStatement statement =connection.prepareStatement(delete)) {
            statement.setString(1 , course);
            statement.setString(2 , emailcurrent);
            statement.executeUpdate() ;
        }catch (SQLException e ) {
            e.getMessage() ;
        }

    }
    public List<String> fillCourses () {
        List<String> corsi = new ArrayList<String>() ;
        try (Connection connection = DriverManager.getConnection(dbUrl , user ,pass) ; PreparedStatement statement = connection.prepareStatement(getCourses, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setString(1, emailcurrent);
            ResultSet set = statement.executeQuery() ;
            while (set.next()){
                corsi.add(set.getString(1)) ;


            }
        } catch (SQLException e) {
            e.getMessage() ;
        }
        return corsi ;

    }
}
