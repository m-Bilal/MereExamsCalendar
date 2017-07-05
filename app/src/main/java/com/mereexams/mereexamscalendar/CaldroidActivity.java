package com.mereexams.mereexamscalendar;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mereexams.mereexamscalendar.Fragments.EventsFragment;
import com.mereexams.mereexamscalendar.Helpers.ApiClient;
import com.mereexams.mereexamscalendar.Helpers.ApiInterface;
import com.mereexams.mereexamscalendar.Models.ExamDate;
import com.mereexams.mereexamscalendar.Models.MyCalendarEvent;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaldroidActivity extends AppCompatActivity {

    private final static String TAG = "CaldroidActivity";
    private final short[] DAYS_IN_MONTHS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final short[] DAYS_IN_MONTH_LEAP_YEAR = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    FrameLayout frameLayout;
    RecyclerView recyclerViewCalendarEvents;
    ProgressDialog dialog;
    CaldroidFragment caldroidFragment;
    TextView textViewSelectedDate;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;

    // A list that has the dates of all the allEvents, no filter used
    List<Date> allDates;
    // List that has all the parsed objects returned by the server
    List<ExamDate> restDateResponse;
    // HashMap to map allEvents to their dates,
    // the list(second arg) contains the allEvents associated with the date
    public static HashMap<Date, List<MyCalendarEvent>> allEvents;

    Date lateDateSelected;

    // The current date on the calendar, not necessarily today's date
    public static Date currentDateOnCalendar;
    public static final int MAX_PAGES_COUNT = 1000;

    // the position of the current page in the view pager
    private int currentPagePosition;

    // Calendar variable to move forward and backward between dates
    private Calendar currentCalender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caldroid);

        allDates = new ArrayList<>();
        restDateResponse = new ArrayList<>();
        allEvents = new HashMap<>();
        lateDateSelected = null;
        currentCalender = Calendar.getInstance();
        int year = currentCalender.get(Calendar.YEAR) - 1900;
        int month = currentCalender.get(Calendar.MONTH);
        int day = currentCalender.get(Calendar.DAY_OF_MONTH);
        currentDateOnCalendar = new Date(year, month, day);

        frameLayout = (FrameLayout) findViewById(R.id.framelayout_fragment_container);
        textViewSelectedDate = (TextView) findViewById(R.id.textview_selected_date);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(MAX_PAGES_COUNT / 2);
        caldroidFragment = new CaldroidFragment();

        currentPagePosition = MAX_PAGES_COUNT / 2;

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                caldroidFragment.setBackgroundDrawableForDate(getResources().getDrawable(R.drawable.circle), currentDateOnCalendar);
                Log.i(TAG, "on Page selected: " + position);
                if(position < currentPagePosition) {
                    currentCalender.set(Calendar.DAY_OF_YEAR,currentCalender.get(Calendar.DAY_OF_YEAR) - 1);
                    int year = currentCalender.get(Calendar.YEAR) - 1900;
                    int month = currentCalender.get(Calendar.MONTH);
                    int day = currentCalender.get(Calendar.DAY_OF_MONTH);
                    currentDateOnCalendar = new Date(year, month, day);
                    Log.i(TAG, "mycalendar, current Time:" + currentCalender.getTime().toString());
                    Log.i(TAG, "mycalendar, Day of year: " + currentCalender.get(Calendar.DAY_OF_YEAR));
                }
                else {
                    currentCalender.set(Calendar.DAY_OF_YEAR,currentCalender.get(Calendar.DAY_OF_YEAR) + 1);
                    int year = currentCalender.get(Calendar.YEAR) - 1900;
                    int month = currentCalender.get(Calendar.MONTH);
                    int day = currentCalender.get(Calendar.DAY_OF_MONTH);
                    currentDateOnCalendar = new Date(year, month, day);
                    Log.i(TAG, "mycalendar, current Time:" + currentCalender.getTime().toString());
                    Log.i(TAG, "mycalendar, Day of year: " + currentCalender.get(Calendar.DAY_OF_YEAR));
                }
                currentPagePosition = position;

                caldroidFragment.setBackgroundDrawableForDate(getResources().getDrawable(R.drawable.date_selected), currentDateOnCalendar);
                caldroidFragment.refreshView();
                lateDateSelected = currentDateOnCalendar;
                textViewSelectedDate.setText(currentDateOnCalendar.toString());

                // Getting the current fragment
                FragmentStatePagerAdapter adapter = (FragmentStatePagerAdapter) viewPager.getAdapter();
                EventsFragment fragment = (EventsFragment) adapter.instantiateItem(viewPager, currentPagePosition);
                fragment.update();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        requestInfoFromServer();
        addCalendar();
    }

    // Add the calendar fragment to the Activity with today's date as the arg
    void addCalendar() {
        // Current Date
        java.util.Calendar today = java.util.Calendar.getInstance();

        Bundle args = new Bundle();
        // Set the month
        args.putInt(CaldroidFragment.MONTH, today.get(java.util.Calendar.MONTH) + 1);
        // Set the year
        args.putInt(CaldroidFragment.YEAR, today.get(java.util.Calendar.YEAR));
        args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, false);
        caldroidFragment.setArguments(args);

        caldroidFragment.setTextColorForDate(R.color.colorAccent, new Date());
        caldroidFragment.setBackgroundDrawableForDate(getResources().getDrawable(R.drawable.date_selected), new Date());
        caldroidFragment.refreshView();

        caldroidFragment.setCaldroidListener(new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                textViewSelectedDate.setText(date.toString());
                if(lateDateSelected != null){
                    caldroidFragment.setBackgroundDrawableForDate(getResources().getDrawable(R.drawable.circle), lateDateSelected);
                }
                caldroidFragment.setBackgroundDrawableForDate(getResources().getDrawable(R.drawable.date_selected), date);
                lateDateSelected = date;
                currentDateOnCalendar = date;
                currentCalender.setTime(date);
                caldroidFragment.refreshView();

                // Getting the current fragment
                FragmentStatePagerAdapter adapter = (FragmentStatePagerAdapter) viewPager.getAdapter();
                EventsFragment fragment = (EventsFragment) adapter.instantiateItem(viewPager, currentPagePosition);
                fragment.update();
            }
        });

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.framelayout_fragment_container, caldroidFragment);
        t.commit();
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
                restDateResponse.clear();
                restDateResponse = response.body().getExamDates();
                extractEvents();
                Log.d(TAG, "Number of Exams: " + restDateResponse.size());
                dialog.hide();
            }

            @Override
            public void onFailure(Call<ExamDate.ExamDatesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                Toast.makeText(getApplicationContext(),"Not connected", Toast.LENGTH_SHORT).show();
                dialog.hide();
            }
        });
    }

    void addEvent(List<Date> dates, ExamDate event, short eventType, short dateType) {
        MyCalendarEvent myEvent = new MyCalendarEvent();
        myEvent.setTitle(event.getName());
        myEvent.setEventType(eventType);
        myEvent.setDateType(dateType);
        for (Date d : dates) {
            if (allEvents.get(d) == null) {
                List<MyCalendarEvent> list = new ArrayList<>();
                list.add(myEvent);
                allEvents.put(d, list);
            } else {
                List<MyCalendarEvent> list = allEvents.get(d);
                list.add(myEvent);
                allEvents.put(d, list);
            }
        }
    }

    void extractEvents() {
        for (ExamDate i : restDateResponse) {
            List<Date> dates;
            // for registration date
            if (i.getRegistrationStartConfirmed().equals("") || i.getRegistrationEndConfirmed().equals("")) {
                if (i.getRegistrationStartExpected().equals("") || i.getRegistrationEndExpected().equals("")) {
                    continue;
                } else {
                    dates = datesInRange(i.getRegistrationStartExpected(), i.getRegistrationEndExpected());
                    addEvent(dates, i, MyCalendarEvent.EVENT_TYPE_REGISTRATION, MyCalendarEvent.DATE_TYPE_EXPECTED);
                }
            } else {
                dates = datesInRange(i.getRegistrationStartConfirmed(), i.getRegistrationEndConfirmed());
                addEvent(dates, i, MyCalendarEvent.EVENT_TYPE_REGISTRATION, MyCalendarEvent.DATE_TYPE_CONFIRMED);
            }

            // for admit card date
            if (i.getAdmitCardConfirmed().equals("")) {
                if (i.getAdmitCardExpected().equals("")) {
                    continue;
                } else {
                    dates = datesInRange(i.getAdmitCardExpected(), i.getAdmitCardExpected());
                    addEvent(dates, i, MyCalendarEvent.EVENT_TYPE_ADMIT_CARD, MyCalendarEvent.DATE_TYPE_EXPECTED);
                }
            } else {
                dates = datesInRange(i.getAdmitCardConfirmed(), i.getAdmitCardConfirmed());
                addEvent(dates, i, MyCalendarEvent.EVENT_TYPE_ADMIT_CARD, MyCalendarEvent.DATE_TYPE_CONFIRMED);
            }

            // for exam date
            if (i.getExamDateConfirmed().equals("")) {
                if (i.getExamExpected().equals("")) {
                    continue;
                } else {
                    dates = datesInRange(i.getExamExpected(), i.getExamExpected());
                    addEvent(dates, i, MyCalendarEvent.EVENT_TYPE_EXAM, MyCalendarEvent.DATE_TYPE_EXPECTED);
                }
            } else {
                dates = datesInRange(i.getExamDateConfirmed(), i.getExamDateConfirmed());
                addEvent(dates, i, MyCalendarEvent.EVENT_TYPE_EXAM, MyCalendarEvent.DATE_TYPE_CONFIRMED);
            }

            // for result date
            if (i.getResultConfirmed().equals("")) {
                if (i.getResultExpected().equals("")) {
                    continue;
                } else {
                    dates = datesInRange(i.getResultExpected(), i.getResultExpected());
                    addEvent(dates, i, MyCalendarEvent.EVENT_TYPE_RESULT, MyCalendarEvent.DATE_TYPE_EXPECTED);
                }
            } else {
                dates = datesInRange(i.getResultConfirmed(), i.getResultConfirmed());
                addEvent(dates, i, MyCalendarEvent.EVENT_TYPE_RESULT, MyCalendarEvent.DATE_TYPE_CONFIRMED);
            }
        }
        caldroidFragment.setBackgroundDrawableForDates(createDrawables());
        caldroidFragment.refreshView();

        // Getting the current fragment
        FragmentStatePagerAdapter adapter = (FragmentStatePagerAdapter) viewPager.getAdapter();
        EventsFragment fragment = (EventsFragment) adapter.instantiateItem(viewPager, currentPagePosition);
        fragment.update();
    }

    Map<Date, Drawable> createDrawables() {
        Map<Date, Drawable> dateDrawableMap = new HashMap<>();
        for(Date date : allDates) {
            dateDrawableMap.put(date, getResources().getDrawable(R.drawable.circle));
        }
        return dateDrawableMap;
    }

    List<Date> datesInRange(String startDate, String endDate) {
        String[] startDateStr = startDate.split("/");
        String[] endDateStr = endDate.split("/");

        List<Date> datesOfEvent = new ArrayList<>();

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
                    allDates.add(currentDate);
                    datesOfEvent.add(currentDate);
                }
                currentMonth++;
            }
        }
        return datesOfEvent;
    }

    // Pager Adapter
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            EventsFragment fragment = new EventsFragment();
            return fragment;
        }

        @Override
        public int getCount() {
            return MAX_PAGES_COUNT;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}
