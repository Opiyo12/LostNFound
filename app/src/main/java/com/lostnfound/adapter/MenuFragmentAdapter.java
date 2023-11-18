package com.lostnfound.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lostnfound.R;

public class MenuFragmentAdapter extends BaseAdapter {
    private Context context;
    private  String [] itemlist;
    private int[] itemImages;
    //private int forwardIconResourceId;

    public MenuFragmentAdapter(Context context, String[] itemlist, int[] itemImages) {
        this.context = context;
        this.itemlist = itemlist;
        this.itemImages = itemImages;

    }

    @Override
    public int getCount() {
        return itemlist.length;
    }

    @Override
    public Object getItem(int position) {
        return itemlist[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            viewHolder.icon = view.findViewById(R.id.icon);
            viewHolder.list = view.findViewById(R.id.listText);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        // Set icon and text based on the array
        viewHolder.icon.setImageResource(itemImages[position]);
        viewHolder.list.setText(itemlist[position]);

        // Set the forward icon based on the forwardIconResourceId
       // viewHolder.forward.setImageResource(forwardIconResourceId);

        return view;
    }
    static class ViewHolder {
        ImageView icon;
       // ImageView forward;
        TextView list;
    }

}
