package com.example.agrimony;
import static android.util.Log.d;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;

import com.google.android.material.navigation.NavigationView;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawerLayout;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    public void setContentView(View view){
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout container = drawerLayout.findViewById(R.id.container);
        container.addView(view);
        super.setContentView(drawerLayout);


        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d("Navigation", "Item selected: " + item.getTitle());
        int itemId = item.getItemId();
        if(itemId == R.id.home){
            startActivity(new Intent(getBaseContext(),MainActivity.class));
        }else if(itemId == R.id.assistance){
            startActivity(new Intent(getBaseContext(),Boot.class));

        }else if(itemId == R.id.logout){
            mAuth.signOut();
            startActivity(new Intent(getBaseContext(), LogIn.class));
        }else if(itemId == R.id.crops){
            startActivity(new Intent(getBaseContext(), CropsCycle.class));
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed () {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        super.onBackPressed();
    }



}
