package com.lostnfound.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.lostnfound.R;
import com.lostnfound.fragment.HomeFragment;
import com.lostnfound.fragment.LogoutFragment;
import com.lostnfound.fragment.MyPostFragment;
import com.lostnfound.fragment.ProfileFragment;

public class UserHome extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.navDrawer);
        navigationView = findViewById(R.id.nav);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this
                , drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.home);
        }

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(UserHome.this, "Messages", Toast.LENGTH_SHORT).show();
                    myFragment(new HomeFragment());
                }
                else if (item.getItemId() == R.id.profile) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(UserHome.this, "Profile", Toast.LENGTH_SHORT).show();
                    myFragment(new ProfileFragment());
                }
                else if (item.getItemId() == R.id.logout) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(UserHome.this, "Logout", Toast.LENGTH_SHORT).show();
                    myFragment(new LogoutFragment());
                }
                else if (item.getItemId() == R.id.post) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(UserHome.this, "Login", Toast.LENGTH_LONG).show();
                    myFragment(new MyPostFragment());
                }
                else if (item.getItemId() == R.id.settings) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(UserHome.this, "Settings", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    private void myFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
