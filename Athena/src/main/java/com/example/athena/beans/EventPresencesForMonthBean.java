package com.example.athena.beans;

import java.time.YearMonth;

public class EventPresencesForMonthBean {

    private YearMonth month ;

    public EventPresencesForMonthBean(YearMonth month) {
        this.month = month ;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }
}
