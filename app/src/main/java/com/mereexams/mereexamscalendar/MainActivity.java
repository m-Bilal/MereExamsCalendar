package com.mereexams.mereexamscalendar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.mereexams.mereexamscalendar.Helpers.FileIO;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button buttonDefault, buttonCaldroid, buttonAgendaCalendarView;

    public static HashMap<String, String> vars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileIO fileIO = new FileIO(this);
        vars = fileIO.getVars();

        buttonDefault = (Button) findViewById(R.id.button_default_calendar);
        buttonCaldroid = (Button) findViewById(R.id.button_caldroid);
        buttonAgendaCalendarView = (Button) findViewById(R.id.button_agenda_calendar_view);

        addActionListeners();
    }

    void addActionListeners() {
        buttonDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DefaultCalendarActivity.class);
                startActivity(intent);
            }
        });

        buttonCaldroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CaldroidActivity.class);
                startActivity(intent);
            }
        });

        buttonAgendaCalendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AgendaCalendarViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
