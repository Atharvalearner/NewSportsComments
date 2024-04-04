package com.example.newsports;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SportsDetails extends AppCompatActivity {
    ImageView image_event;
    TextView text_location,text_date,text_sport_name;
    TextView text_organizers,text_winners,text_description;
    TextView number,normal_text;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_details);
        image_event=findViewById(R.id.image_event);
        text_sport_name=findViewById(R.id.text_sport_name);
        text_location=findViewById(R.id.text_location);
        text_date=findViewById(R.id.text_date);
        number=findViewById(R.id.number);
        normal_text=findViewById(R.id.normal_text);

        text_sport_name.setText(getIntent().getExtras().getString("Name"));
        text_date.setText(getIntent().getExtras().getString("Date"));
        text_location.setText(getIntent().getExtras().getString("Location"));

        int my_image_event=getIntent().getIntExtra("Img",0);
        image_event.setImageResource(my_image_event);


        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber();
            }
        });
    }

    public void onPhoneNumberClicked(View view) {
        dialPhoneNumber();
    }


    private void dialPhoneNumber() {
        // Get the phone number from the TextView
        TextView number = findViewById(R.id.number);
        String phoneNumber = number.getText().toString();

        // Create an intent to open the dial pad with the phone number
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        // Check if there's an activity to handle the intent before starting it
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}