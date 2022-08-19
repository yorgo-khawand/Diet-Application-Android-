package com.example.projet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projettt.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;


public class DrFragment extends Fragment {
    RecyclerView recyclerView;
    List<UserInfo> arrayList,arrayList1,jsonresponse;
    LinearLayoutManager layoutManager;
    RecyclerViewAdapter adapter,adapter1;
    RecyclerView recyclerView2;
    TextView drname;
String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
   View      view=inflater.inflate(R.layout.fragment_dr, container, false);
   drname =view.findViewById(R.id.doctorname);
   name=this.getArguments().getString("username").toString();
   drname.setText(drname.getText()+name);
        recyclerView = view.findViewById(R.id.recyclerview1);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView2 = view.findViewById(R.id.recyclerview2);
        recyclerView2.setHasFixedSize(false);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        jsonresponse = new ArrayList<>();
        fetchUsersub();
        fetchUserNonsub();
   return view;

    }
    public void fetchUserNonsub(){
        RetrofitClient.getRetrofitClient().getNonSubInfo().enqueue(new Callback<List<UserInfo>>() {
            @Override
            public void onResponse(retrofit2.Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
                arrayList = new ArrayList<>();
                if(response.body()!=null) {
                    arrayList=response.body();
                    adapter1 = new RecyclerViewAdapter(arrayList, getContext());
                    recyclerView2.setAdapter(adapter1);
                    adapter1.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<UserInfo>> call, Throwable t) {

            }
        });
    }
    public void fetchUsersub(){
        RetrofitClient.getRetrofitClient().getUsersInfo().enqueue(new Callback<List<UserInfo>>() {
            @Override
            public void onResponse(retrofit2.Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
                arrayList=new ArrayList<>();
                if (response.body() != null) {
                    jsonresponse = response.body();
                    for(UserInfo u :jsonresponse) {
                        if(u.getDoctorname().equals(name))
                            arrayList.add(u);
                        else
                            continue;
                    }
                    adapter = new RecyclerViewAdapter(arrayList, getContext());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<UserInfo>> call, Throwable t) {
                    Toast.makeText(getContext(), "EROOR:" + t.getMessage(), Toast.LENGTH_LONG).show();
                }
        });
    }
}