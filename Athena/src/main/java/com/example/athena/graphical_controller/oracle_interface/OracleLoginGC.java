package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.UserBean;
import com.example.athena.exceptions.FindException;
import com.example.athena.exceptions.StudentInfoException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.exceptions.UserNotFoundException;
import com.example.athena.graphical_controller.oracle_interface.login_states.LoginSM;
import com.example.athena.graphical_controller.oracle_interface.login_states.OnPasswordInsert;
import com.example.athena.use_case_controllers.LoginUseCaseController;
import com.example.athena.view.oracle_view.LabelView;

public class OracleLoginGC {

    private String username ;
    private String password ;

    private final LoginSM stateMachine ;

    public OracleLoginGC(String username) {
        this.username = username ;
        this.stateMachine = new LoginSM(this, new OnPasswordInsert(this)) ;
    }

    public void createParent() {
        UserBean bean = new UserBean();
        LabelView view = new LabelView();
        bean.setEmail(username);
        bean.setPassword(password);
        LoginUseCaseController controller = new LoginUseCaseController();
        try {
            controller.logout() ;
            controller.findUser(bean);
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Access granted, you can now use all the commands allowed for your category"));
        } catch (UserNotFoundException e) {
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("User not found"));
        } catch (UserInfoException | FindException | StudentInfoException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in login, details follow: " + e.getMessage()));
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void advance() {
        this.stateMachine.goNext();
    }
}
