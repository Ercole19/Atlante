package tests;

import com.example.athena.beans.ReviewTutorSendUsernameBean;
import com.example.athena.beans.normal.*;
import com.example.athena.entities.*;
import com.example.athena.exceptions.*;

import com.example.athena.use_case_controllers.LoginUseCaseController;
import com.example.athena.use_case_controllers.ReviewTutorUseCaseController;
import com.example.athena.use_case_controllers.SearchTutorUseCaseController;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals ;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail ;

/**Sebastian Roberto Opriscan**/
public class OpriscanTests {


    @Test
    public void testSearchFiltersWorkProperly() {

        login("alba@student.it", "tramonto");

        boolean retVal = searchBy(ByCourseOrNameEnum.BY_COURSE, "Fondamenti") && searchBy(ByCourseOrNameEnum.BY_NAME, "Kanye") ;
        assertTrue(retVal) ;

        User.logout();

    }

    @Test
    public void testTutorReviewSystem() {
        login("Paolo-dentici22@gmail.com", "salmone") ;

        ReviewTutorSendUsernameBean bean = new NormalReviewTutorSendUsernameBean(
                "alba@student.it",
                "Fondamenti",
                LocalDate.of(2021,12, 8),
                13, 13,13,14
        ) ;

        String code = pushCodeToDB(bean) ;

        User.logout();

        login("alba@student.it", "tramonto") ;

        SendReviewBean review = new SendReviewBean(5, code) ;

        try {
            new ReviewTutorUseCaseController().sendReview(review) ;
        } catch (TutorReviewException e) {
            fail() ;
        }

        User.logout() ;

        assertTrue(true);

    }

    @Test
    public void testCalendarSubjectRefreshOnLogout() {
        login("alba@student.it", "tramonto") ;

        EventEntity event = new EventEntity("AABBAA", LocalDate.of(2021, 12, 12), LocalTime.of(10, 30), LocalTime.of(11, 30),"Desc", ActivityTypesEnum.OTHER) ;

        try {
            CalendarSubject.getInstance().addEvent(event);
        } catch (EventException e) {
            fail() ;
        }

        User.logout() ;

        login("student@student.it", "student") ;



        try {
            CalendarEntity entity = CalendarSubject.getInstance().getEntity(YearMonth.of(2021, 12)) ;
            List<EventBean> list = entity.getEvents(LocalDate.of(2021, 12, 12)) ;
            
            for(EventBean eventBean : list) {
                if (eventBean.getName().equals("AABBAA")) fail() ;
            }

            User.logout();
            login("alba@student.it", "tramonto");
            entity.deleteEventEntity(event);
            User.logout();
        } catch (EventException e) {
            fail() ;
        }

        assertTrue(true) ;
    }

    private String pushCodeToDB(ReviewTutorSendUsernameBean usernameBean) {
        String reviewCode = null;
        try {
            reviewCode = TutorReviewCodesGenerator.generateReviewCode(5);
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
