package com.example.newsports.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsports.R;
import com.example.newsports.Models.LookingforModel;

import java.util.List;

public class Looking extends RecyclerView.Adapter<Looking.LookingViewHolder> {


    private Context context;
    private List<LookingforModel> lookingList;

    public Looking(Context context, List<LookingforModel> lookingList) {
        this.context = context;
        this.lookingList = lookingList;
    }


    @NonNull
    @Override
    public LookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.looking_items,null);
        return new LookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LookingViewHolder holder, int position) {
        LookingforModel looking = lookingList.get(position);

        holder.imageLooking.setImageResource(looking.getImage());
        holder.textDescription.setText(looking.getDescription());
          holder.imageWhatsapp.setImageResource(looking.getW_image());
          holder.phone.setText(looking.getPhone());
        holder.textLocationLooking.setText(looking.getLocation());
holder.imageWhatsapp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String num=looking.getPhone();
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://api."+"whatsapp.com/send?phone="+num+"&text= I am Interested"));
        context.startActivity(intent);
    }
});
       /* holder.main_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), SportsDetails.class);
                intent.putExtra("Img",event.getImage());
                intent.putExtra("Name",event.getSportName());
                intent.putExtra("Date",event.getDate());
                intent.putExtra("Location",event.getLocation());
                v.getContext().startActivity(intent);
            }
        });*/
    }


    @Override
    public int getItemCount() {
        return lookingList.size();
    }

    public class LookingViewHolder extends RecyclerView.ViewHolder {

        ImageView imageLooking,imageWhatsapp;
        TextView textDescription, textLocationLooking,phone;

//        CardView main_card_looking;
        public LookingViewHolder(@NonNull View itemView) {
            super(itemView);

            imageLooking = itemView.findViewById(R.id.image_looking);
            textDescription = itemView.findViewById(R.id.text_description);
            textLocationLooking = itemView.findViewById(R.id.text_location_looking);
           imageWhatsapp=itemView.findViewById(R.id.image_whatsapp);
            phone=itemView.findViewById(R.id.text_phone);
//
//            main_card_looking=itemView.findViewById(R.id.main_card_looking);
        }
    }
}
