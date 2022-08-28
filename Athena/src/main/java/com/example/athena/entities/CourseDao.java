package com.example.athena.entities;

import com.example.athena.exceptions.CourseException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao extends AbstractDAO {

    public void addCourse(String course) throws CourseException {
        try (PreparedStatement statement = this.getConnection().prepareStatement("INSERT INTO corsi (nomecorso , emailtutor) VALUES (?,?)")) {
            statement.setString(1, course);
            statement.setString(2, Tutor.getInstance().getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CourseException(e.getMessage());
        }

    }

    public void deleteCourse(String course) throws CourseException {
        try (PreparedStatement statement = this.getConnection().prepareStatement("DELETE FROM corsi WHERE nomecorso = ? and emailtutor = ?")) {
            statement.setString(1, course);
            statement.setString(2, Tutor.getInstance().getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CourseException(e.getMessage());
        }

    }

    public List<String> fillCourses(String email)  {
        List<String> courses = new ArrayList<>();
        try (PreparedStatement statement = this.getConnection().prepareStatement("SELECT nomecorso FROM corsi WHERE emailtutor = ?")) {
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                courses.add(set.getString(1));
            }
        } catch (SQLException e) {
            //throw new CourseException(e.getMessage());
        }
        return courses;
    }
}

