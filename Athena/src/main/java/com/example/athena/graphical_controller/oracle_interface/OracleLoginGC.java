package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.normal.UserBean;
import com.example.athena.exceptions.FindException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.exceptions.UserNotFoundException;
import com.example.athena.use_case_controllers.LoginUseCaseController;
import com.example.athena.view.oracle_view.LabelView;

public class OracleLoginGC {

    public void createParent(String email, String pass) {
        UserBean bean = new UserBean();
        LabelView view = new LabelView();
        bean.setEmail(email);
        bean.setPassword(pass);
        LoginUseCaseController controller = new LoginUseCaseController();
        try {
            controller.logout() ;
            controller.findUser(bean);
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Access granted"));
        } catch (UserNotFoundException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("User not found"));
        } catch (UserInfoException | FindException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in login, details follow: " + e.getMessage()));
        }
    }
}