package com.example.athena.dao;

import com.example.athena.entities.LoggedTutor;
import com.example.athena.entities.TutorInfoEntity;
import com.example.athena.exceptions.CourseException;
import com.example.athena.exceptions.NoCvException;
import com.example.athena.exceptions.UserInfoException;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TutorDAO extends AbstractDAO {

    private static final String GET_BASE_INFO = "SELECT nome, surname, aboutme, sessioninfos, contactnumbers, Average, reviewsNumber " +
                                                "FROM athena.utenti JOIN tutordescription ON email = emailuser " +
                                                "WHERE email = ?" ;

    private static final String SAVE_TUTOR = "UPDATE athena.tutordescription " +
                                            "SET aboutme = ?, sessioninfos = ?, contactnumbers = ?, Average = ?, reviewsnumber = ? " +
                                            "WHERE emailuser = ?" ;


    public TutorInfoEntity getTutorFromDB(String mail) throws UserInfoException, CourseException{
        TutorInfoEntity retVal = getBaseInfo(mail) ;
        retVal.setCourses(getTutorCourses(mail));
        return retVal ;
    }

    public void saveInDB(TutorInfoEntity tutor) throws UserInfoException {
        try (PreparedStatement statement = this.getConnection().prepareStatement(SAVE_TUTOR)) {

            statement.setString(1, tutor.getAboutMe());
            statement.setString(2, tutor.getSessionInfos());
            statement.setString(3, tutor.getContactNumbers());
            statement.setFloat(4, tutor.getAvgReview());
            statement.setInt(5, tutor.getReviewNumber());
            statement.setString(6, tutor.getEmail()) ;
            statement.execute();

        } catch (SQLException | IOException exc) {
            throw new UserInfoException(exc.getMessage());
        }
    }

    private TutorInfoEntity getBaseInfo(String email) throws UserInfoException {

        try (PreparedStatement statement = this.getConnection().prepareStatement(GET_BASE_INFO)) {

            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                String name = set.getString(1) ;
                String surname = set.getString(2);
                String aboutMe = set.getString(3);
                String sessionInfos = set.getString(4);
                String contactNumbers = set.getString(5);
                float average = set.getFloat(6) ;
                int reviewsNumber = set.getInt(7) ;

                List<String> courses = new ArrayList<>() ;

                return new TutorInfoEntity(email, aboutMe, sessionInfos, contactNumbers, name, surname, average, reviewsNumber, courses) ;
            }

            throw new UserInfoException("User not found") ;

        } catch (SQLException | IOException exc) {
            throw new UserInfoException(exc.getMessage());
        }
    }

    public void addCourse(String course) throws CourseException {
        try (PreparedStatement statement = this.getConnection().prepareStatement("INSERT INTO corsi (nomecorso , emailtutor) VALUES (?,?)")) {
            statement.setString(1, course);
            statement.setString(2, LoggedTutor.getInstance().getEmail());
            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new CourseException(e.getMessage());
        }

    }

    public void deleteCourse(String course) throws CourseException {
        try (PreparedStatement statement = this.getConnection().prepareStatement("call athena.deleteCourse(?,?)")) {
            statement.setString(1, course);
            statement.setString(2, LoggedTutor.getInstance().getEmail());
            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new CourseException(e.getMessage());
        }

    }

    private List<String> getTutorCourses(String email) throws CourseException {
        List<String> courses = new ArrayList<>();
        try (PreparedStatement statement = this.getConnection().prepareStatement("SELECT nomecorso FROM corsi WHERE emailtutor = ?")) {
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                courses.add(set.getString(1));
            }
        } catch (SQLException | IOException e) {
            throw new CourseException(e.getMessage());
        }
        return courses;
    }

    public void insertCv(File cv)  throws UserInfoException {
        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement("update athena.tutordescription  set CV = ?    where emailuser = ?")) {
            preparedStatement.setBlob(1, new BufferedInputStream(new FileInputStream(cv)));
            preparedStatement.setString(2, LoggedTutor.getInstance().getEmail());
            preparedStatement.execute();


        } catch (SQLException | IOException exc) {
            throw new UserInfoException(exc.getMessage());
        }
    }


    public File getCV(String email) throws UserInfoException, NoCvException {
        try (PreparedStatement statement = this.getConnection().prepareStatement("Select CV from athena.tutordescription where emailuser = ? ")) {

            statement.setString(1, email);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                byte[] cvBytes = set.getBlob(1).getBytes(1, (int) set.getBlob(1).length());
                String pathname = "src/main/resources/tutor_cv/tempCV.html" ;
                return writeCv(cvBytes, pathname);
            }
            throw new NoCvException("File not found") ;
        } catch (SQLException | IOException exc) {
            throw new UserInfoException (exc.getMessage());
        } catch (NullPointerException e) {
            throw new NoCvException("No CV found") ;
        }
    }

    private File writeCv(byte[] cv, String pathname) throws IOException
    {
        File file = new File(pathname);
        file.deleteOnExit();
        try(OutputStream writeStream = new FileOutputStream(file))
        {
            writeStream.write(cv, 0, cv.length);
        }

        return file ;
    }
}
