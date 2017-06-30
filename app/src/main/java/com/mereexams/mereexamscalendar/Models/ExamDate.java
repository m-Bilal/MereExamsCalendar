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
    String registationStartConfirmed;
    @SerializedName("regendcon")
    String registrationEndConfirmed;
    @SerializedName("admitcardcon")
    String admitCardStartConfirmed;
    @SerializedName("exdatecon")
    String admitCardEndConfirmed;
    @SerializedName("rescon")
    String resultConfirmed;
    @SerializedName("regstartexpdt")
    String registrationStartExpected;
    @SerializedName("regendexpdt")
    String registrationEndExpected;
    @SerializedName("resexpdt")
    String resultExpected;
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

    public String getRegistationStartConfirmed() {
        return registationStartConfirmed;
    }

    public void setRegistationStartConfirmed(String registationStartConfirmed) {
        this.registationStartConfirmed = registationStartConfirmed;
    }

    public String getRegistrationEndConfirmed() {
        return registrationEndConfirmed;
    }

    public void setRegistrationEndConfirmed(String registrationEndConfirmed) {
        this.registrationEndConfirmed = registrationEndConfirmed;
    }

    public String getAdmitCardStartConfirmed() {
        return admitCardStartConfirmed;
    }

    public void setAdmitCardStartConfirmed(String admitCardStartConfirmed) {
        this.admitCardStartConfirmed = admitCardStartConfirmed;
    }

    public String getAdmitCardEndConfirmed() {
        return admitCardEndConfirmed;
    }

    public void setAdmitCardEndConfirmed(String admitCardEndConfirmed) {
        this.admitCardEndConfirmed = admitCardEndConfirmed;
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
