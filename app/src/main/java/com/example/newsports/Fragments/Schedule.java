package com.example.newsports.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.newsports.Adapter.EventAdapter;
import com.example.newsports.R;
import com.example.newsports.Models.EventModel;

import java.util.ArrayList;
import java.util.List;


public class Schedule extends Fragment {
    RecyclerView recycle_view;
    EventAdapter adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_schedule, container, false);
        recycle_view=view.findViewById(R.id.recycle_view);
        recycle_view.setLayoutManager(new LinearLayoutManager(getContext()));

        List<EventModel> eventModelList=getEventList();
        adapter=new EventAdapter(getContext(),eventModelList);
        recycle_view.setAdapter(adapter);

        return view;
    }

    private List<EventModel> getEventList() {
        List<EventModel> eventList = new ArrayList<>();
        // Add your event data here
        eventList.add(new EventModel(1,R.drawable.cricket1,"Cricket","April 10, 2024","Gandhi Cricket Ground"));
        eventList.add(new EventModel(2,R.drawable.volleyball,"Volleyball","April 20, 2024","Community Center Gymnasium"));
        eventList.add(new EventModel(3,R.drawable.badmintoon,"Badminton","April 15, 2024","Sunshine Badminton Club"));
        eventList.add(new EventModel(4,R.drawable.football,"Football","April 22, 2024","City Stadium"));
        eventList.add(new EventModel(5,R.drawable.tennis,"Tennis","May 2, 2024","Central Tennis Club"));
        eventList.add(new EventModel(6,R.drawable.swimming,"Swimming","May 5, 2024"," City Aquatic Center"));
        eventList.add(new EventModel(7,R.drawable.cycling,"Cycling Race","April 9, 2024","Greenway Park"));
        eventList.add(new EventModel(8,R.drawable.road_race,"Road Race","April 12, 2024","Downtown Plaza"));
        // Add more events as needed
        return eventList;
    }
}