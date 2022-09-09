package tests;

import com.example.athena.beans.normal.BookBean;
import com.example.athena.beans.normal.UserBean;
import com.example.athena.boundaries.PurchaseBoundary;
import com.example.athena.exceptions.*;
import com.example.athena.graphical_controller.oracle_interface.OracleAverageGC;
import com.example.athena.graphical_controller.oracle_interface.parsers.CommandParser;
import com.example.athena.use_case_controllers.LoginUseCaseController;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**Molinaro Luca**/

public class BookTest {

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
        CommandParser commandParser = new CommandParser();
        try {
            commandParser.parseCommand("login#alba@students.it#tramonto");
            OracleAverageGC oracleAverageGC = new OracleAverageGC();

        } catch (ExceededLoginsException e) {
            fail();
        }

    }
}

