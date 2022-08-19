package com.example.projet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.projettt.R;

public class SubscriptionFragment extends Fragment {
    ImageView sub1;
    ImageView sub2;
    ImageView sub3;

    public SubscriptionFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_subscription, container, false);
              sub1 = (ImageView) v.findViewById(R.id.subscribe1);
        sub2 = (ImageView) v.findViewById(R.id.subscribe2);
        sub3 = (ImageView) v.findViewById(R.id.subscribe3);
        Intent intent = new Intent(getActivity(), PaymentSubscriber.class);
            sub1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    intent.putExtra("payment","100");
                   intent.putExtra("Date","+1 month");
                    startActivity(intent);
                }
            });
        sub2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent.putExtra("payment","300");
               intent.putExtra("Date","+3 month");
                startActivity(intent);
            }
        });
        sub3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent.putExtra("payment","900");
               intent.putExtra("Date","+12 month");
                startActivity(intent);
            }
        });
        return v;
    }

}