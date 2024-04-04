package com.example.newsports.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsports.Adapter.Looking;
import com.example.newsports.R;
import com.example.newsports.Models.LookingforModel;

import java.util.ArrayList;
import java.util.List;


public class LookingFor extends Fragment {
    RecyclerView recycle_view_looking;
    Looking looking_adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_looking_for, container, false);

        recycle_view_looking=view.findViewById(R.id.recycle_view_looking);
        recycle_view_looking.setLayoutManager(new LinearLayoutManager(getContext()));

        List<LookingforModel> lookingModelList=getlookingList();
        looking_adapter=new Looking(getContext(),lookingModelList);
        recycle_view_looking.setAdapter(looking_adapter);

        return view;
    }

    private List<LookingforModel> getlookingList() {
        List<LookingforModel> lookingList = new ArrayList<>();
        // Add your event data here
        lookingList.add(new LookingforModel(1, R.drawable.cricket1,"Cricket","Nashik","9834154073", R.drawable.whatsapp));
        lookingList.add(new LookingforModel(2, R.drawable.volleyball,"We are seeking a dedicated and experienced volleyball team coach to lead and inspire our team to success. The ideal candidate should have a strong background in volleyball, with coaching experience at the high school or college level","Nashik","9834154073", R.drawable.whatsapp));
        lookingList.add(new LookingforModel(3, R.drawable.badmintoon,"Badminton partner required for mens double","Nashik","9834154073", R.drawable.whatsapp));
        lookingList.add(new LookingforModel(4, R.drawable.football,"Looking for a skilled and dedicated football defender to strengthen our team's defense and contribute to our success on the field.","Mumbai","9834154073", R.drawable.whatsapp));
        lookingList.add(new LookingforModel(5, R.drawable.tennis,"Seeking a male partner for mixed doubles tennis to compete in upcoming tournaments and enhance our team's performance.","Mumbai","9834154073", R.drawable.whatsapp));
        lookingList.add(new LookingforModel(6, R.drawable.swimming,"Seeking a knowledgeable and enthusiastic swimming coach to train and motivate our team to achieve their full potential in the pool.","Pune","9834154073", R.drawable.whatsapp));

        // Add more events as needed
        return lookingList;
    }
}