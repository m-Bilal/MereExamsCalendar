package com.mereexams.mereexamscalendar.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mereexams.mereexamscalendar.CaldroidActivity;
import com.mereexams.mereexamscalendar.Models.MyCalendarEvent;
import com.mereexams.mereexamscalendar.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {

    private static final String TAG = "EventsFragment";
    TextView hello;
    RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;

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
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_events_fragment);

        hello.setText(CaldroidActivity.currentDateOnCalendar.toString());

        // ReyclerView
        adapter = new MyRecyclerViewAdapter(CaldroidActivity.allEvents.get(CaldroidActivity.currentDateOnCalendar));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void update() {
        hello.setText(CaldroidActivity.currentDateOnCalendar.toString());

        // ReyclerView
        adapter = new MyRecyclerViewAdapter(CaldroidActivity.allEvents.get(CaldroidActivity.currentDateOnCalendar));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    // Recycler View Adapter
    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

        List<MyCalendarEvent> exams;

        public MyRecyclerViewAdapter(List<MyCalendarEvent> examDates) {
            exams = examDates;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_calendar_events_layout, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.title.setText(exams.get(position).getTitle());
            holder.type.setText(exams.get(position).getEventType());
            holder.dateType.setText(exams.get(position).getDateType());
        }

        @Override
        public int getItemCount() {
            if (exams == null) return 0;
            else return exams.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title, type, dateType;

            public MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.textview_recyclerview_calendar_events_title);
                type = (TextView) view.findViewById(R.id.textview_recyclerview_calendar_events_type);
                dateType = (TextView) view.findViewById(R.id.textview_recyclerview_calendar_events_date_type);
            }
        }
    }
}
