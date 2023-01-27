package com.example.athena.beans;

import com.example.athena.exceptions.EventException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReminderBean extends EventBean{

    private boolean remove;

    public ReminderBean(EventBean bean, boolean remove) throws EventException {
        this.name = bean.getName();
        this.start = bean.getStart() ;
        this.end = bean.getEnd() ;
        this.date = bean.getDate() ;
        this.type = bean.getType() ;
        this.description = bean.getDescription() ;
        this.dateOfReminder = bean.getDateOfReminder() ;
        this.remove = remove ;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }
}
