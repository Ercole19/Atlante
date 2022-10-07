package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.exceptions.ExceededLoginsException;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandParser {

    public void parseCommand(String command) throws ExceededLoginsException {
        String cleanToken = cleanUp(command);
        LabelView labelView = new LabelView();
        List<String> commandToken = new ArrayList<>(Arrays.asList(cleanToken.split(" {0,100}# {0,100}")));
        switch (commandToken.get(0)) {
            case "login" :
                LoginParser loginParser = new LoginParser();
                commandToken.remove(0) ;
                loginParser.parseLogin(commandToken);
                break;
            case "signup" :
                SignUpParser signUpParser = new SignUpParser();
                commandToken.remove(0);
                signUpParser.parseSignUp(commandToken);
                break;
            case "validate_signup" :
                ValidateSignUpParser validateSignUpParser = new ValidateSignUpParser();
                commandToken.remove(0);
                validateSignUpParser.parseValidateSignUp(commandToken);
                break;
            case "add_event" :
                AddEventParser addEventParser = new AddEventParser();
                commandToken.remove(0);
                addEventParser.parseAddEvent(commandToken);
                break ;
            case "show" :
                ShowParser showParser = new ShowParser();
                commandToken.remove(0);
                showParser.parseShowCommand(commandToken);
                break;
            case "generate_review" :
                GenerateReviewParser generateReviewParser = new GenerateReviewParser();
                generateReviewParser.parseGenerateReview();
                break;
            case "review" :
                ReviewParser reviewParser = new ReviewParser();
                commandToken.remove(0);
                reviewParser.parseReview(commandToken);
                break;
            case "sell_book" :
                SellBookParser sellBookParser = new SellBookParser();
                commandToken.remove(0);
                sellBookParser.parseSellBook(commandToken);
                break;
            case "add_course" :
                AddCoursesParser addCoursesParser = new AddCoursesParser();
                commandToken.remove(0);
                addCoursesParser.parseAddCourse(commandToken);
                break ;
            case "add_cv":
                AddCvParser addCvParser = new AddCvParser();
                commandToken.remove(0);
                addCvParser.parseAddCV(commandToken);
                break;
            case "set_maxCfus":
                SetMaxCfuParser setMaxCfuParser = new SetMaxCfuParser();
                commandToken.remove(0);
                setMaxCfuParser.parseSetMaxCfus(commandToken);
                break;
            case "add_exam":
                AddExamsParser addExamsParser = new AddExamsParser();
                commandToken.remove(0);
                addExamsParser.parseAddExams(commandToken);
                break;
            case "set_maxExams" :
                SetMaxExamsParser setMaxExamsParser = new SetMaxExamsParser();
                commandToken.remove(0);
                setMaxExamsParser.parseSetMaxExams(commandToken);
                break;
            case "delete_course" :
                DeleteCourseParser deleteCourseParser = new DeleteCourseParser();
                commandToken.remove(0);
                deleteCourseParser.parseDeleteCourse(commandToken);
                break;
            case "delete_exam" :
                DeleteExamParser deleteExamParser = new DeleteExamParser();
                commandToken.remove(0);
                deleteExamParser.parseDeleteExams(commandToken);
                break;
            case "update_exam" :
                UpdateExamParser parser = new UpdateExamParser();
                commandToken.remove(0);
                parser.parseUpdateExam(commandToken);
                break;
            case "help" :
                HelpParser helpParser = new HelpParser();
                helpParser.parseHelp();
                break;
            default:
                ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("this command do not exists: type help to see the full list"));
                break;
        }
    }

    private String cleanUp(String command){
        String ret = command;
        int i = 0;
        int j = command.length();

        while(command.charAt(i) == ' ') {
            i++;
        }
        while(command.substring(0, j).endsWith(" ")){
            j--;
        }
        ret = ret.substring(i, j);
        return ret;
    }

}
