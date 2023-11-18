package com.lostnfound.activity;

import static androidx.fragment.app.FragmentManager.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.lostnfound.R;
import com.lostnfound.modal_classes.UserRegistration;

public class UserRegister extends AppCompatActivity {
    TextInputEditText firstName, lastName, email, password, cpassword,phone;
    Button signUpButton;
    TextView logintxt;
    ProgressDialog progressDialog;
    FirebaseAuth mauth;
    FirebaseDatabase firebaseDatabase;

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
        mauth=  FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Registering");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

        logintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserRegister.this,UserLogin.class));
                finish();
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();
            }
        });

    }

    private void registerUser() {
        String fname = firstName.getText().toString().trim();
        String lname = lastName.getText().toString().trim();
        String uemail = email.getText().toString().trim();
        String telephone = phone.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        String cpwd = cpassword.getText().toString().trim();
        String role = "Users";

        if (fname.isEmpty() || lname.isEmpty() || uemail.isEmpty() || telephone.isEmpty() || pwd.isEmpty() || cpwd.isEmpty()) {
            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(uemail).matches()) {
            Toast.makeText(getApplicationContext(), "Provide a valid email", Toast.LENGTH_SHORT).show();
            email.requestFocus();
            return;
        } else if (pwd.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password should be at least 6 characters", Toast.LENGTH_SHORT).show();
            password.requestFocus();
            return;
        }
        else if (!pwd.equals(cpwd)) {
            Toast.makeText(getApplicationContext(), "Password Mistmatched", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.show();

        mauth.createUserWithEmailAndPassword(uemail, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    UserRegistration user = new UserRegistration(fname, lname, uemail, telephone, pwd, cpwd, role);
                    firebaseDatabase.getInstance().getReference("Registered Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(), UserLogin.class));
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_LONG).show();
                                        progressDialog.dismiss();

                                    }
                                }
                            });
                } else {
                    // Handle the failure to create a user
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}