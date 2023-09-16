package com.example.athena.use_case_controllers;

import com.example.athena.beans.HelpBean;
import com.example.athena.dao.HelpDao;
import com.example.athena.dao.HelpDaoEnum;
import com.example.athena.engineering_classes.help_factory.HelpDaoFactory;
import com.example.athena.exceptions.HelpDaoException;

public class OracleHelpUcc {

    public HelpBean getString() throws HelpDaoException {
        HelpBean bean;
        try {
            HelpDao dao = HelpDaoFactory.getInstance().createDao(HelpDaoEnum.FILE_SYSTEM) ;
            String helpCommands = dao.getHelpCommands();
            bean = new HelpBean();
            bean.setHelpCommands(helpCommands);
            return bean;
        } catch(HelpDaoException e) {
            return getJDBCBean();
        }
    }

    private HelpBean getJDBCBean() throws HelpDaoException{
        try {
            HelpDao dao = HelpDaoFactory.getInstance().createDao(HelpDaoEnum.JDBC);
            String helpCommands = dao.getHelpCommands();
            HelpBean bean = new HelpBean();
            bean.setHelpCommands(helpCommands);
            return bean;
        }
        catch (HelpDaoException e){
            throw new HelpDaoException("Failed to get commands either from db and file system");
        }
    }
}