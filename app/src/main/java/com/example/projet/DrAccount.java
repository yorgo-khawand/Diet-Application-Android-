package com.example.projet;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.projettt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DrAccount extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_account);
        bottomNavigationView=findViewById(R.id.bottomnav1);
        name = this.getIntent().getStringExtra("username");
        Bundle b = new Bundle();
        b.putString("username",name);
       Bundle bundle = new Bundle();
       bundle.putString("username",name);
       DrFragment d = new DrFragment();
       d.setArguments(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container,d).commit();
        bottomNavigationView.setSelectedItemId(R.id.drfragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                Bundle bundle=null;
                switch (item.getItemId()){
                    case R.id.drfragment:
                         bundle = new Bundle();
                        bundle.putString("username",name);
                        fragment = new DrFragment();
                        fragment.setArguments(bundle);
                        break;
                    case R.id.appointmentfragment:
                        bundle = new Bundle();
                        bundle.putString("username",name);
                        fragment = new AppointmentFragmentDietitian();
                        fragment.setArguments(bundle);
                        break;
                    case R.id.viewappfragment:
                        bundle = new Bundle();
                        bundle.putString("username",name);
                        fragment = new ViewAppointment();
                        fragment.setArguments(bundle);
                        break;
                    case R.id.dietplanfragment:
                        bundle = new Bundle();
                        bundle.putString("username",name);
                        fragment = new DietplanfragmentDietitian();
                        fragment.setArguments(bundle);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container,fragment).commit();


                return true;
            }
        });
    }
}