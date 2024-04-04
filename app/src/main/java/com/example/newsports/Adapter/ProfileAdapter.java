package com.example.newsports.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newsports.R;
import com.example.newsports.Models.UserProfile_model;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    private List<UserProfile_model> userProfileList;

    public ProfileAdapter(List<UserProfile_model> userProfileList) {
        this.userProfileList = userProfileList;
    }

    public void setData(List<UserProfile_model> newData) {
        userProfileList = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_profile, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        UserProfile_model userProfile = userProfileList.get(position);
        // Bind userProfile data to UI elements in holder
    }

    @Override
    public int getItemCount() {
        return userProfileList.size();
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {
        // Define UI elements here
        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize UI elements
        }
    }
}