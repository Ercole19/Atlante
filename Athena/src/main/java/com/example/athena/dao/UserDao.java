package com.example.athena.dao;

import com.example.athena.entities.ByCourseOrNameEnum;
import com.example.athena.entities.ExamsOrCfusEnum;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.entities.LoggedTutor;
import com.example.athena.exceptions.*;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends AbstractDAO {

    public boolean findStudent(String studentEmail, String pass) throws FindException{
        try (PreparedStatement stmt = this.getConnection().prepareStatement(" SELECT * FROM utenti WHERE  email = ? and password = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            stmt.setString(1, studentEmail);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();

            return rs.first();

        } catch (SQLException | IOException e) {
            throw  new FindException(e.getMessage());
        }
    }

    public Boolean registerUser(String email, String code) throws UserRegistrationException {

        String queryRegister = "CALL athena.register_choose(?,?)" ;

        try (PreparedStatement stmt = this.getConnection().prepareStatement(queryRegister)) {
            stmt.setString(1, email);
            stmt.setString(2, code);
            stmt.executeUpdate();
            return true;

        } catch (SQLException | IOException exception) {
            throw new UserRegistrationException(exception.getMessage());
        }
    }


    public Object getUserType(String email) throws UserInfoException {

        try (PreparedStatement statement = this.getConnection().prepareStatement("SELECT type FROM utenti WHERE email = ?")) {

            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            set.next();
            return set.getString(1);


        } catch (SQLException | IOException exc) {
            throw new UserInfoException(exc.getMessage());
        }
    }

    public String[] getName(String email) throws UserInfoException{

        String[] infos = new String[2];
        try (PreparedStatement statement = this.getConnection().prepareStatement("select nome,surname from athena.utenti where email = ? ")) {

            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                infos[0] = set.getString(1);
                infos[1] = set.getString(2);
            }

        } catch (SQLException | IOException e) {
           throw new UserInfoException(e.getMessage());
        }
        return infos;
    }

    public void setCfusOrExams(int data, ExamsOrCfusEnum cfuOrExams) throws CareerStatusException {
        String setQuery;

        if (cfuOrExams.toString().equals("SET_MAX_EXAMS")) { setQuery = "Update athena.student_infos set max_exams = ? where email = ?";}
        else {setQuery = "Update athena.student_infos set max_cfus = ? where email = ?";}

        try (PreparedStatement statement = this.getConnection().prepareStatement(setQuery)) {

            statement.setInt(1, data);
            statement.setString(2, LoggedStudent.getInstance().getEmail());

            statement.execute();


        } catch (SQLException | IOException exc) {
            throw new CareerStatusException("Unable to update career status. Details follow: " + exc.getMessage());
        }
    }


    public int getAllExams() throws UserInfoException {

        int total = 0;
        try (PreparedStatement statement = this.getConnection().prepareStatement("Select max_exams from athena.student_infos where email =? ")) {

            statement.setString(1, LoggedStudent.getInstance().getEmail());
            ResultSet set = statement.executeQuery();

            set.next();
            total = set.getInt(1);


        } catch (SQLException | IOException exc) {
            throw new UserInfoException(exc.getMessage());
        }
        return total;
    }


    public int getAllCfus() throws UserInfoException {
        int total = 0;

        try (PreparedStatement statement = this.getConnection().prepareStatement("Select max_cfus from athena.student_infos where email =? ")) {


            statement.setString(1, LoggedStudent.getInstance().getEmail());
            ResultSet set = statement.executeQuery();

            set.next();
            total = set.getInt(1);


        } catch (SQLException | IOException exception) {
            throw new UserInfoException(exception.getMessage());
        }
        return total;
    }


    public int getTotalReport(String email) throws UserInfoException {

        int repNum = 0;
        try(PreparedStatement statement = this.getConnection().prepareStatement("Select report_number from athena.student_infos where email = ?")){

            statement.setString(1, email);

            ResultSet set = statement.executeQuery();

            set.next();
            repNum = set.getInt(1);


        }catch (SQLException | IOException exc){
            throw new UserInfoException(exc.getMessage());
        }
        return repNum;
    }

    public void preRegistration(String email, String password, String type, String name, String surname, String code) throws UserRegistrationException {
        String queryRegister = "INSERT INTO `athena`.`pending_users`(email,password,type,nome,surname,registration_code) VALUES (?,?,?,?,?,?)" ;
        try (PreparedStatement stmt = this.getConnection().prepareStatement(queryRegister)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, type);
            stmt.setString(4, name);
            stmt.setString(5, surname);
            stmt.setString(6, code);
            stmt.executeUpdate();

        } catch (SQLException | IOException exception) {
            throw new UserRegistrationException(exception.getMessage());
        }
    }
}