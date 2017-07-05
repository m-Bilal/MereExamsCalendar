package com.mereexams.mereexamscalendar.Fragments;


import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mereexams.mereexamscalendar.CaldroidActivity;
import com.mereexams.mereexamscalendar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {

    TextView hello;

    private static final String TAG = "EventsFragment";

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        // Reference views
        hello = (TextView) view.findViewById(R.id.text_hello);
        hello.setText(CaldroidActivity.currentDateOnCalendar.toString());
        return view;
    }

    public void update() {
        hello.setText(CaldroidActivity.currentDateOnCalendar.toString());
    }
}
