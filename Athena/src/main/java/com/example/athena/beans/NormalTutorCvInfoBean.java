package com.example.athena.beans;

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
