package com.example.athena.dao;

import com.example.athena.exceptions.HelpDaoException;

public interface HelpDao {
    String getHelpCommands() throws HelpDaoException;
}
