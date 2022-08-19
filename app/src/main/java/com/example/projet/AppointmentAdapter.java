package com.example.projet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projettt.R;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {
Context ctx;
List<AppointmentModel> appointments;

public AppointmentAdapter(Context ctx,List<AppointmentModel>a){
    this.appointments=a;
    this.ctx=ctx;
}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_item,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pname.setText(appointments.get(position).getPatientName());
        holder.date.setText(appointments.get(position).getDate());
        holder.day.setText(appointments.get(position).getDay());
        holder.time.setText(appointments.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    TextView pname,date,day,time;
public ViewHolder(@NonNull View view){
    super(view);

pname = view.findViewById(R.id.viewname);
date= view.findViewById(R.id.viewdate);
day= view.findViewById(R.id.viewday);
time= view.findViewById(R.id.viewtime);
}
    }
}
