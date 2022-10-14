package com.example.athena.entities;

import com.example.athena.exceptions.HelpDaoException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelpDaoJdbc extends AbstractDAO implements HelpDao{

    @Override
    public String getHelpCommands() throws HelpDaoException {
        try (PreparedStatement statement = super.getConnection().prepareStatement("SELECT * from athena.helpfile ")) {
            ResultSet set = statement.executeQuery();
            set.next();
            return new String(set.getBlob(1).getBytes(1, (int) set.getBlob(1).length()));
        }
        catch (SQLException | IOException e) {
            throw new HelpDaoException(e.getMessage());
        }
    }
}
