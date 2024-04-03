package com.example.newsports.PostData;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsports.R;
// Code in your VideoActivity to receive and play the video
public class VideoActivity extends AppCompatActivity {
    private VideoView videoView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoView);

        // Retrieve video URL from Intent
        String videoUrl = getIntent().getStringExtra("VIDEO_URL");

        // Play video
        if (videoUrl != null) {
            Uri videoUri = Uri.parse(videoUrl);
            videoView.setVideoURI(videoUri);
            videoView.start();
        }
    }
}
