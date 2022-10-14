package com.example.athena.entities;

import com.example.athena.exceptions.HelpDaoException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public interface HelpDao {
    String getHelpCommands() throws HelpDaoException;
}
