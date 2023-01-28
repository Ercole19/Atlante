package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.use_case_controllers.LoginUseCaseController;

public class MainPageController {

    private final SceneSwitcher switcher = SceneSwitcher.getInstance();

    public void onCalendarButtonClick()  {
        switcher.switcher("CalendarPage.fxml");
    }


    public void onTutorButtonClick()  {
        switcher.switcher("tutorSearchPage.fxml");
    }

    public void onLogoutButtonClick() {
        new LoginUseCaseController().logout() ;
        switcher.switcher("LoginPage.fxml");
    }
}