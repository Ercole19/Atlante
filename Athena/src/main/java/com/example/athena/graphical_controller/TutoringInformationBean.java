package com.example.athena.graphical_controller;

import java.time.LocalDate;
import java.time.LocalTime;

public class TutoringInformationBean
{
    private String tutorsName ;
    private String tutoringSubject ;
    private String tutoringDaysHour ;

    public TutoringInformationBean(String tutorsName, String tutoringSubject, LocalDate day, LocalTime startTime, LocalTime endTime)
    {
        setTutorsName(tutorsName) ;
        setTutoringSubject(tutoringSubject) ;
        setTutoringDaysHour(day, startTime, endTime) ;
    }

    public void setTutorsName(String tutorsName)
    {
        this.tutorsName = tutorsName ;
    }

    public String getTutorsName()
    {
        return this.tutorsName ;
    }

    public void setTutoringSubject(String tutoringSubject)
    {
        this.tutoringSubject = tutoringSubject ;
    }

    public String getTutoringSubject()
    {
        return this.tutoringSubject ;
    }

    public void setTutoringDaysHour(LocalDate day, LocalTime startTime, LocalTime endTime)
    {
        this.tutoringDaysHour = day.toString() + " " + startTime.getHour() + ":" + startTime.getMinute() + "-"
                + endTime.getHour() + ":" + endTime.getMinute() ;
    }

    public String getTutoringDaysHour()
    {
        return this.tutoringDaysHour ;
    }
}
