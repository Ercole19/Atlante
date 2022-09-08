package com.example.athena.beans;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ReviewTutorSendUsernameBean {
    
    String getUsername();
    String getSubject();
    LocalDate getDay();
    LocalTime getStartTime();
    LocalTime getEndTime();
    
}
