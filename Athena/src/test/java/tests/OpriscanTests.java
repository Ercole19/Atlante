package tests;

import com.example.athena.beans.*;
import com.example.athena.entities.*;
import com.example.athena.exceptions.*;

import com.example.athena.use_case_controllers.LoginUseCaseController;
import com.example.athena.use_case_controllers.ReviewTutorUCC;
import com.example.athena.use_case_controllers.SearchTutorUseCaseController;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals ;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail ;

/**Sebastian Roberto Opriscan**/
public class OpriscanTests {

    private static final String TEST_USERNAME = "alba@student.it" ;
    private static final String TEST_PARTICULAR_WORD_FOR_ACCESS = "tramonto" ;
    @Test
    public void testSearchFiltersWorkProperly() {

        login(TEST_USERNAME, TEST_PARTICULAR_WORD_FOR_ACCESS);

        boolean retVal = searchBy(ByCourseOrNameEnum.BY_COURSE, "Fondamenti") && searchBy(ByCourseOrNameEnum.BY_NAME, "Kanye") ;
        assertTrue(retVal) ;

        LoggedUser.logout();

    }

    @Test
    public void testTutorReviewSystem() {
        login("Paolo-dentici22@gmail.com", "salmone") ;

        ReviewInfoBean bean = new ReviewInfoBean() ;

        bean.setStudentMail(TEST_USERNAME) ;
        bean.setTutoringSubject("Fondamenti") ;
        bean.setTutoringDay(LocalDate.of(2021,12, 8));
        bean.setTutoringStartTime(LocalTime.of(13, 13));
        bean.setTutoringEndTime(LocalTime.of(14, 30)) ;

        String code = pushCodeToDB(bean) ;

        LoggedUser.logout();

        login(TEST_USERNAME, TEST_PARTICULAR_WORD_FOR_ACCESS) ;

        ReviewTutorBean review = new ReviewTutorBean(5, code) ;

        try {
            new ReviewTutorUCC().reviewTutor(review) ;
        } catch (TutorReviewException e) {
            fail() ;
        }

        LoggedUser.logout() ;

        assertTrue(true);

    }

    @Test
    public void testCalendarSubjectRefreshOnLogout() {
        login(TEST_USERNAME, TEST_PARTICULAR_WORD_FOR_ACCESS) ;

        try {
            EventEntity event = new EventEntity("AABBAA", LocalDate.of(2021, 12, 12), LocalTime.of(10, 30), LocalTime.of(11, 30),"Desc", ActivityTypesEnum.OTHER) ;
            CalendarSubject.getInstance().addEvent(event);

            LoggedUser.logout() ;

            login("student@student.it", "student") ;


            List<EventBean> list = CalendarSubject.getInstance().getEventsOfDay(new EventsDayBean(LocalDate.of(2021, 12, 12))) ;
            
            for(EventBean eventBean : list) {
                if (eventBean.getName().equals("AABBAA")) fail() ;
            }

            LoggedUser.logout();
            login(TEST_USERNAME, TEST_PARTICULAR_WORD_FOR_ACCESS);
            CalendarSubject.getInstance().deleteEvent(event) ;
            LoggedUser.logout();
        } catch (EventException e) {
            fail() ;
        }

        assertTrue(true) ;
    }

    private String pushCodeToDB(ReviewInfoBean usernameBean) {
        String reviewCode = null;
        try {
            reviewCode = RandomCodesGenerator.generateRandomCode(5);
            ReviewEntity review = new ReviewEntity(usernameBean, reviewCode) ;
            review.toDB() ;

        } catch (NoSuchAlgorithmException | TutorReviewException e) {
            fail() ;
        }

        return reviewCode ;
    }

    private void login(String username, String password) {
        UserBean params = new UserBean() ;
        params.setEmail(username);
        params.setPassword(password) ;
        LoginUseCaseController controller = new LoginUseCaseController() ;
        try {
            controller.findUser(params) ;
        } catch (UserNotFoundException | UserInfoException | FindException e) {
            fail() ;
        }
    }

    private boolean searchBy(ByCourseOrNameEnum searchEnum, String query) {
        SearchTutorQueryBean bean = new SearchTutorQueryBean() ;
        if(searchEnum == ByCourseOrNameEnum.BY_COURSE) bean.setByCourseOrName("BY_COURSE") ;
        else bean.setByCourseOrName("BY_NAME") ;
        bean.setByBestReviews(false) ;
        bean.setQuery(query);

        List<TutorSearchResultBean> beans = new ArrayList<>() ;
        try {
            beans = new SearchTutorUseCaseController().formatSearchResults(bean) ;
        } catch(FindTutorException e) {
            fail() ;
        }

        boolean firstResult = true ;

        for(TutorSearchResultBean searchResultBean : beans) {
            if (searchEnum == ByCourseOrNameEnum.BY_COURSE) firstResult = firstResult && (searchResultBean.getTaughtSubject().contains(query)) ;
            else firstResult = firstResult && (searchResultBean.getName().contains(query)) ;

        }

        return firstResult ;
    }


}
