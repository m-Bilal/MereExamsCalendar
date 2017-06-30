package com.mereexams.mereexamscalendar.Models;

import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;
import com.github.tibolte.agendacalendarview.models.WeekItem;

import java.util.Calendar;

/**
 * Created by Bilal on 30-Jun-17.
 */

public class MyCalendarEvent implements CalendarEvent {

    private long id;
    private Calendar startTime;
    private Calendar endTime;
    private String title;
    private Calendar instanceDay;
    private DayItem dayReference;
    private WeekItem weekReference;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Calendar getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    @Override
    public Calendar getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Calendar getInstanceDay() {
        return instanceDay;
    }

    @Override
    public void setInstanceDay(Calendar instanceDay) {
        this.instanceDay = instanceDay;
    }

    public DayItem getDayReference() {
        return dayReference;
    }

    public void setDayReference(DayItem dayRefernce) {
        this.dayReference = dayRefernce;
    }

    public WeekItem getWeekReference() {
        return weekReference;
    }

    public void setWeekReference(WeekItem weekRefernce) {
        this.weekReference = weekRefernce;
    }

    public MyCalendarEvent copy() {
        return this;
    }
}
