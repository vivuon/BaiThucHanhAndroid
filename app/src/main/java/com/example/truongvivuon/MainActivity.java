package com.example.truongvivuon;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    AppBarConfiguration appBarConfiguration;
    NavigationView navigationView;
    DrawerLayout drawer;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    FirebaseDatabase fDatabase;
    FirebaseAuth fAuth;

    TextView email,name;
    private String Uid;
    EditText et_Name,et_Phone,et_Address;
    TextView tv_Email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fDatabase = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();

            //Thêm toprestaurant trên fb
//       Query query= FirebaseDatabase.getInstance().getReference().child("restaurants");
//       query.addValueEventListener(new ValueEventListener() {
//           @Override
//           public void onDataChange(@NonNull DataSnapshot snapshot) {
//               for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
//                   Restaurant restaurant=dataSnapshot.getValue(Restaurant.class);
//                   if (restaurant.name.contains("Circle K")||restaurant.name.contains("VinMart")||restaurant.name.contains("Highland Coffee"))
//                   {
//                       TopRestaurant topRestaurant=new TopRestaurant(restaurant.name,restaurant.logo,restaurant.rate,restaurant.resKey);
//                       FirebaseDatabase.getInstance().getReference().child("toprestaurants").child(restaurant.resKey).setValue(restaurant).addOnSuccessListener(new OnSuccessListener<Void>() {
//                           @Override
//                           public void onSuccess(Void aVoid) {
//
//                           }
//                       });
//                   }
//               }
//
//           }
//
//           @Override
//           public void onCancelled(@NonNull DatabaseError error) {
//
//           }
//       });



        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navView);

        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,
                drawer,
                toolbar,
                R.string.open,
                R.string.close);

        drawer.addDrawerListener(actionBarDrawerToggle);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.orderFragment, R.id.profileFragment)
                .setDrawerLayout(drawer)
                .build();

        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View view = navigationView.getHeaderView(0);
        name=view.findViewById(R.id.tvNameMain);
        email=view.findViewById(R.id.tvEmailMain);


        tv_Email=findViewById(R.id.tv_Email);
        et_Name=findViewById(R.id.et_Name);
        et_Phone=findViewById(R.id.et_Phone);
        et_Address=findViewById(R.id.et_Address);
    }
}