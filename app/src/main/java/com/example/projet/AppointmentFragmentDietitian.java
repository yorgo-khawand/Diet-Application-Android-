package com.example.projet;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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


public class AppointmentFragmentDietitian extends Fragment {
    View view;
String drname;
EditText p_name,date,day,time,address;
TextView msg;
Button send;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_appointmentdietitian, container, false);
        drname=this.getArguments().getString("username").toString();
        send=view.findViewById(R.id.appbtn);
send.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      bookAppointment();
    }
});
        return view;
    }
    public void bookAppointment(){
        p_name = view.findViewById(R.id.appointmentpname);
        String pname=p_name.getText().toString();
        date=view.findViewById(R.id.appointmentdatetext);
        String appdate= date.getText().toString();
        day=view.findViewById(R.id.appointmentdaytext);
        String appday=day.getText().toString();
        time=view.findViewById(R.id.appointmenttimetext);
        String apptime = time.getText().toString();
        address=view.findViewById(R.id.appointmentaddresstext);
        String appaddress=address.getText().toString();
        Log.d("bookAppointment: ",drname);
        AddAppointment addAppointment = new AddAppointment();
        addAppointment.execute(drname,pname,appdate,appday,apptime,appaddress);
    }


    class AddAppointment extends AsyncTask<String,Void,String> {
        Context ctx;

        @Override
        protected void onPreExecute() {
msg=view.findViewById(R.id.appointmentmsg);
        }

        @Override
        protected String doInBackground(String... params) {
            String doctorname = params[0];
String patientname = params[1];
String appdate=params[2];
String appday=params[3];
String apptime=params[4];
String appaddress=params[5];
            String login_url="http://192.168.1.107/php_project/appointment.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("doctorname", "UTF-8") +"="+ URLEncoder.encode(doctorname, "UTF-8")+
                        "&"+ URLEncoder.encode("patientname", "UTF-8") +"="+ URLEncoder.encode(patientname, "UTF-8")+
                        "&"+ URLEncoder.encode("appdate", "UTF-8") +"="+ URLEncoder.encode(appdate, "UTF-8")+
                        "&"+ URLEncoder.encode("appday", "UTF-8") +"="+ URLEncoder.encode(appday, "UTF-8")+
                        "&"+ URLEncoder.encode("apptime", "UTF-8") +"="+ URLEncoder.encode(apptime, "UTF-8")+
                        "&"+ URLEncoder.encode("appaddress", "UTF-8") +"="+ URLEncoder.encode(appaddress, "UTF-8");
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
        protected void onPostExecute(String result){
            Log.d( "onPostExecute: ",result);
if(result.contains("all fields are required"))
    msg.setText("all fields are required");
 else if(result.contains("date and time taken"))
     msg.setText("date and time taken");
 else if(result.contains("appoitment booked"))
     msg.setText("appoitment booked");
            }
        }
    }
