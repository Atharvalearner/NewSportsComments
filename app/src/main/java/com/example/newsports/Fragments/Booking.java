package com.example.newsports.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.newsports.R;
import com.example.newsports.WebView;

public class Booking extends Fragment {
    ImageView imageView1,imageView2,imageView3,imageView4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_booking, container, false);

        imageView1 = view.findViewById(R.id.imageView1);
        imageView2 = view.findViewById(R.id.imageView2);
        imageView3 = view.findViewById(R.id.imageView3);
        imageView4 = view.findViewById(R.id.imageView4);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url1="https://www.playspots.in";
                openWeb(url1);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url2="https://www.sportobuddy.com/sports-venues";
                openWeb(url2);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url3="https://www.gwsportsapp.in";
                openWeb(url3);
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url4="https://www.sporloc.com";
                openWeb(url4);
            }
        });
        return view;
    }
    private void openWeb(String url) {
        Intent intent = new Intent(getActivity(), WebView.class);
        intent.putExtra("webUrl", url);
        startActivity(intent);
    }
}