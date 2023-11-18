    package com.lostnfound.fragment;

    import static com.lostnfound.activity.RecyclerViewHelper.initGridRecyclerView;

    import android.annotation.SuppressLint;
    import android.os.Bundle;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;
    import androidx.recyclerview.widget.RecyclerView;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.auth.FirebaseUser;
    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;
    import com.lostnfound.R;
    import com.lostnfound.adapter.ItemFoundAdapter;
    import com.lostnfound.adapter.ItemPostAdapter;
    import com.lostnfound.modal_classes.ItemFoundModal;
    import com.lostnfound.modal_classes.ItemPostModal;

    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.Date;
    import java.util.Locale;


    public class HomeFragment extends Fragment {
        ArrayList<ItemPostModal>postModals;
        ArrayList<ItemFoundModal>foundlist;
        ItemPostAdapter myAdapter;
        ItemFoundAdapter adapter2;
        RecyclerView recyclerView1, recyclerView2;
        TextView welcome, date;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment

            View view=   inflater.inflate(R.layout.fragment_home, container, false);
            welcome= view.findViewById(R.id.welcome);
            date= view.findViewById(R.id.currentDate);
            welcomeMessage();
            setDate();
            //recycler vertical display
            recyclerView1 = view.findViewById(R.id.recView);
            RecyclerView initializedRecycler = initGridRecyclerView(getContext(),recyclerView1,2);
            postModals= new ArrayList<>();
            postModals.add(new ItemPostModal(R.drawable.lap,"Lost Laptop","Mbarara","12/11/2023",
                    "it got lost","Katete","Computer"));
            postModals.add(new ItemPostModal(R.drawable.lap,"Lost Laptop","Mbarara","12/11/2023",
                    "it got lost","Katete","Computer"));
            postModals.add(new ItemPostModal(R.drawable.lap,"Lost Laptop","Mbarara","12/11/2023",
                    "it got lost","Katete","Computer"));
            postModals.add(new ItemPostModal(R.drawable.lap,"Lost Laptop","Mbarara","12/11/2023",
                    "it got lost","Katete","Computer"));
            myAdapter= new ItemPostAdapter(getContext(),postModals,4);
            initializedRecycler.setAdapter(myAdapter);

            recyclerView2= view.findViewById(R.id.recView2);
            foundlist= new ArrayList<>();
            RecyclerView initializedRecycler2 = initGridRecyclerView(getContext(),recyclerView2,2);
            foundlist.add(new ItemFoundModal(R.drawable.lap,"Lost Laptop","Mbarara","12/11/2023",
                    "it got lost","Katete","Computer"));
            foundlist.add(new ItemFoundModal(R.drawable.lap,"Lost Laptop","Mbarara","12/11/2023",
                    "it got lost","Katete","Computer"));
            foundlist.add(new ItemFoundModal(R.drawable.lap,"Lost Laptop","Mbarara","12/11/2023",
                    "it got lost","Katete","Computer"));
            foundlist.add(new ItemFoundModal(R.drawable.lap,"Lost Laptop","Mbarara","12/11/2023",
                    "it got lost","Katete","Computer"));
            foundlist.add(new ItemFoundModal(R.drawable.lap,"Lost Laptop","Mbarara","12/11/2023",
                    "it got lost","Katete","Computer"));
            foundlist.add(new ItemFoundModal(R.drawable.lap,"Lost Laptop","Mbarara","12/11/2023",
                    "it got lost","Katete","Computer"));
            adapter2= new ItemFoundAdapter(getContext(),foundlist);
            initializedRecycler2.setAdapter(adapter2);





            return view;

        }

        private void setDate() {
            // Get the current date
            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();
            // Format the current date
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM yyyy", Locale.getDefault());
            String formattedDate = dateFormat.format(currentDate);
          // Set the formatted date to your TextView
            date.setText(formattedDate);
        }

        private void welcomeMessage() {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseUser user = auth.getCurrentUser();

            if (user != null) {
                String userId = user.getUid();
                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            String userName = dataSnapshot.child("lname").getValue(String.class);
                            // Display a welcome message with the user's name
                            welcome.setText("Hello " +userName+","+ "Welcome");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle any errors that may occur
                        Toast.makeText(getContext(), "Failed to retrieve user data", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }


    }