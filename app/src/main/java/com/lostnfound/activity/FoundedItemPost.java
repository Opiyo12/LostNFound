package com.lostnfound.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.lostnfound.R;
import com.lostnfound.adapter.ItemViewPagerAdapter;
import com.lostnfound.modal_classes.ItemFoundModal;
import com.lostnfound.modal_classes.ItemUploadModal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoundedItemPost extends AppCompatActivity {
    TextInputLayout title, category, description, placeFound;
    Button submit, date;
    RelativeLayout pickImage;
    Uri ImageUri;
    ViewPager viewPager;
    TextView itemDate;
    ProgressDialog progressDialog;
    private static int PICK_IMAGE_REQUEST = 1;
    ArrayList<String> UrlsList;
    ArrayList<Uri> ChooseImageList;
    //firebases variables here
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();//for creating a specific path for a file

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_founded_item_post);
        title = findViewById(R.id.title_description);
        category = findViewById(R.id.category);
        description = findViewById(R.id.description);
        placeFound = findViewById(R.id.placeFound);
        pickImage = findViewById(R.id.imageChooser);
        submit = findViewById(R.id.submit);
        date= findViewById(R.id.dateBtn);
        itemDate= findViewById(R.id.itemDate);
        viewPager = findViewById(R.id.viewpager);
        ChooseImageList = new ArrayList<>();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading Data");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getInstance().getReference();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               pickImageFromGallery();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImages();
            }
        });

    }

    private void showDatePickerDialog() {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            // Handle the selected date
                            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                            itemDate.setText(selectedDate);
                        }
                    },
                    year, month, day
            );
            datePickerDialog.show();
    }

    //method for picking the image
    private void pickImageFromGallery() {
        //here we go to the gallery and select images
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                if (data.getClipData() != null) {
                    // Multiple images were selected
                    int count = data.getClipData().getItemCount();
                    for (int i = 0; i < count; i++) {
                        ImageUri = data.getClipData().getItemAt(i).getUri();
                        ChooseImageList.add(ImageUri);
                    }
                    setAdapter();//adapter method for setting the image
                }
            }
        }
    }

    private void setAdapter() {
        ItemViewPagerAdapter adapter = new ItemViewPagerAdapter(this, ChooseImageList);
        viewPager.setAdapter(adapter);
    }

    //method for uploading images
    private void uploadImages() {
        if (ChooseImageList.isEmpty()) {
            Toast.makeText(this, "Please select at least one image", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.show();
        // Create a unique folder for each set of images
        String imageFolder = "ItemImages" + System.currentTimeMillis();
        storageRef = FirebaseStorage.getInstance().getReference().child(imageFolder);
        // List to store image URLs
        UrlsList = new ArrayList<>();
        // Upload each image
        // we need list that images urls
        for (int i = 0; i < ChooseImageList.size(); i++) {
            Uri IndividualImage = ChooseImageList.get(i);
            if (IndividualImage != null) {
                progressDialog.show();
                //StorageReference ImageFolder= storagereference.child(items)
                StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("ItemImages");
                final StorageReference ImageName = ImageFolder.child("Image" + i + ": " + IndividualImage.getLastPathSegment());
                ImageName.putFile(IndividualImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                UrlsList.add(String.valueOf(uri));
                                if (UrlsList.size() == ChooseImageList.size()) {
                                    StorageUploadData(UrlsList);
                                }
                            }
                        });

                    }
                });
            } else {
                Toast.makeText(this, "Please fill All Field", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //method for uploading all data
    private void StorageUploadData(ArrayList<String> urlsList) {
        //TextInputLayout title,category, description,placeFound;
        String itemTiltle = title.getEditText().getText().toString().trim();
        String itemCategory = category.getEditText().getText().toString().trim();
        String itemDescription = description.getEditText().getText().toString().trim();
        String itemPlaceFound = placeFound.getEditText().getText().toString().trim();
        if (itemTiltle.isEmpty() || itemCategory.isEmpty() || itemDescription.isEmpty() || itemPlaceFound.isEmpty()) {
            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }
        ItemUploadModal modal = new ItemUploadModal(itemTiltle, itemCategory, itemDescription, itemPlaceFound, "", urlsList);

        // Create a HashMap to represent the data
        Map<String, Object> itemData = new HashMap<>();
        itemData.put("title", itemTiltle);
        itemData.put("category", itemCategory);
        itemData.put("description", itemDescription);
        itemData.put("placeFound", itemPlaceFound);
        itemData.put("imageUrls", urlsList);

        // Get a reference to the Firebase Realtime Database
        DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference().child("Items");

        // Push the data to the "Items" node
        String newItemKey = itemsRef.push().getKey(); // Generate a unique key for the new item
        itemsRef.child(newItemKey).setValue(itemData)
                .addOnSuccessListener(aVoid -> {
                    progressDialog.dismiss();
                    Toast.makeText(FoundedItemPost.this, "Your data uploaded successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(FoundedItemPost.this, "Failed to upload data", Toast.LENGTH_SHORT).show();
                });

        // If you want to clear ViewPager after uploading images
        ChooseImageList.clear();
    }

    }





