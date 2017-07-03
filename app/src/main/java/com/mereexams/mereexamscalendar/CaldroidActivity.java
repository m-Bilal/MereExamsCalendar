package com.mereexams.mereexamscalendar;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.roomorama.caldroid.CaldroidFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaldroidActivity extends AppCompatActivity {

    private final static String TAG = "CaldroidActivity";
    private final short[] DAYS_IN_MONTHS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final short[] DAYS_IN_MONTH_LEAP_YEAR = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    FrameLayout frameLayout;
    List<Date> eventDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caldroid);

        eventDates = new ArrayList<>();

        frameLayout = (FrameLayout) findViewById(R.id.framelayout_fragment_container);

        addCalendar();

        datesInRange();
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

    void datesInRange() {
        // TODO: Modify the method signature to accept startDate and endDate as parameters
        // Dummy start and end dates
        String[] startDateStr = "15/05/2015".split("/");
        String[] endDateStr = "14/07/2017".split("/");

        // Starting date
        short startDay = Short.parseShort(startDateStr[0].trim());
        short startMonth = Short.parseShort(startDateStr[1].trim());
        --startMonth;
        int startYear = Integer.parseInt(startDateStr[2].trim());

        // End Date
        short endDay = Short.parseShort(endDateStr[0].trim());
        short endMonth = Short.parseShort(endDateStr[1].trim());
        --endMonth;
        int endYear = Integer.parseInt(endDateStr[2].trim());

        // Iterating over years
        for (int currentYear = startYear; currentYear <= endYear; currentYear++) {
            short[] noOfDays;
            if (currentYear % 4 == 0) noOfDays = DAYS_IN_MONTH_LEAP_YEAR;
            else noOfDays = DAYS_IN_MONTHS;

            // Iterating over months
            // The month from which the counting of dates should start and stop
            short firstMonth, lastMonth;
            // if current year is the starting year, start from the month specified in the date,
            // else start from the first(0) month
            if (currentYear == startYear) firstMonth = startMonth;
            else firstMonth = 0;
            // if current year is the year of deadline, count till the month specified in the date,
            // else count till the last(11) month
            if (currentYear == endYear) lastMonth = endMonth;
            else lastMonth = 11;
            short currentMonth = firstMonth;
            while (currentMonth <= lastMonth) {

                // Iterating over days
                // The day from which the counting should begin to the day at which it should end
                short firstDay, lastDay;
                // If current month is the starting month of the date and current year is the starting year,
                // count from the day specified in the date
                // else count from the first day
                if (currentMonth == startMonth && currentYear == startYear) firstDay = startDay;
                else firstDay = 1;
                // If current month and year is the month and year specified in the deadline,
                // count till the day specified in the deadline
                // else count till the last day
                if (currentMonth == endMonth && currentYear == endYear) lastDay = endDay;
                else lastDay = noOfDays[currentMonth];
                short currentDay = firstDay;
                while (currentDay <= lastDay) {
                    Date currentDate = new Date(currentYear - 1900, currentMonth, currentDay++);
                    Log.i(TAG, "Added date: " + currentDate.toString());
                    eventDates.add(currentDate);
                }
                currentMonth++;
            }
        }
    }
}
