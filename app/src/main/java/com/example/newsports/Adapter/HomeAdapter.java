package com.example.newsports.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsports.R;
import com.example.newsports.Models.HomeCategory;

import java.util.List;

//Explore product
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
   Context context;
   List<HomeCategory> categoryList;

    public HomeAdapter(Context context, List<HomeCategory> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cat_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       if(context!=null){
           Glide.with(context).load(categoryList.get(position).getImg_url()).into(holder.catImg);
           holder.name.setText(categoryList.get(position).getName());
       }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView catImg;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catImg=itemView.findViewById(R.id.home_cat_img);
            name=itemView.findViewById(R.id.home_cat_name);
        }
    }
}
