package com.lostnfound.activity;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;

import com.lostnfound.R;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
              startActivity( new Intent(SplashScreen.this, UserLogin.class));
              finish();

           }
       },3000);

    }
}