package com.lostnfound.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.lostnfound.R;

public class UserRegister extends AppCompatActivity {
    TextInputEditText firstName, lastName, email, password, cpassword,phone;
    Button signUpButton;
    TextView logintxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        initialization();
    }

    private void initialization() {
        firstName= findViewById(R.id.fname);
        lastName= findViewById(R.id.lname);
        email= findViewById(R.id.email);
        password= findViewById(R.id.password);
        cpassword= findViewById(R.id.confirmPassword);
        phone= findViewById(R.id.phone);
        signUpButton= findViewById(R.id.singin_btn);
        logintxt= findViewById(R.id.loginText);
        logintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserRegister.this,UserLogin.class));
                finish();

            }
        });

    }
}