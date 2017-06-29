package com.mereexams.mereexamscalendar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class MainActivity extends AppCompatActivity {

    Button buttonDefault, buttonCaldroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonDefault = (Button) findViewById(R.id.button_default_calendar);
        buttonCaldroid = (Button) findViewById(R.id.button_caldroid);

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
    }
}
