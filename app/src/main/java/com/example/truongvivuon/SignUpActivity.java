package com.example.truongvivuon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;


public class SignUpActivity extends AppCompatActivity {

    AppBarConfiguration appBarConfiguration;
    NavController navController;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navController = Navigation.findNavController(this,R.id.nav_graph);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.fullnameFragment, R.id.addressFragment, R.id.usernamePasswordFragment)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController( toolbar,navController);
    }
}