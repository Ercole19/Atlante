package com.example.athena.entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDao extends AbstractDAO {
    private final String emailcurrent =  User.getUser().getEmail() ;
    private String add = "INSERT INTO corsi (Nome , emailtutor) VALUES (?,?)" ;
    private String delete = "DELETE FROM corsi WHERE Nome = ? and emailtutor = ?" ;
    private String getCourses = "SELECT Nome FROM corsi WHERE emailtutor = ?" ;



    public  void addCourse (String course) {
        try ( PreparedStatement statement =this.getConnection().prepareStatement(add)) {
            statement.setString(1 , course);
            statement.setString(2 , emailcurrent);
            statement.executeUpdate() ;
        }catch (SQLException e ) {
            e.getMessage() ;
        }

    }
    public  void deleteCourse (String course) {
        try ( PreparedStatement statement =this.getConnection().prepareStatement(delete)) {
            statement.setString(1 , course);
            statement.setString(2 , emailcurrent);
            statement.executeUpdate() ;
        }catch (SQLException e ) {
            e.getMessage() ;
        }

    }
    public List<String> fillCourses () {
        List<String> corsi = new ArrayList<>() ;
        try ( PreparedStatement statement = this.getConnection().prepareStatement(getCourses, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
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
