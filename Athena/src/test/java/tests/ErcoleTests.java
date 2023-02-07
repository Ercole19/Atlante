package tests;

import com.example.athena.beans.EventBean;
import com.example.athena.beans.EventsDayBean;
import com.example.athena.beans.NormalExamBean;
import com.example.athena.beans.UserBean;
import com.example.athena.entities.CalendarSubject;
import com.example.athena.entities.PersonalTakenExams;
import com.example.athena.exceptions.*;
import com.example.athena.use_case_controllers.LoginUseCaseController;
import com.example.athena.use_case_controllers.ManageEventUCC;
import com.example.athena.use_case_controllers.ManageExamsUCC;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**Ercole Simone**/
public class ErcoleTests {
    String date = "2022-09-19";

    @Test
    public void firstTest() {
        String failDescription  = "#####################################################################################################################################################################";
        login();
        try {
            EventBean eventBean = new EventBean();
            eventBean.setDate(LocalDate.parse(date));
            eventBean.setName("Event");
            eventBean.setDescription(failDescription);
            eventBean.setStart(LocalTime.parse("20:30"));
            eventBean.setStart(LocalTime.parse("21:30"));
            eventBean.setType("OTHER");
            eventBean.setDateOfReminder(2,30);
        }
        catch (EventException e) {
            assertEquals(e.getMessage(), "Description is too long.");
        }

    }
    @Test
    public void test2() {
        login();
        int prev;
        int after;
        ManageEventUCC controller = new ManageEventUCC();
        try {
            prev = CalendarSubject.getInstance().getEventsOfDay(new EventsDayBean(LocalDate.parse(date))).size() ;
            EventBean eventBean = new EventBean();
            eventBean.setDate(LocalDate.parse(date));
            eventBean.setName("Event");
            eventBean.setDescription("ciao");
            eventBean.setStart(LocalTime.parse("20:30"));
            eventBean.setEnd(LocalTime.parse("21:30"));
            eventBean.setType("OTHER");
            controller.addEvent(eventBean);

            after = CalendarSubject.getInstance().getEventsOfDay(new EventsDayBean(LocalDate.parse(date))).size() ;
            assertEquals(after, prev + 1);
        }
        catch (EventException | SendEmailException e) {
           fail();
        }
    }

    @Test
    public void test3() {
        login();
        int prevCfus;
        int afterCfus;
        try {
            NormalExamBean bean = new NormalExamBean();
            bean.setExamDate(LocalDate.parse("2022-10-12"));
            bean.setExamCfu("9");
            bean.setExamGrade("22");
            bean.setExamName("Exam");

            prevCfus = PersonalTakenExams.getInstance().getGainedCfusNumber();
            ManageExamsUCC controller = new ManageExamsUCC();
            controller.addExam(bean);
            afterCfus = PersonalTakenExams.getInstance().getGainedCfusNumber();
            assertEquals(afterCfus, prevCfus + 9);

        }catch (ExamException e) {
            fail();
        }
    }

    public void login() {
        UserBean params = new UserBean() ;
        params.setEmail("alba@student.it");
        params.setPassword("tramonto") ;
        LoginUseCaseController controller = new LoginUseCaseController() ;

        try {
            controller.findUser(params) ;
        } catch (UserNotFoundException | UserInfoException | FindException | StudentInfoException e) {
            fail() ;
        }
    }

}
