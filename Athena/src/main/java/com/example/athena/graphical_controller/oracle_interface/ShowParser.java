package com.example.athena.graphical_controller.oracle_interface;

import java.util.List;

public class ShowParser {

    public void parseShowCommand(List<String> tokenCommand) {

        switch (tokenCommand.get(0)) {
            case "event":
                break;
            case "plots" :
                break;
            case "career_graph":
                break;
            case "taken_exams":
                break;
            case "cfu_graph" :
                break;
            case "max_exams" :
                break;
            case "average_evolution" :
                break;
            case "average" :
                break;
            case "gained_cfu" :
                break;
            case "max_cfu" :
                break;
            case "tutors" :
                break;
            case "books" :
                break;
            case "sold_books" :
                break;
            case "purchased_books" :
                break;
            case "my_courses" :
                ShowMyCoursesParser coursesParser = new ShowMyCoursesParser() ;
                coursesParser.parseMyCourses();
                break;
            case "my_infos" :
                ShowMyInfosParser personalPageParser = new ShowMyInfosParser();
                personalPageParser.parseMyInfos();
                break;
            case "my_CV" :
                ShowMyCVParser myCVParser = new ShowMyCVParser();
                myCVParser.parseInfos();
                break;
            default:
                //throw exeption
        }

    }
}
