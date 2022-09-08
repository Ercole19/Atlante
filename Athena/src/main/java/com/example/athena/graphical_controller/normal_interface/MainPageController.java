package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.entities.User;

public class MainPageController {

    private final SceneSwitcher switcher = SceneSwitcher.getInstance();

    public void onExamsButtonClick() {
        switcher.switcher( "ExamHomepageView.fxml");
    }

    public void onCalendarButtonClick()  {
        switcher.switcher("CalendarPage.fxml");
    }


    public void onShopButtonClick()  {
        switcher.switcher("bookshop-choose-view.fxml");
    }


    public void onTutorButtonClick()  {
        switcher.switcher("tutorSearchPage.fxml");
    }

    public void onLogoutButtonClick() {
        User.logout();
        switcher.switcher("LoginPage.fxml");
    }
}