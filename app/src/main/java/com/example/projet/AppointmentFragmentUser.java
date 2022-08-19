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


public class AppointmentFragmentUser extends Fragment {
    String usrname;
    List<UserAppointment> appoint;
    TextView t1,t2,t3;

    public AppointmentFragmentUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_appointmentuser, container, false);
        t1 = (TextView) v.findViewById(R.id.text1);
        t2 = (TextView) v.findViewById(R.id.text2);
        t3 = (TextView) v.findViewById(R.id.text3);
        usrname = this.getArguments().getString("username").toString();
        getAppointment();
        return v;
    }
    private void getAppointment() {
                    RetrofitClient.getRetrofitClient().getAppointmentsUser().enqueue(new Callback<List<UserAppointment>>(){
                @Override
                public void onResponse(Call<List<UserAppointment>> call, Response<List<UserAppointment>> response) {
                    if (response.body() != null) {
                        appoint = response.body();
                        for (UserAppointment a : appoint) {
                            if (a.getUsername().equals(usrname)) {
                                String Date = "";
                                String Day = "";
                                String Time = "";
                                Date += a.getDate();
                                Day += a.getDay();
                                Time += a.getTime();
                                t1.setText(Date);
                                t2.setText(Day);
                                t3.setText(Time);
                            }

                        }
                    }
                }

                @Override
                public void onFailure(Call<List<UserAppointment>> call,Throwable t) {
                }
            });
        }
    }
