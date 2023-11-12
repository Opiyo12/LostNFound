package com.lostnfound.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lostnfound.R;
import com.lostnfound.fragment.HomeFragment;
import com.lostnfound.fragment.MenuFragment;
import com.lostnfound.fragment.MyPostFragment;
import com.lostnfound.fragment.SubscribeFragment;

public class UserHome extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment= new HomeFragment();
    MenuFragment menuFragment= new MenuFragment();
    MyPostFragment myPostFragment= new MyPostFragment();


    SubscribeFragment subscribeFragment= new SubscribeFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        bottomNavigationView = findViewById(R.id.bottomNavBar);
        //Code for displaying the home fragment first
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).addToBackStack(null).commit();
                return true;
            }
            else if (itemId == R.id.mypost) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, myPostFragment).addToBackStack(null).commit();
                return true;
            }
            else if (itemId == R.id.subscribe) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, subscribeFragment).addToBackStack(null).commit();
                return true;
            }
            else if (itemId == R.id.menu) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment).addToBackStack(null).commit();
                return true;
            }
           return false;
        });

    }
}
