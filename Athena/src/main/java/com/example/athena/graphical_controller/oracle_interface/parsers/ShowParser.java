package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class ShowParser {

    public void parseShowCommand(List<String> commandToken) {
        LabelView view = new LabelView();
        switch (commandToken.get(0)) {
            case "calendar":
                ShowCalendarParser showEventParser = new ShowCalendarParser();
                commandToken.remove(0);
                showEventParser.showEventParse(commandToken);
                break;
            case "plots" :
                ShowPlotsParser showPlotsParser = new ShowPlotsParser();
                commandToken.remove(0) ;
                showPlotsParser.parseShowPlots(commandToken);
                break;
            case "tutors" :
                ShowTutorsParser showTutorsParser = new ShowTutorsParser();
                commandToken.remove(0);
                showTutorsParser.parseShowTutor(commandToken);
                break;
            case "my_courses" :
                ShowMyCoursesParser coursesParser = new ShowMyCoursesParser() ;
                coursesParser.parseMyCourses();
                break;
            case "my_infos" :
                ShowMyInfosParser personalPageParser = new ShowMyInfosParser();
                personalPageParser.parseMyInfos();
                break;
            case "my_cv" :
                ShowMyCVParser myCVParser = new ShowMyCVParser();
                myCVParser.parseInfos();
                break;
            default:
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("this command do not exists: type help to see the full list"));
        }

    }
}
