package com.lostnfound.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.lostnfound.R;
import com.lostnfound.activity.About;
import com.lostnfound.activity.UserLogin;
import com.lostnfound.adapter.MenuFragmentAdapter;
import com.lostnfound.utilityClasses.CustomDialog;

public class MenuFragment extends Fragment {
    ListView itemlist;
    String[] item = {"Categories","Subscription", "FAQs", "About LostNFound", "Terms and Conditions","Privacy Policy",
          "App Version 1.0","Settings", "Share", "Logout"};
    int[] itemImages = {R.drawable.baseline_preview_24,R.drawable.popular, R.drawable.baseline_question_mark_24, R.drawable.about,
            R.drawable.terms, R.drawable.baseline_privacy_tip_24, R.drawable.baseline_android_24,
            R.drawable.baseline_settings_24,R.drawable.baseline_share_24, R.drawable.baseline_logout_24};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_menu, container, false);
        MenuFragmentAdapter adapter = new MenuFragmentAdapter(getContext(), item, itemImages);
        itemlist = view.findViewById(R.id.list);
        itemlist.setAdapter(adapter);
        itemlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = item[position];
                // You can create an Intent to navigate to the appropriate activity based on the selected item
                if (selectedItem.equals("Categories")) {
                    // Navigate to the About activity

                }
                else if (selectedItem.equals("Subscription")) {
                    // Navigate to another activity

                }
                else if (selectedItem.equals("FAQs")) {
                    // Navigate to another activity

                }
                else if (selectedItem.equals("About LostNFound")) {
                    Intent intent = new Intent(getContext(), About.class);
                    startActivity(intent);

                }
                else if (selectedItem.equals("Terms and Conditions")) {
                    // Navigate to another activity

                }
                else if (selectedItem.equals("Privacy Policy")) {
                    // Navigate to another activity

                }
                else if (selectedItem.equals("App Version 1.0")) {
                    // Navigate to another activity

                }
                else if (selectedItem.equals("Share")) {
                    shareApp();


                }
                else if (selectedItem.equals("Logout")) {
                    CustomDialog.showCustomDialog(getContext(), "Logout", "Are you sure you want to log out?",
                            new Runnable() {
                                @Override
                                public void run() {
                                    // Handle the positive (Yes) button click here
                                    logout();
                                }
                            }
                    );
                }
            }
        });

        return view;


    }

    private void shareApp() {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out this amazing app!");
            sendIntent.setType("text/plain");
            // You can set a title for the chooser dialog
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);

    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getContext(), UserLogin.class);
        startActivity(intent);
        getActivity().finish();

    }
}