package com.example.athena.beans;

import com.example.athena.beans.TutorCvInfoBean;

import java.io.File;

public class OracleTutorCvInfoBean implements TutorCvInfoBean {
    
    private File file ;
    
    @Override
    public File getCV() {
        return this.file ;
    }
    
    public void setFile(String pathName) {
        this.file = new File(pathName) ;
        
    }
}
