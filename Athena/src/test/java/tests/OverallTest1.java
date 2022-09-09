package tests;

import com.example.athena.beans.normal.SearchTutorQueryBean;
import com.example.athena.beans.normal.TutorSearchResultBean;
import com.example.athena.beans.normal.UserBean;
import com.example.athena.entities.ByCourseOrNameEnum;
import com.example.athena.exceptions.FindException;
import com.example.athena.exceptions.FindTutorException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.exceptions.UserNotFoundException;

import com.example.athena.use_case_controllers.LoginUseCaseController;
import com.example.athena.use_case_controllers.SearchTutorUseCaseController;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals ;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail ;


public class OverallTest1 {

    @Test
    public void test1() {

        UserBean params = new UserBean() ;
        params.setEmail("alba@student.it");
        params.setPassword("tramonto") ;
        LoginUseCaseController controller = new LoginUseCaseController() ;
        try {
            controller.findUser(params) ;
        } catch (UserNotFoundException | UserInfoException | FindException e) {
            fail() ;
        }
        boolean retVal = searchBy(ByCourseOrNameEnum.BY_COURSE, "Fondamenti") && searchBy(ByCourseOrNameEnum.BY_NAME, "Kanye") ;
        assertTrue(retVal) ;

    }

    private boolean searchBy(ByCourseOrNameEnum searchEnum, String query) {
        SearchTutorQueryBean bean = new SearchTutorQueryBean() ;
        if(searchEnum == ByCourseOrNameEnum.BY_COURSE) bean.setByCourseOrName("byCourse") ;
        else bean.setByCourseOrName("byName") ;
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
            firstResult = firstResult && (searchResultBean.getName().contains(query)) ;
        }

        return firstResult ;
    }
}
