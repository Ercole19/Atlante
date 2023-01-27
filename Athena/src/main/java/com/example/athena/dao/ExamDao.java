package com.example.athena.dao;

import com.example.athena.entities.EntityExam;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.ExamException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamDao extends AbstractDAO {
    private final String emailcurrent =  LoggedStudent.getInstance().getEmail().getMail() ;


    public ObservableList<EntityExam>  getExamlist() throws ExamException  {

        ObservableList<EntityExam> examlist = FXCollections.observableArrayList();
        try ( PreparedStatement
        statement = this.getConnection().prepareStatement("SELECT Nome , Voto , CFU , Data_Esame FROM esami WHERE email_utente = ? " ) ) {
            statement.setString(1 , emailcurrent);
            ResultSet set = statement.executeQuery() ;

            while (set.next()) {

                String name  = (set.getString("Nome"));
                int grade = (Integer.parseInt(set.getString("Voto")));
                int cfu = Integer.parseInt((set.getString("CFU")));
                String date = (set.getString("Data_Esame"));
                EntityExam exam = new EntityExam(name, grade, cfu, date);
                examlist.add(exam);

            }
        } catch (SQLException | IOException exc) {
            throw new ExamException("Error in retrieving exams from server, details follow: " + exc.getMessage());
        }
        return examlist;
    }

    public void addExam(EntityExam exam) throws ExamException {

        try (PreparedStatement stm =this.getConnection().prepareStatement(" INSERT INTO esami  VALUES (?,?,?,?,?); ")  ){

            stm.setString(1, exam.getName());
            stm.setString(2, String.valueOf(exam.getGrade()));
            stm.setString(3, String.valueOf(exam.getCfu()));
            stm.setString(4, exam.getDate());
            stm.setString(5, emailcurrent);
            stm.executeUpdate();

        } catch (SQLException | IOException exc) {
            throw new ExamException("Error in adding exam, details follow: " + exc.getMessage());
        }

    }

    public void deleteExam(String name) throws ExamException {

        try ( PreparedStatement stm = this.getConnection().prepareStatement("DELETE FROM esami WHERE Nome = ? and email_utente = ?")) {
            stm.setString(1, name);
            stm.setString(2,emailcurrent);
            stm.execute();
        }catch (SQLException | IOException exc) {
            throw new ExamException("Error in deleting exam, details follow: " + exc.getMessage());
        }
    }

    public int getTakenExamsNumber(String email) throws ExamException {
        try (PreparedStatement statement = this.getConnection().prepareStatement("SELECT COUNT(*) FROM esami where email_utente = ?")) {
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            set.next() ;
            return set.getInt(1);
        }catch (SQLException | IOException exc) {
            throw new ExamException("Error in finding taken exams number, details follow: " + exc.getMessage());
        }
    }

    public int getTakenCfus(String email) throws ExamException {
        try (PreparedStatement statement = this.getConnection().prepareStatement("SELECT SUM(CFU) FROM esami where email_utente = ?")) {
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            set.next() ;
            return set.getInt(1);
        }catch (SQLException | IOException exc) {
            throw new ExamException("Error in finding gained cfus number, details follow: " + exc.getMessage());
        }
    }
}