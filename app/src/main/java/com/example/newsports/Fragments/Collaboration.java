package com.example.newsports.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsports.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Collaboration extends Fragment {

    public Collaboration() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_collaboration, container, false);

        // Find the contact number TextView
        TextView contactNumberTextView = rootView.findViewById(R.id.text_contact_number);

        // Set click listener for the contact number TextView
        contactNumberTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open WhatsApp with the specified contact number
                String contactNumber = "9834154073"; // Replace with your actual contact number
                openWhatsApp(contactNumber);
            }
        });

        return rootView;
    }

    private void openWhatsApp(String contactNumber) {
        // Create intent to open WhatsApp with the specified contact number
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=" + contactNumber));
        startActivity(intent);
    }
}
