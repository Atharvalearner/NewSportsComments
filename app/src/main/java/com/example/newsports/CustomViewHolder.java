package com.example.newsports;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView source;
    public ImageView image_headline;
    public CardView cardView;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.text_title);
        source = itemView.findViewById(R.id.text_source);
        image_headline = itemView.findViewById(R.id.img_headline);
        cardView = itemView.findViewById(R.id.main_container);
    }
}
