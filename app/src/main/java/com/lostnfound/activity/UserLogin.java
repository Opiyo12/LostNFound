package com.lostnfound.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.lostnfound.R;

public class UserLogin extends AppCompatActivity {
 TextInputLayout email, password;
 TextView signup;
 Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        initialization();//initilazing widget method
        userLogin(); //method for user to login into the system

    }

    private void userLogin() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLogin.this, UserHome.class));
                finish();
            }
        });
    }


    private void initialization() {
        email =  findViewById(R.id.email);
        password =  findViewById(R.id.password);
        signup =  findViewById(R.id.textSignUp);
        login= findViewById(R.id.singin_btn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLogin.this, UserRegister.class));
                finish();
            }
        });

    }
}