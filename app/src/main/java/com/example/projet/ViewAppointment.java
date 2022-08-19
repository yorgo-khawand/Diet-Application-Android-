package com.example.projet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projettt.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewAppointment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager layoutManager;
    View view;
    String drname;
    List<AppointmentModel>jsonresponse,appointmentlists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_view_appointmentdietitian, container, false);
        drname=this.getArguments().getString("username").toString();
        recyclerView = view.findViewById(R.id.appointmentrecyclerview);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        viewAppointmentlist();

        return view;
    }

    public void viewAppointmentlist() {
        jsonresponse=new ArrayList<>();
        appointmentlists=new ArrayList<>();
        RetrofitClient.getRetrofitClient().getAppointments().enqueue(new Callback<List<AppointmentModel>>() {

            @Override
            public void onResponse(Call<List<AppointmentModel>> call, Response<List<AppointmentModel>> response) {
if (response.body()!=null){
    jsonresponse=response.body();

    for (AppointmentModel a:jsonresponse){
        if(a.getDoctorname().equals(drname))
            appointmentlists.add(a);
           else continue;
    }
    adapter = new AppointmentAdapter(getContext(),appointmentlists);
    recyclerView.setAdapter(adapter);
    adapter.notifyDataSetChanged();
}
            }

            @Override
            public void onFailure(Call<List<AppointmentModel>> call, Throwable t) {

            }
        });


    }
}