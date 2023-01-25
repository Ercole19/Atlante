package com.example.athena.beans;

import java.time.LocalDate;
import java.time.LocalTime;

public class TutoringInformationBean
{
    private String tutorsName ;
    private String tutoringSubject ;
    private LocalDate tutoringDay ;

    private LocalTime tutoringStart ;

    private LocalTime tutoringEnd ;

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

    public LocalDate getTutoringDay() {
        return tutoringDay;
    }

    public void setTutoringDay(LocalDate tutoringDay) {
        this.tutoringDay = tutoringDay;
    }

    public LocalTime getTutoringStart() {
        return tutoringStart;
    }

    public void setTutoringStart(LocalTime tutoringStart) {
        this.tutoringStart = tutoringStart;
    }

    public LocalTime getTutoringEnd() {
        return tutoringEnd;
    }

    public void setTutoringEnd(LocalTime tutoringEnd) {
        this.tutoringEnd = tutoringEnd;
    }
}
