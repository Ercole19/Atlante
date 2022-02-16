package com.example.athena.entities;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao extends AbstractDAO {
    private final String emailcurrent =  User.getUser().getEmail() ;
    private String add = "INSERT INTO corsi (nomecorso , emailtutor) VALUES (?,?)" ;
    private String delete = "DELETE FROM corsi WHERE nomecorso = ? and emailtutor = ?" ;
    private String getCourses = "SELECT nomecorso FROM corsi WHERE emailtutor = ?" ;
    private String checkQuery  = "SELECT * from corsi where nomecorso = ? and emailtutor= ?";



    public  void addCourse (String course) {
        try ( PreparedStatement statement =this.getConnection().prepareStatement(add)) {
            statement.setString(1, course);
            statement.setString(2, emailcurrent);
            statement.executeUpdate() ;
        }catch (SQLException e ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in adding course", ButtonType.CLOSE) ;
            alert.showAndWait() ;
        }

    }
    public  void deleteCourse (String course) {
        try ( PreparedStatement statement =this.getConnection().prepareStatement(delete)) {
            statement.setString(1 , course);
            statement.setString(2 , emailcurrent);
            statement.executeUpdate() ;
        }catch (SQLException e ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in deleting course", ButtonType.CLOSE) ;
            alert.showAndWait() ;
        }

    }
    public List<String> fillCourses (String email) {
        List<String> corsi = new ArrayList<>() ;
        try ( PreparedStatement statement = this.getConnection().prepareStatement(getCourses)) {
            statement.setString(1, email);
            ResultSet set = statement.executeQuery() ;
            while (set.next()){
                corsi.add(set.getString(1)) ;


            }
        } catch (SQLException e) {
            e.getMessage() ;
        }
        return corsi ;

    }

    public boolean checkCourseExist(String name) {
        try (PreparedStatement statement = this.getConnection().prepareStatement(checkQuery)){
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
