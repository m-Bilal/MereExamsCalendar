package com.mereexams.mereexamscalendar.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bilal on 30-Jun-17.
 */

public class ExamDate {

    @SerializedName("id")
    int id;

    @SerializedName("name")
    String name;

    @SerializedName("disciplineid")
    int disciplineId;

    @SerializedName("regstartcon")
    String registrationStartConfirmed;

    @SerializedName("regendcon")
    String registrationEndConfirmed;

    @SerializedName("regstartexpdt")
    String registrationStartExpected;

    @SerializedName("regendexpdt")
    String registrationEndExpected;

    @SerializedName("admitcardcon")
    String admitCardConfirmed;

    @SerializedName("admitcardexpdt")
    String admitCardExpected;

    @SerializedName("rescon")
    String resultConfirmed;

    @SerializedName("resexpdt")
    String resultExpected;

    @SerializedName("exdatecon")
    String examDateConfirmed;

    public String getAdmitCardExpected() {
        return admitCardExpected;
    }

    public void setAdmitCardExpected(String admitCardExpected) {
        this.admitCardExpected = admitCardExpected;
    }

    @SerializedName("exdatexpdt")
    String ExamExpected;

    public String getExamExpected() {
        return ExamExpected;
    }

    public void setExamExpected(String examExpected) {
        ExamExpected = examExpected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getRegistrationStartConfirmed() {
        return registrationStartConfirmed;
    }

    public void setRegistrationStartConfirmed(String registrationStartConfirmed) {
        this.registrationStartConfirmed = registrationStartConfirmed;
    }

    public String getRegistrationEndConfirmed() {
        return registrationEndConfirmed;
    }

    public void setRegistrationEndConfirmed(String registrationEndConfirmed) {
        this.registrationEndConfirmed = registrationEndConfirmed;
    }

    public String getAdmitCardConfirmed() {
        return admitCardConfirmed;
    }

    public void setAdmitCardConfirmed(String admitCardConfirmed) {
        this.admitCardConfirmed = admitCardConfirmed;
    }

    public String getExamDateConfirmed() {
        return examDateConfirmed;
    }

    public void setExamDateConfirmed(String examDateConfirmed) {
        this.examDateConfirmed = examDateConfirmed;
    }

    public String getResultConfirmed() {
        return resultConfirmed;
    }

    public void setResultConfirmed(String resultConfirmed) {
        this.resultConfirmed = resultConfirmed;
    }

    public String getRegistrationStartExpected() {
        return registrationStartExpected;
    }

    public void setRegistrationStartExpected(String registrationStartExpected) {
        this.registrationStartExpected = registrationStartExpected;
    }

    public String getRegistrationEndExpected() {
        return registrationEndExpected;
    }

    public void setRegistrationEndExpected(String registrationEndExpected) {
        this.registrationEndExpected = registrationEndExpected;
    }

    public String getResultExpected() {
        return resultExpected;
    }

    public void setResultExpected(String resultExpected) {
        this.resultExpected = resultExpected;
    }

    public class ExamDatesResponse {
        @SerializedName("status")
        String status;
        @SerializedName("exams_dates_all")
        List<ExamDate> examDates;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<ExamDate> getExamDates() {
            return examDates;
        }

        public void setExamDates(List<ExamDate> examDates) {
            this.examDates = examDates;
        }
    }
}
