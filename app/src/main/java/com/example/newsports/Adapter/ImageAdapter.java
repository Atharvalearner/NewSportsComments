package com.example.newsports.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.newsports.R;

public class ImageAdapter extends PagerAdapter {
    Context context;
    private View view;
    private Object object;

    public ImageAdapter(Context context) {
        this.context = context;
    }


    private int[] sliderImageId=new int[]{
            R.drawable.cricket_ground ,R.drawable.volleyball_ground,R.drawable.football_ground,R.drawable.volleyball_ground1
    };

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(sliderImageId[position]);
        ((ViewPager) container).addView(imageView,0);
        return imageView;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        this.view=view;
        this.object=object;
        return view==((ImageView) object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    @Override
    public int getCount() {
        return sliderImageId.length;
    }
}
