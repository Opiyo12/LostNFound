package com.lostnfound.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.lostnfound.R;

import java.util.ArrayList;

public class ItemViewPagerAdapter extends PagerAdapter {
    private Context context;
    ArrayList<Uri>ImageUrls;
    LayoutInflater layoutInflater;

    public ItemViewPagerAdapter(Context context, ArrayList<Uri> imageUrls) {
        this.context = context;
        ImageUrls = imageUrls;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= layoutInflater.inflate(R.layout.showimageslayout, container,false);
        ImageView imageView=  view.findViewById(R.id.uploadImage);
        imageView.setImageURI(ImageUrls.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return ImageUrls.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull View container, int position, @NonNull Object object) {
        ((RelativeLayout)object).removeView(container);
    }
}