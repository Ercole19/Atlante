package com.example.athena.beans.normal;

public class EventReminderWrapperBean
{
    private boolean isThereAReminder ;
    private int hoursBefore;
    private int minutesBefore;

    public EventReminderWrapperBean(boolean isThereAReminder)
    {
        setThereAReminder(isThereAReminder) ;
    }

    public EventReminderWrapperBean(boolean isThereAReminder, int hoursBefore, int minutesBefore)
    {
        setThereAReminder(isThereAReminder) ;
        setHoursBefore(hoursBefore) ;
        setMinutesBefore(minutesBefore) ;
    }

    public void setThereAReminder(boolean value)
    {
        this.isThereAReminder = value ;
    }

    public void setHoursBefore(int hour)
    {
        this.hoursBefore = hour ;
    }

    public void setMinutesBefore(int hour)
    {
        this.minutesBefore = hour ;
    }

    public int getHoursBefore()
    {
        return this.hoursBefore;
    }

    public int getMinutesBefore()
    {
        return this.minutesBefore;
    }

    public boolean getIsThereAReminder()
    {
        return this.isThereAReminder ;
    }
}
