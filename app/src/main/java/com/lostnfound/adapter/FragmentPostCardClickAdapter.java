package com.lostnfound.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lostnfound.R;
import com.lostnfound.activity.FoundedItemPost;
import com.lostnfound.activity.LostItemPost;
import com.lostnfound.modal_classes.HomeServiceModal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FragmentPostCardClickAdapter extends RecyclerView.Adapter<FragmentPostCardClickAdapter.MyViewHolder>{
    Context mContext;
    List<HomeServiceModal>list;

    public FragmentPostCardClickAdapter(Context mContext, List<HomeServiceModal> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card_design,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HomeServiceModal modal= list.get(position);
        int imageUri;
        imageUri= modal.getImage();
        Picasso.get().load(imageUri).into(holder.image);
        holder.service.setText(modal.getService());
        holder.description.setText(modal.getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position= holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    if (position == 0) {
                        // First card clicked, navigate to LostItemPost
                        Intent intent = new Intent(mContext, FoundedItemPost.class);
                        mContext.startActivity(intent);
                    } else if (position == 1) {
                        // Second card clicked, navigate to FoundedItemPost
                        Intent intent = new Intent(mContext, LostItemPost.class);
                        mContext.startActivity(intent);
                    }
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{
           ImageView image;
           TextView service, description;
           CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.serviceImage);
            service= itemView.findViewById(R.id.serviceName);
            description= itemView.findViewById(R.id.serviceDescription);
            cardView= itemView.findViewById(R.id.cardView);


        }
    }
}
