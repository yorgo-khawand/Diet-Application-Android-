package com.example.projet;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.projettt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationButtonClass extends AppCompatActivity {

    String name;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigationbuttonmenu);
        //this line hide statusbar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navigationView = findViewById(R.id.bottom_navigation);
        name = this.getIntent().getStringExtra("username");
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new com.example.projet.SubscriptionFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_subs);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                Bundle bundle=null;
                switch (item.getItemId()){

                    case R.id.nav_subs:
                        bundle = new Bundle();
                        bundle.putString("username",name);
                        fragment = new SubscriptionFragment();
                        fragment.setArguments(bundle);
                        break;

                    case R.id.nav_app:
                        bundle = new Bundle();
                        bundle.putString("username",name);
                        fragment = new AppointmentFragmentUser();
                        fragment.setArguments(bundle);
                        break;

                    case R.id.nav_diet:
                        bundle = new Bundle();
                        bundle.putString("username",name);
                        fragment = new DietPlanFragmentUser();
                        fragment.setArguments(bundle);
                        break;

                    case R.id.nav_exercice:
                        fragment = new ExerciceActivityFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();

                return true;
            }
        });

    }
}