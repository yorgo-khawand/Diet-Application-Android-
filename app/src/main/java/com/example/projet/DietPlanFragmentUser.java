package com.example.projet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.projettt.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DietPlanFragmentUser extends Fragment {
    List<UserDietPlan> appoint;
    TextView date, b, l, d;
    String username;

    public DietPlanFragmentUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dietplanuser, container, false);
        username = this.getArguments().getString("username").toString();
        date = (TextView) v.findViewById(R.id.date);
        b = (TextView) v.findViewById(R.id.breakfast);
        l = (TextView) v.findViewById(R.id.lunch);
        d = (TextView) v.findViewById(R.id.dinner);

        getDiet();

        return v;
    }

    private void getDiet() {
        RetrofitClient.getRetrofitClient().getDietPlanUser().enqueue(new Callback<List<UserDietPlan>>() {
            @Override
            public void onResponse(Call<List<UserDietPlan>> call, Response<List<UserDietPlan>> response) {
                if (response.body() != null) {
                    appoint = response.body();
                    for (UserDietPlan a : appoint) {
                        if (a.getUsername().equals(username)) {
                            if (a.getType().equals("breakfast")) {
                                String Date = "";
                                String Meal = "";
                                String Type = "";
                                Date += a.getDate();
                                Meal += a.getMeal()+" "+a.getServing_size()+" "+a.getMealtime();
                                Type += a.getType();
                                date.setText(Date);
                                b.setText(Meal);
                                l.setText(Type);
                            } else if (a.getType().equals("lunch")) {
                                String Date = "";
                                String Meal = "";
                                Date += a.getDate();
                                Meal += a.getMeal()+" "+a.getServing_size()+" "+a.getMealtime();
                                date.setText(Date);
                                l.setText(Meal);
                            } else {
                                String Date = "";
                                String Meal = "";
                                Date += a.getDate();
                                Meal += a.getMeal()+" "+a.getServing_size()+" "+a.getMealtime();
                                date.setText(Date);
                                d.setText(Meal);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<UserDietPlan>> call, Throwable t) {
            }
        });
    }
}