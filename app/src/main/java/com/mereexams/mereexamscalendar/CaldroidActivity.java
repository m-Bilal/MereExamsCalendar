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
        Bundle args = new Bundle();
        args.putInt(CaldroidFragment.MONTH, 6);
        args.putInt(CaldroidFragment.YEAR, 2017);
        caldroidFragment.setArguments(args);

        Date today = new Date(2017 - 1900, 5, 29);
        Date tomorrow = new Date(2017 - 1900, 5, 30);
        Log.d("Today", today.toString());

        caldroidFragment.setBackgroundDrawableForDate(getResources().getDrawable(R.drawable.circle), tomorrow);
        caldroidFragment.refreshView();

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.framelayout_fragment_container, caldroidFragment);
        t.commit();
    }
}
