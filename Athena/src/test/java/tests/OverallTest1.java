package tests;

import com.example.athena.beans.ReviewTutorSendUsernameBean;
import com.example.athena.beans.normal.*;
import com.example.athena.entities.ByCourseOrNameEnum;
import com.example.athena.entities.ReviewEntity;
import com.example.athena.entities.TutorReviewCodesGenerator;
import com.example.athena.entities.User;
import com.example.athena.exceptions.*;

import com.example.athena.use_case_controllers.LoginUseCaseController;
import com.example.athena.use_case_controllers.ReviewTutorUseCaseController;
import com.example.athena.use_case_controllers.SearchTutorUseCaseController;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals ;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail ;


public class OverallTest1 {


    @Test
    public void test1() {

        login("alba@student.it", "tramonto");

        boolean retVal = searchBy(ByCourseOrNameEnum.BY_COURSE, "Fondamenti") && searchBy(ByCourseOrNameEnum.BY_NAME, "Kanye") ;
        assertTrue(retVal) ;

    }

    @Test
    public void test2() {
        login("tutor@tutor.it", "rotut") ;

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

        assertTrue(true);

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
