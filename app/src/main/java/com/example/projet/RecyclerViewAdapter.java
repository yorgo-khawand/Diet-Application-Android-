package com.example.projet;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projettt.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    List<UserInfo> arrayList;
Context ctx;
    public RecyclerViewAdapter(List<UserInfo> list,Context ctx) {
        arrayList=new ArrayList<>();
        this.arrayList =list;
        this.ctx = ctx;

    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
      RecyclerViewHolder vholder = new RecyclerViewHolder(view);
     Dialog mydialog = new Dialog(ctx);
     mydialog.setContentView(R.layout.user_information);


        vholder.btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              TextView name = mydialog.findViewById(R.id.usernameDialog);
              TextView birthday = mydialog.findViewById(R.id.birthdaydialog);
              TextView height = mydialog.findViewById(R.id.heightdialog);
              TextView weight = mydialog.findViewById(R.id.weightdialog);
              TextView bodyfat = mydialog.findViewById(R.id.bodyfatdialog);
              TextView gender = mydialog.findViewById(R.id.genderdialog);
              TextView startdate = mydialog.findViewById(R.id.startdatedialog);
              TextView enddate = mydialog.findViewById(R.id.enddatedialog);
              name.setText( arrayList.get(vholder.getAdapterPosition()).getUsername());
              birthday.setText(birthday.getText()+ arrayList.get(vholder.getAdapterPosition()).getBirthday());
              height.setText(height.getText() + Integer.toString(arrayList.get(vholder.getAdapterPosition()).getHeight()));
              weight.setText(weight.getText()+Integer.toString(arrayList.get(vholder.getAdapterPosition()).getWeight()));
              bodyfat.setText(bodyfat.getText()+Integer.toString(arrayList.get(vholder.getAdapterPosition()).getBody_fat()));
             gender.setText(gender.getText()+arrayList.get(vholder.getAdapterPosition()).getGender());
              startdate.setText( arrayList.get(vholder.getAdapterPosition()).getStartdate());
              enddate.setText( arrayList.get(vholder.getAdapterPosition()).getEnd_date());
mydialog.show();
          }
      });
        return vholder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.name.setText(arrayList.get(position).getUsername());
        holder.startDate.setText(arrayList.get(position).getStartdate());
        holder.endDate.setText(arrayList.get(position).getEnd_date());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView name, startDate, endDate;
        Button btn;
        public RecyclerViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.name);
            startDate =  view.findViewById((R.id.startDate));
            endDate = view.findViewById((R.id.endDate));
            btn=view.findViewById(R.id.button);
//            image = (ImageView) view.findViewById(R.id.imageView2);

        }
    }
}
