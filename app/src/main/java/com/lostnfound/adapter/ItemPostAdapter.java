package com.lostnfound.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lostnfound.R;
import com.lostnfound.modal_classes.ItemPostModal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemPostAdapter extends RecyclerView.Adapter<ItemPostAdapter.ViewHolder>{
    private Context context;
    ArrayList<ItemPostModal>list;

    public ItemPostAdapter(Context context, ArrayList<ItemPostModal> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.verticaldisplay_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemPostModal modal= list.get(position);
        int imageUri;
        imageUri= modal.getImage();
        Picasso.get().load(imageUri).into(holder.image);
        holder.title.setText(modal.getTitle());
        holder.location.setText(modal.getLocation());
        holder.date.setText(modal.getDate());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
         ImageView image;
         TextView title, location, date, description,address, category;
         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             image= itemView.findViewById(R.id.image);
             title= itemView.findViewById(R.id.title);
             location= itemView.findViewById(R.id.location);
             date= itemView.findViewById(R.id.date);

         }
     }
}
