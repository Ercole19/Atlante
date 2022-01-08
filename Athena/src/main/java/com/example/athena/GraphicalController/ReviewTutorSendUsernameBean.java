package com.example.athena.GraphicalController;

import com.example.athena.View.SubjectLabels;

import java.time.LocalDate;

public class ReviewTutorSendUsernameBean
{
    private String username ;
    private SubjectLabels subject ;
    private LocalDate day ;
    private int startHour ;
    private int startMinute ;
    private int endHour ;
    private int endMinute ;

    public ReviewTutorSendUsernameBean(String username, SubjectLabels subject, LocalDate day, int startHour, int startMinute,
                                       int endHour, int endMinute)
    {
        setUsername(username) ;
        setSubject(subject) ;
        setDay(day) ;
        setStartHour(startHour) ;
        setStartMinute(startMinute) ;
        setEndHour(endHour) ;
        setEndMinute(endMinute) ;
    }

    public void setUsername(String username)
    {
        this.username = username ;
    }

    public String getUsername()
    {
        return this.username ;
    }

    public void setSubject(SubjectLabels subject)
    {
        this.subject = subject ;
    }

    public SubjectLabels getSubject()
    {
        return this.subject ;
    }

    public void setDay(LocalDate day)
    {
        this.day = day ;
    }

    public LocalDate getDay()
    {
        return this.day ;
    }

    public void setStartHour(int startHour)
    {
        this.startHour = startHour ;
    }

    public int getStartHour()
    {
        return this.startHour ;
    }

    public void setStartMinute(int startMinute)
    {
        this.startMinute = startMinute ;
    }

    public int getStartMinute()
    {
        return this.startMinute ;
    }

    public void setEndHour(int endHour)
    {
        this.endHour = endHour ;
    }

    public int getEndHour()
    {
        return this.endHour ;
    }

    public void setEndMinute(int endMinute)
    {
        this.endMinute = endMinute ;
    }

    public int getEndMinute()
    {
        return this.endMinute ;
    }
}
