package com.example.authentication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Switch;

public class NavigationActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_location, R.id.nav_contract,
                R.id.nav_signOut, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void onNavigationItemSelected(MenuItem menuItem) {
        /*switch(menuItem.getItemId()){
            case R.id.nav_home:
                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
                break;*/
        if (menuItem.getItemId() == R.id.nav_home) {

        }
        if (menuItem.getItemId() == R.id.nav_location) {

        }
        if (menuItem.getItemId() == R.id.nav_contract) {
            Intent intent = new Intent(getApplicationContext(), ContractActivity.class);
            startActivity(intent);
        }
        if(menuItem.getItemId()==R.id.nav_location) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
        }
        if (menuItem.getItemId() == R.id.nav_signOut) {
            mAuth.signOut();
            finish();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        }
        if (menuItem.getItemId() == R.id.nav_rating) {

        }
        if (menuItem.getItemId() == R.id.nav_share) {

        }
        if (menuItem.getItemId() == R.id.nav_send) {

        }
       /* int id = menuItem.getItemId();
        if (id==R.id.nav_tools){
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
        }*/
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return;
    }
}
