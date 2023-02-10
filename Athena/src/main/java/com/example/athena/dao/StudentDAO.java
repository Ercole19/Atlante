package com.example.athena.dao;

import com.example.athena.entities.ExamsOrCfusEnum;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.PurchaseException;
import com.example.athena.exceptions.ReportException;
import com.example.athena.exceptions.StudentInfoException;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class StudentDAO extends AbstractDAO {


    public String[] getStudentFullName(String email) throws StudentInfoException {

        String[] infos = new String[2];
        try (PreparedStatement statement = this.getConnection().prepareStatement("select nome,surname from athena.utenti where email = ? ")) {

            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                infos[0] = set.getString(1);
                infos[1] = set.getString(2);
            }

        } catch (SQLException | IOException e) {
            throw new StudentInfoException(e.getMessage());
        }
        return infos;
    }

    public int getTotalReport(String email) throws StudentInfoException {

        int repNum = 0;
        try(PreparedStatement statement = this.getConnection().prepareStatement("Select report_number from athena.student_infos where email = ?")){

            statement.setString(1, email);

            ResultSet set = statement.executeQuery();

            set.next();
            repNum = set.getInt(1);


        }catch (SQLException | IOException exc){
            throw new StudentInfoException(exc.getMessage());
        }
        return repNum;
    }

    public void setCfusOrExams(int data, ExamsOrCfusEnum cfuOrExams) throws StudentInfoException {
        String setQuery;

        if (cfuOrExams.toString().equals("SET_MAX_EXAMS")) { setQuery = "Update athena.student_infos set max_exams = ? where email = ?";}
        else {setQuery = "Update athena.student_infos set max_cfus = ? where email = ?";}

        try (PreparedStatement statement = this.getConnection().prepareStatement(setQuery)) {

            statement.setInt(1, data);
            statement.setString(2, LoggedStudent.getInstance().getEmail().getMail());

            statement.execute();


        } catch (SQLException | IOException exc) {
            throw new StudentInfoException("Unable to update career status. Details follow: " + exc.getMessage());
        }
    }


    public int getMaxExams() throws StudentInfoException {

        int total = 0;
        try (PreparedStatement statement = this.getConnection().prepareStatement("Select max_exams from athena.student_infos where email =? ")) {

            statement.setString(1, LoggedStudent.getInstance().getEmail().getMail());
            ResultSet set = statement.executeQuery();

            set.next();
            total = set.getInt(1);


        } catch (SQLException | IOException exc) {
            throw new StudentInfoException(exc.getMessage());
        }
        return total;
    }


    public int getMaxCfu() throws StudentInfoException {
        int total = 0;

        try (PreparedStatement statement = this.getConnection().prepareStatement("Select max_cfus from athena.student_infos where email =? ")) {


            statement.setString(1, LoggedStudent.getInstance().getEmail().getMail());
            ResultSet set = statement.executeQuery();

            set.next();
            total = set.getInt(1);


        } catch (SQLException | IOException exception) {
            throw new StudentInfoException(exception.getMessage());
        }
        return total;
    }

    public void finalizePurchase(String title, String isbn, Float price, String emailVendor, String timestamp, String purchaser) throws PurchaseException
    {
        try(PreparedStatement statement = this.getConnection().prepareStatement("CALL finalizePurchase(?,?,?,?,?,?)")) {

            statement.setString(1, title);
            statement.setString(2, isbn);
            statement.setFloat(3, price);
            statement.setString(4, purchaser);
            statement.setString(5, emailVendor);
            statement.setTimestamp(6, Timestamp.valueOf(timestamp));

            statement.execute();

        }catch (SQLException | IOException exc) {
            throw new PurchaseException(exc.getMessage());
        }
    }


    public void daoReportSeller(String reporter, String seller) throws ReportException {
        try(PreparedStatement statement = this.getConnection().prepareStatement("call athena.reportSeller(?,?)")) {

            statement.setString(1, reporter);
            statement.setString(2, seller);

            statement.execute();
        } catch (SQLException | IOException exc) {
            throw new ReportException(exc.getMessage());
        }
    }
}
