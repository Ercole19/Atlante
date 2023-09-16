package com.example.athena.dao;

import com.example.athena.exceptions.HelpDaoException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class HelpDaoFileSystem implements HelpDao{

    public String getHelpCommands() throws HelpDaoException {
        String helpCommands = "" ;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(Objects.requireNonNull(getClass().getClassLoader().getResource("help.txt")).toURI())))) {
            String line;
            while ((line = reader.readLine()) != null)
                helpCommands = helpCommands.concat(line).concat("\n");
        }
        catch (IOException | URISyntaxException | NullPointerException e) {
            throw new HelpDaoException(e.getMessage());
        }
        return helpCommands;
    }
}


