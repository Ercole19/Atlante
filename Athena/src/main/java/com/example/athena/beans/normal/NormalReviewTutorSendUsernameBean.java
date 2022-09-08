package com.example.athena.beans.normal;

import com.example.athena.beans.ReviewTutorSendUsernameBean;

import java.time.LocalDate;
import java.time.LocalTime;

public class NormalReviewTutorSendUsernameBean implements ReviewTutorSendUsernameBean
{
    private String username ;
    private String subject ;
    private LocalDate day ;
    private int startHour ;
    private int startMinute ;
    private int endHour ;
    private int endMinute ;

    public NormalReviewTutorSendUsernameBean(String username, String subject, LocalDate day, int startHour, int startMinute,
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

    @Override
    public String getUsername()
    {
        return this.username ;
    }

    public void setSubject(String subject)
    {
        this.subject = subject ;
    }

    @Override
    public String  getSubject()
    {
        return this.subject ;
    }

    public void setDay(LocalDate day)
    {
        this.day = day ;
    }

    @Override
    public LocalDate getDay()
    {
        return this.day ;
    }

    @Override
    public LocalTime getStartTime() {
        return LocalTime.of(startHour,startMinute) ;
    }
    

    @Override
    public LocalTime getEndTime() {
        return LocalTime.of(endHour, endMinute);
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
