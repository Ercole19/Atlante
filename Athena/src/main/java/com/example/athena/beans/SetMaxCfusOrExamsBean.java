package com.example.athena.beans;

import com.example.athena.entities.ExamsOrCfusEnum;
import com.example.athena.exceptions.CareerStatusException;

public class SetMaxCfusOrExamsBean {

    private int newMax ;
    private ExamsOrCfusEnum type ;

    public int getNewMax(){
        return this.newMax;
    }

    public ExamsOrCfusEnum getType(){
        return this.type;
    }

    public void setNewMax(String max) throws CareerStatusException
    {
        try {
            this.newMax = Integer.parseInt(max) ;
        }
        catch (NumberFormatException exc) {
            throw new CareerStatusException(exc.getMessage());
        }
    }

    public void setType(ExamsOrCfusEnum type)
    {
        this.type = type ;
    }
}
