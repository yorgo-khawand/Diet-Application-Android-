package com.example.projet;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.projettt.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;


public class DietplanfragmentDietitian extends Fragment {
EditText brekfastmeal,breakfasttime,breakfastsize,lunchmeal,lunchsize,lunchtime,dinnermeal,dinnertime,dinnersize,patientname,date;
    Button btn,lastdate;
    View view;
    String drname;
    TextView stts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_dietplandietitian, container, false);
        drname=this.getArguments().getString("username");

        btn = view.findViewById(R.id.dietplanbtnid);
        lastdate = view.findViewById(R.id.lastdatedietplan);
        lastdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewLastdate();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adddietplan();
            }
        });

        return view;

    }
    public void viewLastdate(){
        String type = "viewlastdate";
        patientname=view.findViewById(R.id.dietplanusername);
        String n = patientname.getText().toString();
        Dietplanworker dp = new Dietplanworker(getContext());
        dp.execute(type,n);

    }
    public void adddietplan(){
        String type ="dietplan";
        brekfastmeal = view.findViewById(R.id.breakfastmeal);
      String  bmeal =brekfastmeal.getText().toString();
      breakfasttime = view.findViewById(R.id.breakfastmealtime);
      String bmealtime=breakfasttime.getText().toString();
      breakfastsize= view.findViewById(R.id.breakfastmealqtty);
      String bmealsize=breakfastsize.getText().toString();
        lunchmeal = view.findViewById(R.id.lunchmeal);
        String  lmeal =lunchmeal.getText().toString();
        lunchtime = view.findViewById(R.id.lunchmealtime);
        String lmealtime=lunchtime.getText().toString();
        lunchsize= view.findViewById(R.id.lunchmealqtty);
        String lmealsize=lunchsize.getText().toString();
        dinnermeal = view.findViewById(R.id.dinnermeal);
        String  dmeal =dinnermeal.getText().toString();
        dinnertime = view.findViewById(R.id.dinnermealtime);
        String dmealtime=dinnertime.getText().toString();
        dinnersize= view.findViewById(R.id.dinnermealqtty);
        String dmealsize=dinnersize.getText().toString();
        patientname = view.findViewById(R.id.dietplanusername);
        String pname = patientname.getText().toString();
        date = view.findViewById(R.id.dietplandate);
        String dateplan = date.getText().toString();
    Dietplanworker d = new Dietplanworker(getContext());
    d.execute(type,pname,drname,bmeal,bmealtime,bmealsize,lmeal,lmealtime,lmealsize,dmeal,dmealtime,dmealsize,dateplan);

    }
    class Dietplanworker extends AsyncTask<String,Void,String> {
        Context ctx;
        String post_data,patient_name,type;
       String  pname,drname,bmeal,bmealtime,bmealsize,lmeal,lmealtime,lmealsize,dmeal,dmealtime,dmealsize,dateplan;
        public Dietplanworker(Context context) {
            this.ctx = context;
        }

        @Override
        protected void onPreExecute() {
            stts = view.findViewById(R.id.dietplanstts);
        }

        @Override
        protected String doInBackground(String... params) {
            type = params[0];
            if(type.contains("dietplan")) {
                pname = params[1];
                drname = params[2];
                bmeal = params[3];
                bmealtime = params[4];
                bmealsize = params[5];
                lmeal = params[6];
                lmealtime = params[7];
                lmealsize = params[8];
                dmeal = params[9];
                dmealtime = params[10];
                dmealsize = params[11];
                dateplan = params[12];
            }
            if(type.contains("viewlastdate")){
                patient_name = params[1];
            }
            String login_url="http://192.168.1.107/php_project/"+params[0]+".php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
           if(type.contains("dietplan")) {
                post_data = URLEncoder.encode("breakfastmeal", "UTF-8") + "=" + URLEncoder.encode(bmeal, "UTF-8") +
                       "&" + URLEncoder.encode("breakfastmealtime", "UTF-8") + "=" + URLEncoder.encode(bmealtime, "UTF-8") +
                       "&" + URLEncoder.encode("breakfastmealsize", "UTF-8") + "=" + URLEncoder.encode(bmealsize, "UTF-8") +
                       "&" + URLEncoder.encode("lunchmeal", "UTF-8") + "=" + URLEncoder.encode(lmeal, "UTF-8") +
                       "&" + URLEncoder.encode("lunchmealtime", "UTF-8") + "=" + URLEncoder.encode(lmealtime, "UTF-8") +
                       "&" + URLEncoder.encode("lunchmealsize", "UTF-8") + "=" + URLEncoder.encode(lmealsize, "UTF-8") +
                       "&" + URLEncoder.encode("dinnermeal", "UTF-8") + "=" + URLEncoder.encode(dmeal, "UTF-8") +
                       "&" + URLEncoder.encode("dinnermealtime", "UTF-8") + "=" + URLEncoder.encode(dmealtime, "UTF-8") +
                       "&" + URLEncoder.encode("dinnermealsize", "UTF-8") + "=" + URLEncoder.encode(dmealsize, "UTF-8") +
                       "&" + URLEncoder.encode("patientname", "UTF-8") + "=" + URLEncoder.encode(pname, "UTF-8") +
                       "&" + URLEncoder.encode("doctorname", "UTF-8") + "=" + URLEncoder.encode(drname, "UTF-8") +
                       "&" + URLEncoder.encode("diary_date", "UTF-8") + "=" + URLEncoder.encode(dateplan, "UTF-8");
           }
           if(type.contains("viewlastdate")){
               post_data = URLEncoder.encode("patientname", "UTF-8") + "=" + URLEncoder.encode(patient_name, "UTF-8") ;

           }
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }
        protected void onPostExecute(String result) {
       if(type.contains("dietplan")) {
           if (result.contains("all fields are required"))
               stts.setText("all fields are required");
           if (result.contains("food inserted succesfully"))
               stts.setText("food inserted succesfully");
       }
       else if(type.contains("viewlastdate"))
           if(result.contains("please enter the patient name"))
               stts.setText("please enter the patient name");
           else if(result.contains("patient not found"))
               stts.setText("patient not found");
           else stts.setText(result);
        }

        }
    }
