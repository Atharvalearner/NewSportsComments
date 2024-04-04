package com.example.newsports;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsports.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {
    private NewsHeadlines headlines;
    private TextView txt_title, txt_author, txt_time, txt_detail, txt_content;
    private ImageView img_news;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_details, container, false);

        txt_title = view.findViewById(R.id.text_detail_title);
        txt_author = view.findViewById(R.id.text_detail_author);
        txt_time = view.findViewById(R.id.text_detail_time);
        txt_detail = view.findViewById(R.id.text_detail_detail);
        txt_content = view.findViewById(R.id.text_detail_content);
        img_news = view.findViewById(R.id.img_detail_news);

        headlines = (NewsHeadlines) requireActivity().getIntent().getSerializableExtra("data");

        txt_title.setText(headlines.getTitle());
        txt_author.setText(headlines.getAuthor());
        txt_time.setText(headlines.getPublishedAt());
        txt_detail.setText(headlines.getDescription());
        txt_content.setText(headlines.getContent());

        // Load image using Picasso library
        if (headlines.getUriToImage() != null && !headlines.getUriToImage().isEmpty()) {
            Picasso.get().load(headlines.getUriToImage()).into(img_news);
        }

        return view;
    }
}