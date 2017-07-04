package com.mereexams.mereexamscalendar.Models;

import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;
import com.github.tibolte.agendacalendarview.models.WeekItem;

import java.util.Calendar;

/**
 * Created by Bilal on 30-Jun-17.
 */

public class MyCalendarEvent {

    private String startDate;
    private String endDate;
    private String title;
    private String type;
    private int id;
    private int disciplineId;
    private short eventType;
    private short dateType;


    private final String[] EVENT_TYPE = {"Registration",
            "Admit Card",
            "Exam",
            "Result"};

    private final String[] DATE_TYPE = {"Expected", "Confirmed"};

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
    }

    public short getEventType() {
        return eventType;
    }

    public void setEventType(short eventType) {
        this.eventType = eventType;
    }

    public short getDateType() {
        return dateType;
    }

    public void setDateType(short dateType) {
        this.dateType = dateType;
    }

    public int eventColor() {
        // TODO: Modify this method to return the event color
        return 0;
    }
}
