package com.example.newsports.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsports.R;
import com.example.newsports.SportsDetails;
import com.example.newsports.Models.EventModel;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {


    private Context context;
    private List<EventModel> eventList;

    public EventAdapter(Context context, List<EventModel> eventList) {
        this.context = context;
        this.eventList = eventList;
    }


    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.event_items,null);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventModel event = eventList.get(position);

        holder.imageEvent.setImageResource(event.getImage());
        holder.textSportName.setText(event.getSportName());
        holder.textDate.setText(event.getDate());
        holder.textLocation.setText(event.getLocation());

        holder.main_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), SportsDetails.class);
                intent.putExtra("Img",event.getImage());
                intent.putExtra("Name",event.getSportName());
                intent.putExtra("Date",event.getDate());
                intent.putExtra("Location",event.getLocation());
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        ImageView imageEvent;
        TextView textSportName, textDate, textLocation;

        CardView main_card;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            imageEvent = itemView.findViewById(R.id.image_event_item);
            textSportName = itemView.findViewById(R.id.text_sport_name_item);
            textDate = itemView.findViewById(R.id.text_date_item);
            textLocation = itemView.findViewById(R.id.text_location_item);

            main_card=itemView.findViewById(R.id.main_card);
        }
    }
}
