package com.example.athena.dao;


import com.example.athena.exceptions.FindException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.exceptions.UserRegistrationException;

import java.io.IOException;
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