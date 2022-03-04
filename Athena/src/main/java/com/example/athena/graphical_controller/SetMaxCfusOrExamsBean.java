package com.example.athena.graphical_controller;

import com.example.athena.entities.ExamsOrCfusEnum;

public class SetMaxCfusOrExamsBean {

    private int newMax ;
    private ExamsOrCfusEnum type ;

    public int getNewMax(){
        return this.newMax;
    }

    public ExamsOrCfusEnum getType(){
        return this.type;
    }

    public void setNewMax(int max)
    {
        this.newMax = max ;
    }

    public void setType(ExamsOrCfusEnum type)
    {
        this.type = type ;
    }
}
