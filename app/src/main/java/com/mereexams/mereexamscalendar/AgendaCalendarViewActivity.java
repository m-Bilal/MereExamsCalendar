package com.mereexams.mereexamscalendar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;
import com.mereexams.mereexamscalendar.Helpers.ApiClient;
import com.mereexams.mereexamscalendar.Helpers.ApiInterface;
import com.mereexams.mereexamscalendar.Models.ExamDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaCalendarViewActivity extends AppCompatActivity {

    AgendaCalendarView calendarView;
    ProgressDialog dialog;
    public final static String TAG = "AgendaCalendarView";

    List<CalendarEvent> calendarEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_calendar_view);

        calendarView = (AgendaCalendarView) findViewById(R.id.agenda_calendar_view);

        //List<CalendarEvent> eventList = new ArrayList<>();
        //mockList(eventList);

        requestInfoFromServer();
    }

    void addCalendarEvents() {
        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        minDate.add(Calendar.MONTH, -2);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 1);

        Log.i("Events size", calendarEvents.size() + "");

        calendarView.init(calendarEvents, minDate, maxDate, Locale.getDefault(), new CalendarPickerController() {
            @Override
            public void onDaySelected(DayItem dayItem) {
                Log.i("Day", dayItem.toString());
            }

            @Override
            public void onEventSelected(CalendarEvent event) {

            }

            @Override
            public void onScrollToDate(Calendar calendar) {

            }
        });
    }

    private void createEvents(List<ExamDate> examDates) {
        calendarEvents = new LinkedList<>();
        for (ExamDate date : examDates) {
            Calendar startTime = Calendar.getInstance();
            Calendar endTime = Calendar.getInstance();

            if(date.getRegistationStartConfirmed().equals("") &&
                    date.getRegistrationStartExpected().equals("")){
                continue;
            } else if(date.getRegistrationEndConfirmed().equals("") &&
                    date.getRegistrationEndExpected().equals("")){
                continue;
            } else {
                if (date.getRegistationStartConfirmed().equals("")) {
                    Log.d(TAG, "ID: " + date.getId());
                    Log.d(TAG, "date start exp: " + date.getRegistrationStartExpected());
                    String[] dateFormat = date.getRegistrationStartExpected().split("/");
                    startTime.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateFormat[0].trim()));
                    startTime.set(Calendar.MONTH, Integer.parseInt(dateFormat[1].trim()) - 1);
                    startTime.set(Calendar.YEAR, 2017);
                } else {
                    Log.d(TAG, "ID: " + date.getId());
                    Log.d(TAG, "date start con: " + date.getRegistationStartConfirmed());
                    String[] dateFormat = date.getRegistationStartConfirmed().split("/");
                    startTime.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateFormat[0].trim()));
                    startTime.set(Calendar.MONTH, Integer.parseInt(dateFormat[1].trim()) - 1);
                    startTime.set(Calendar.YEAR, 2017);
                }
                if (date.getRegistrationEndConfirmed().equals("")) {
                    Log.d(TAG, "ID: " + date.getId());
                    Log.d(TAG, "date end exp: " + date.getRegistrationEndExpected());
                    String[] dateFormat = date.getRegistrationEndExpected().split("/");
                    endTime.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateFormat[0].trim()));
                    endTime.set(Calendar.MONTH, Integer.parseInt(dateFormat[1].trim()) - 1);
                    endTime.set(Calendar.YEAR, 2017);
                } else {
                    Log.d(TAG, "ID: " + date.getId());
                    Log.d(TAG, "date end con: " + date.getRegistrationEndConfirmed());
                    String[] dateFormat = date.getRegistrationEndConfirmed().split("/");
                    endTime.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateFormat[0].trim()));
                    endTime.set(Calendar.MONTH, Integer.parseInt(dateFormat[1].trim()) - 1);
                    endTime.set(Calendar.YEAR, 2017);
                }
                BaseCalendarEvent event1 = new BaseCalendarEvent(date.getName(), "Exam", "MereExams",
                        ContextCompat.getColor(this, R.color.colorAccent), startTime, endTime, true);
                calendarEvents.add(event1);
            }
        }
        addCalendarEvents();
    }

    private void mockList(List<CalendarEvent> eventList) {
        Calendar startTime1 = Calendar.getInstance();
        Calendar endTime1 = Calendar.getInstance();
        endTime1.add(Calendar.MONTH, 1);
        BaseCalendarEvent event1 = new BaseCalendarEvent("Thibault travels in Iceland", "A wonderful journey!", "Iceland",
                ContextCompat.getColor(this, R.color.colorPrimary), startTime1, endTime1, true);
        eventList.add(event1);

        Calendar startTime2 = Calendar.getInstance();
        startTime2.add(Calendar.DAY_OF_YEAR, 1);
        Calendar endTime2 = Calendar.getInstance();
        endTime2.add(Calendar.DAY_OF_YEAR, 3);
        BaseCalendarEvent event2 = new BaseCalendarEvent("Visit to Dalvík", "A beautiful small town", "Dalvík",
                ContextCompat.getColor(this, R.color.colorAccent), startTime2, endTime2, true);
        eventList.add(event2);

        Calendar startTime3 = Calendar.getInstance();
        Calendar endTime3 = Calendar.getInstance();
        Log.i("Month", endTime3.get(Calendar.MONTH) + "");
        BaseCalendarEvent event3 = new BaseCalendarEvent("My Own Event", "My description", "Temple of Thought",
                ContextCompat.getColor(this, R.color.event_color), startTime3, endTime3, true);
        eventList.add(event3);
    }

    void requestInfoFromServer() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Getting dates");
        dialog.show();

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ExamDate.ExamDatesResponse> call = apiService.getExamDates(MainActivity.vars.get("api_get_all_exam_dates"),
                MainActivity.vars.get("token"));
        call.enqueue(new Callback<ExamDate.ExamDatesResponse>() {
            @Override
            public void onResponse(Call<ExamDate.ExamDatesResponse> call, Response<ExamDate.ExamDatesResponse> response) {
                List<ExamDate> exams = response.body().getExamDates();
                createEvents(exams);
                Log.d(TAG, "Number of Exams: " + exams.size());
                dialog.hide();
            }

            @Override
            public void onFailure(Call<ExamDate.ExamDatesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                dialog.hide();
            }
        });
    }

}
