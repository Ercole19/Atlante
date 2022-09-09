package com.example.athena.beans.normal;

import com.example.athena.beans.TutorCvInfoBean;

import java.io.File;

public class NormalTutorCvInfoBean implements TutorCvInfoBean {
    private File file ;
    
    @Override
    public File getCV() {
        return this.file ;
    }
    
    public void setFile(File file) {
        this.file = file ;
    }
}
