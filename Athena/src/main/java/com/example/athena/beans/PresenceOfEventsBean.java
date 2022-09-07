package com.example.athena.beans;

import java.time.LocalDate;
import java.util.Set;

public class PresenceOfEventsBean {
    private Set<LocalDate> eventSet ;

    public Set<LocalDate> getEventSet() {
        return eventSet;
    }

    public void setEventSet(Set<LocalDate> eventSet) {
        this.eventSet = eventSet;
    }
}
