package com.example.athena.graphical_controller;

public class PresenceOfEventsBean {
    private boolean isThereAnEvent;

    public PresenceOfEventsBean(boolean condition) {
        setThereAnEvent(condition) ;
    }

    public Boolean getThereAnEvent() {
        return isThereAnEvent;
    }

    public void setThereAnEvent(boolean thereAnEvent) {
        isThereAnEvent = thereAnEvent;
    }
}
