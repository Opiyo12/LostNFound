package com.lostnfound.fragment;

import static com.lostnfound.activity.RecyclerViewHelper.initGridRecyclerView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lostnfound.R;
import com.lostnfound.adapter.FragmentPostCardClickAdapter;
import com.lostnfound.modal_classes.HomeServiceModal;

import java.util.ArrayList;

public class MyPostFragment extends Fragment {

    ArrayList<HomeServiceModal> list;
    FragmentPostCardClickAdapter adapter;
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_post, container, false);
        list= new ArrayList<>();
        recyclerView= view.findViewById(R.id.recyclerview);
        RecyclerView initializedRecyclerView = initGridRecyclerView(getContext(),recyclerView,2);
        list.add(new HomeServiceModal(R.drawable.found,"Found Item","post found item"));
        list.add(new HomeServiceModal(R.drawable.post,"Lost Item","post lost item"));
        adapter= new FragmentPostCardClickAdapter(getContext(), list);
        initializedRecyclerView.setAdapter(adapter);
        return view;
    }
}