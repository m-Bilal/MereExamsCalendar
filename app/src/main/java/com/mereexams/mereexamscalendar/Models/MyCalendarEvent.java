package com.mereexams.mereexamscalendar.Models;

/**
 * Created by Bilal on 30-Jun-17.
 */

public class MyCalendarEvent {

    private static final String[] EVENT_TYPE = {"Registration",
            "Admit Card",
            "Exam",
            "Result"};

    private static final String[] DATE_TYPE = {"Expected", "Confirmed"};

    public static final short EVENT_TYPE_REGISTRATION = 0;
    public static final short EVENT_TYPE_ADMIT_CARD = 1;
    public static final short EVENT_TYPE_EXAM = 2;
    public static final short EVENT_TYPE_RESULT = 3;

    public static final short DATE_TYPE_EXPECTED = 0;
    public static final short DATE_TYPE_CONFIRMED = 1;

    private String startDate;
    private String endDate;
    private String title;
    private int id;
    private int disciplineId;
    private short eventType;
    private short dateType;

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

    public String getEventType() {
        return EVENT_TYPE[eventType];
    }

    public void setEventType(short eventType) {
        this.eventType = eventType;
    }

    public String getDateType() {
        return DATE_TYPE[dateType];
    }

    public void setDateType(short dateType) {
        this.dateType = dateType;
    }

    public int eventColor() {
        // TODO: Modify this method to return the event color
        return 0;
    }
}
