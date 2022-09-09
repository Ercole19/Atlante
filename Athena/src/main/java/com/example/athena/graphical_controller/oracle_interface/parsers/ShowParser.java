package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class ShowParser {

    public void parseShowCommand(List<String> commandToken) {
        LabelView view = new LabelView();
        switch (commandToken.get(0)) {
            case "events":
                ShowEventParser showEventParser = new ShowEventParser();
                commandToken.remove(0);
                showEventParser.showEventParse(commandToken);
                break;
            case "plots" :
                ShowPlotsParser showPlotsParser = new ShowPlotsParser();
                showPlotsParser.parseShowPlots();
                break;
            case "exams_completion":
                ShowExamsCompletionParser showExamsCompletionParser = new ShowExamsCompletionParser();
                showExamsCompletionParser.parseExamsCompletion();
                break;
            case "cfu_completion" :
                ShowCfusCompletionParser showCfusCompletionParser = new ShowCfusCompletionParser();
                showCfusCompletionParser.parseCfusCompletion();
                break;
            case "taken_exams" :
                ShowTakenExamsParser showTakenExamsParser = new ShowTakenExamsParser();
                showTakenExamsParser.parseTakenExams();
                break;
            case "averages" :
                ShowAllAveragesParser showAllAveragesParser = new ShowAllAveragesParser();
                showAllAveragesParser.parseAllAverages();
                break ;
            case "tutors" :
                ShowTutorsParser showTutorsParser = new ShowTutorsParser();
                commandToken.remove(0);
                showTutorsParser.parseShowTutor(commandToken);
                break;
            case "books" :
                ShowBooksParser showBooksParser = new ShowBooksParser();
                commandToken.remove(0);
                showBooksParser.parseShowBooks(commandToken);
                break;
            case "sold_books" :
                ShowSoldBooksParser soldBooksParser = new ShowSoldBooksParser();
                soldBooksParser.parseSoldBooks();
                break;
            case "purchased_books" :
                ShowPurchasedBooksParser purchasesParser = new ShowPurchasedBooksParser() ;
                purchasesParser.showPurchasesParse();
                break;
            case "my_selling_books":
                ShowMySellingBooksParser showMySellingBooksParser = new ShowMySellingBooksParser();
                showMySellingBooksParser.parseShowMySellingBooks();
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
