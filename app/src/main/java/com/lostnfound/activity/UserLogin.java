package com.lostnfound.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lostnfound.R;

public class UserLogin extends AppCompatActivity {
 TextInputLayout useremail, userpassword;
 TextView signup, forgotPassword;
 Button login;
 ProgressDialog progressDialog;
 FirebaseAuth mauth;
 DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        useremail =  findViewById(R.id.email);
        userpassword =  findViewById(R.id.password);
        forgotPassword= findViewById(R.id.forgetPassword);
        signup =  findViewById(R.id.textSignUp);
        login= findViewById(R.id.singin_btn);
        mauth= FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Please wait...");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserRegister.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loginUser();
            }
        });
    }

    private void loginUser() {
        String email = useremail.getEditText().getText().toString().trim();
        String password = userpassword.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
            useremail.requestFocus();
            return;
        } else if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
            userpassword.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getApplicationContext(), "Provide a valid email", Toast.LENGTH_SHORT).show();
            useremail.requestFocus();
            return;
        } else if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password should be at least 6 characters", Toast.LENGTH_SHORT).show();
            userpassword.requestFocus();
            return;
        }

        progressDialog.show();
        mauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    handleLoginSuccess();//login success method
                } else {
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void handleLoginSuccess() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference().child("Registered Users").child(user.getUid());
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String role = snapshot.child("role").getValue(String.class);
                if (role == null) {
                    Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
                } else if(role.equals("admin")) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), UserRegister.class));
                    finish();
                } else if (role.equals("Users")) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), UserHome.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Login Failed, Please Check Your Credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "An error occurred in the login process", Toast.LENGTH_SHORT).show();
            }
        });
    }



}