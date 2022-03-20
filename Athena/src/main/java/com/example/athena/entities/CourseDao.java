package com.example.athena.entities;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao extends AbstractDAO {
    private final String emailcurrent =  Tutor.getInstance().getEmail() ;


    public  void addCourse (String course) {
        try ( PreparedStatement statement =this.getConnection().prepareStatement("INSERT INTO corsi (nomecorso , emailtutor) VALUES (?,?)")) {
            statement.setString(1, course);
            statement.setString(2, emailcurrent);
            statement.executeUpdate() ;
        }catch (SQLException e ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in adding course", ButtonType.CLOSE) ;
            alert.showAndWait() ;
        }

    }
    public  void deleteCourse (String course) {
        try ( PreparedStatement statement =this.getConnection().prepareStatement("DELETE FROM corsi WHERE nomecorso = ? and emailtutor = ?")) {
            statement.setString(1 , course);
            statement.setString(2 , emailcurrent);
            statement.executeUpdate() ;
        }catch (SQLException e ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in deleting course", ButtonType.CLOSE) ;
            alert.showAndWait() ;
        }

    }
    public List<String> fillCourses (String email) {
        List<String> courses = new ArrayList<>() ;
        try ( PreparedStatement statement = this.getConnection().prepareStatement("SELECT nomecorso FROM corsi WHERE emailtutor = ?")) {
            statement.setString(1, email);
            ResultSet set = statement.executeQuery() ;
            while (set.next()){
                courses.add(set.getString(1)) ;


            }
        } catch (SQLException e) {
            e.getMessage() ;
        }
        return courses ;

    }

    public boolean checkCourseExist(String name) {
        try (PreparedStatement statement = this.getConnection().prepareStatement("SELECT * from corsi where nomecorso = ? and emailtutor= ?")){
            statement.setString(1, name);
            statement.setString(2, emailcurrent);

            ResultSet set = statement.executeQuery();
            if (set.next()){
                return true ;
            }

        }
        catch (SQLException e) {
            e.getMessage();

        }return false;
    }
}
