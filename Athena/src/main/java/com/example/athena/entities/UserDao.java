package com.example.athena.entities;

import com.example.athena.exceptions.*;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> fillTutorInfosProcedure(String email) throws UserInfoException {
        List<String> infos = new ArrayList<>();
        try (PreparedStatement statement = this.getConnection().prepareStatement("SELECT  aboutme ,  sessioninfos  , contactnumbers  FROM athena.tutordescription WHERE emailuser = ? ")) {

            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                infos.add(set.getString(1));
                infos.add(set.getString(2));
                infos.add(set.getString(3));
            }
        } catch (SQLException | IOException exc) {
            throw new UserInfoException(exc.getMessage());
        }

        return infos;

    }


    public void updateTutorInfos(String about, String sessionInfos, String contactNumbers) throws UserInfoException{
        try (PreparedStatement statement = this.getConnection().prepareStatement("UPDATE athena.tutordescription SET aboutme = ?,  sessioninfos=?, contactnumbers=?  WHERE emailuser= ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setString(1, about);
            statement.setString(2, sessionInfos);
            statement.setString(3, contactNumbers);
            statement.setString(4, Tutor.getInstance().getEmail());

            statement.executeUpdate();
        } catch (SQLException | IOException exc) {
            throw new UserInfoException(exc.getMessage());
        }

    }


    public String[] findTutor(String query, ByCourseOrNameEnum searchEnum, boolean byBestReviews) throws FindException {
        String prepStatement;

        if (searchEnum.toString().equals("BY_COURSE")) {prepStatement = "select utenti.nome , utenti.surname , corsi.nomecorso , tutordescription.Average , utenti.email from athena.tutordescription join athena.corsi on tutordescription.emailuser = corsi.emailtutor join athena.utenti on tutordescription.emailuser = utenti.email where  nomecorso like concat('%' , ? , '%') ";}
        else { prepStatement = "SELECT  utenti.nome ,  utenti.surname , corsi.nomecorso , tutordescription.Average ,  utenti.email FROM athena.utenti join athena.tutordescription on utenti.email = tutordescription.emailuser join athena.corsi on utenti.email = corsi.emailtutor WHERE CONCAT( nome,  ' ', surname ) LIKE  concat ('%' , ? , '%')";}

        if (byBestReviews) {prepStatement = prepStatement + "order by tutordescription.average DESC";}

        String[] tutorInfos = new String[500];
        int i = 0;
        try (PreparedStatement statement = this.getConnection().prepareStatement(prepStatement)) {

            statement.setString(1, query);


            ResultSet set = statement.executeQuery();
            while (set.next()) {
                tutorInfos[i] = set.getString(1);
                tutorInfos[i + 1] = set.getString(2);
                tutorInfos[i + 2] = set.getString(3);
                tutorInfos[i + 3] = Float.toString(set.getFloat(4));
                tutorInfos[i + 4] = set.getString(5);
                i = i + 5;
            }

        } catch (SQLException | IOException exc) {
            throw new FindException(exc.getMessage());
        }

        return tutorInfos;


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


    public float getAvg(String email) throws UserInfoException {
        float avg = 0;

        try (PreparedStatement statement = this.getConnection().prepareStatement("select average from athena.tutordescription where emailuser =?")) {

            statement.setString(1, email);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                avg = set.getFloat(1);
            }


        } catch (SQLException | IOException e) {
            throw new UserInfoException(e.getMessage());
        }
        return avg;

    }


    public void insertCv(File cv)  throws UserInfoException {
        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement("update athena.tutordescription  set CV = ?    where emailuser = ?")) {
            preparedStatement.setBlob(1, new BufferedInputStream(new FileInputStream(cv)));
            preparedStatement.setString(2, Tutor.getInstance().getEmail());
            preparedStatement.execute();


        } catch (SQLException | IOException exc) {
            throw new UserInfoException(exc.getMessage());
        }
    }


    public void getCV(String email) throws UserInfoException, NoCvException {
        try (PreparedStatement statement = this.getConnection().prepareStatement("Select CV from athena.tutordescription where emailuser = ? ")) {

            statement.setString(1, email);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                byte[] cvBytes = set.getBlob(1).getBytes(1, (int) set.getBlob(1).length());
                String pathname = "src/main/resources/tutor_cv/tempCV.html" ;
                writeCv(cvBytes, pathname);
            }

        } catch (SQLException | IOException exc) {
            throw new UserInfoException (exc.getMessage());
        } catch (NullPointerException e) {
            throw new NoCvException("No CV found") ;
        }
    }

    private void writeCv(byte[] cv, String pathname) throws IOException
    {
        File file = new File(pathname);
        file.deleteOnExit();
        try(OutputStream writeStream = new FileOutputStream(file))
        {
            writeStream.write(cv, 0, cv.length);
        }
    }

    public void setCfusOrExams(int data, ExamsOrCfusEnum cfuOrExams) throws CareerStatusException {
        String setQuery;

        if (cfuOrExams.toString().equals("SET_MAX_EXAMS")) { setQuery = "Update athena.student_infos set max_exams = ? where email = ?";}
        else {setQuery = "Update athena.student_infos set max_cfus = ? where email = ?";}

        try (PreparedStatement statement = this.getConnection().prepareStatement(setQuery)) {

            statement.setInt(1, data);
            statement.setString(2, Student.getInstance().getEmail());

            statement.execute();


        } catch (SQLException | IOException exc) {
            throw new CareerStatusException("Unable to update career status. Details follow: " + exc.getMessage());
        }
    }


    public int getAllExams() throws UserInfoException {

        int total = 0;
        try (PreparedStatement statement = this.getConnection().prepareStatement("Select max_exams from athena.student_infos where email =? ")) {

            statement.setString(1, Student.getInstance().getEmail());
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


            statement.setString(1, Student.getInstance().getEmail());
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





