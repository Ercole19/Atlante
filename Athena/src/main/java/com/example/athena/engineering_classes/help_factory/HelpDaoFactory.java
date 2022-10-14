package com.example.athena.engineering_classes.help_factory;

import com.example.athena.entities.HelpDao;
import com.example.athena.entities.HelpDaoEnum;
import com.example.athena.entities.HelpDaoFileSystem;
import com.example.athena.entities.HelpDaoJdbc;

public class HelpDaoFactory {

    private static HelpDaoFactory instance = null ;

    private HelpDaoFactory(){}

    public static synchronized HelpDaoFactory getInstance(){
        if (instance == null){
            instance = new HelpDaoFactory();
        }
        return instance;
    }

    public HelpDao createDao(HelpDaoEnum productType){
        if(productType == HelpDaoEnum.JDBC) {
            return createJDBCDao() ;
        } else return createFilesystemDao() ;
    }

    public HelpDao createFilesystemDao() {
        return new HelpDaoFileSystem() ;
    }

    public HelpDao createJDBCDao() {
        return new HelpDaoJdbc() ;
    }
}

