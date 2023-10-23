    package com.lostnfound.fragment;

    import static com.lostnfound.activity.RecyclerViewHelper.initGridRecyclerView;

    import android.net.wifi.hotspot2.pps.HomeSp;
    import android.os.Bundle;

    import androidx.appcompat.app.ActionBarDrawerToggle;
    import androidx.fragment.app.Fragment;
    import androidx.recyclerview.widget.RecyclerView;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import com.lostnfound.R;
    import com.lostnfound.activity.RecyclerViewHelper;
    import com.lostnfound.adapter.HomeServiceAdapter;
    import com.lostnfound.adapter.ItemPostAdapter;
    import com.lostnfound.modal_classes.HomeServiceModal;
    import com.lostnfound.modal_classes.ItemPostModal;

    import java.util.ArrayList;


    public class HomeFragment extends Fragment {
        ArrayList<HomeServiceModal> list;
        ArrayList<ItemPostModal>postModals;
        ItemPostAdapter myAdapter;
        HomeServiceAdapter adapter;
        RecyclerView recyclerView, recView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment

            View view=   inflater.inflate(R.layout.fragment_home, container, false);
            list= new ArrayList<>();
            recyclerView= view.findViewById(R.id.recyclerview);
          RecyclerView initializedRecyclerView = initGridRecyclerView(getContext(),recyclerView,2);
          list.add(new HomeServiceModal(R.drawable.found,"Found Item","post found item"));
          list.add(new HomeServiceModal(R.drawable.post,"Lost Item","post lost item"));

            adapter= new HomeServiceAdapter(getContext(), list);
            initializedRecyclerView.setAdapter(adapter);
            //recycler vertical display
            recView = view.findViewById(R.id.recyclerview);
            RecyclerView initializedRecycler = initGridRecyclerView(getContext(),recView,2);
            postModals= new ArrayList<>();
            postModals.add(new ItemPostModal(R.drawable.lap,"Lost Laptop","Mbarara","12/11/2023",
                    "it got lost","Katete","Computer"));
            postModals.add(new ItemPostModal(R.drawable.lap,"Lost Laptop","Mbarara","12/11/2023",
                    "it got lost","Katete","Computer"));
            myAdapter= new ItemPostAdapter(getContext(),postModals);
           // initializedRecycler.setAdapter(myAdapter);



            return view;

        }

    }