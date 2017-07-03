package com.mereexams.mereexamscalendar;

import android.icu.util.Calendar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.roomorama.caldroid.CaldroidFragment;

import java.util.Date;

public class CaldroidActivity extends AppCompatActivity {

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caldroid);

        frameLayout = (FrameLayout) findViewById(R.id.framelayout_fragment_container);

        addCalendar();
    }

    void addCalendar() {
        CaldroidFragment caldroidFragment = new CaldroidFragment();

        // Current Date
        java.util.Calendar today = java.util.Calendar.getInstance();

        Bundle args = new Bundle();
        // Set the month
        args.putInt(CaldroidFragment.MONTH, today.get(java.util.Calendar.MONTH) + 1);
        // Set the year
        args.putInt(CaldroidFragment.YEAR, today.get(java.util.Calendar.YEAR));
        caldroidFragment.setArguments(args);

        Date tomorrow = new Date(2017 - 1900, 5, 30);

        caldroidFragment.setBackgroundDrawableForDate(getResources().getDrawable(R.drawable.circle), tomorrow);
        caldroidFragment.refreshView();

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.framelayout_fragment_container, caldroidFragment);
        t.commit();
    }
}
