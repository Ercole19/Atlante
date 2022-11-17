package tests;

import com.example.athena.beans.normal.BookBean;
import com.example.athena.beans.normal.ExamAverageInformationBean;
import com.example.athena.beans.normal.UserBean;
import com.example.athena.boundaries.PurchaseBoundary;
import com.example.athena.exceptions.*;
import com.example.athena.graphical_controller.oracle_interface.OracleAverageGC;
import com.example.athena.use_case_controllers.AverageUCC;
import com.example.athena.use_case_controllers.LoginUseCaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;


import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

/**Molinaro Luca**/

public class MolinaroTests {

    @Test
    public void boundaryTest(){
        try {
            PurchaseBoundary.purchase();
            assertFalse(false);
        }
        catch (PurchaseException e){
            fail();
        }
    }

    @Test
    public void pessimisticInsertBookTest() {
        UserBean params = new UserBean();
        params.setEmail("alba@student.it");
        params.setPassword("tramonto");
        LoginUseCaseController controller = new LoginUseCaseController();
        try {
            controller.findUser(params);
        } catch (UserNotFoundException | UserInfoException | FindException e) {
            fail();
        }
        BookBean bean = new BookBean();
        try {
            bean.setIsbn("ciaone233452465367457868553897586476878675087656897506758650123456789");
            bean.setPrice("23.12312312centesimi di euro");
            bean.setTimeStamp(" ");
            bean.setOwner("not me");
            fail();
        }
        catch (BookException e){
            assertTrue(true);
        }
    }

    @Test
    public void optimisticAverageTestSecondInterface(){
        UserBean params = new UserBean();
        params.setEmail("alba@student.it");
        params.setPassword("tramonto");
        LoginUseCaseController uController = new LoginUseCaseController();
        try {
            uController.findUser(params);
        } catch (UserNotFoundException | UserInfoException | FindException e) {
            fail();
        }
        DecimalFormat format = new DecimalFormat("+#.00;-#.00");
        OracleAverageGC oracleAverageGC = new OracleAverageGC();
        AverageUCC controller = new AverageUCC();
        String toTest = oracleAverageGC.getAverageInfos();
        ObservableList<ExamAverageInformationBean> examsArithmeticAverageInfos = FXCollections.observableArrayList();
        try {
            examsArithmeticAverageInfos = controller.getExamsArithmeticAverageInformation();
        }
        catch (ExamException | UserInfoException e){
            fail();
        }
        for (ExamAverageInformationBean bean : examsArithmeticAverageInfos) {
            if (toTest.contains(bean.getDate()) && (toTest.contains(format.format(bean.getAverage())))) {
                assertTrue(true);
            } else {
                fail();
            }
        }
    }
}