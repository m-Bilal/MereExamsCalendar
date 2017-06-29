package com.mereexams.mereexamscalendar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = (CalendarView) findViewById(R.id.calendar);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Log.d("Calendar", "Date changed");
                Log.d("Year", year + "");
                Log.d("Month", month + "");
                Log.d("Day", dayOfMonth + "");
                Intent intent = new Intent(getApplicationContext(), CaldroidActivity.class);
                startActivity(intent);
            }
        });
    }
}
