package com.example.athena.graphical_controller.oracle_interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandParser {

    public void parseCommand(String command) {
        String cleanToken = cleanUp(command) ;
        List<String> commandToken = new ArrayList<>(Arrays.asList(cleanToken.split(" +")));
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
                addEventParser.parseAddEvent();
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
                sellBookParser.parseSellBook();
                break;
            case "help" :
                //TODO
            default:
                //Throw parsing exception
                break;
        }
    }

    private String cleanUp(String command){
        String ret = command;
        int i = 0;
        int j = command.length();

        while(command.substring(i).startsWith(" ")) {
            i++;
        }
        while(command.substring(0, j).endsWith(" ")){
            j--;
        }
        ret = ret.substring(i, j);
        return ret;
    }

}
