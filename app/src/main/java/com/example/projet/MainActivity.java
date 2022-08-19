package com.example.projet;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {
EditText Username,Password;
TextView txterror;
Button btn,loginDrbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Username=findViewById(R.id.loginUsername);
        Password= findViewById(R.id.loginPassword);
        txterror=findViewById(R.id.txterror);
        btn= findViewById(R.id.loginbtn);
        loginDrbtn=findViewById(R.id.loginDrbtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=Username.getText().toString();
                String pass=Password.getText().toString();
                LoginWorker l = new LoginWorker(MainActivity.this);
                l.execute("Userlogin",name,pass); }});
        loginDrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=Username.getText().toString();
                String pass=Password.getText().toString();
                LoginWorker l = new LoginWorker(MainActivity.this);
                l.execute("doctorlogin",name,pass); }});


    }
    class LoginWorker extends AsyncTask<String,Void,String> {
        Context ctx;
        String user_name;
        String password;
        String type;
        public LoginWorker(Context context) {
            this.ctx = context;
        }

        @Override
        protected void onPreExecute() {
//            loginDrbtn.setEnabled(false);
//            btn.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... params) {
             type = params[0];
            user_name = params[1];
             password = params[2];
            String login_url="http://192.168.1.107/php_project/login.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8") +"="+ URLEncoder.encode(user_name, "UTF-8")+
                        "&"+ URLEncoder.encode("password", "UTF-8") +"="+ URLEncoder.encode(password, "UTF-8")+
                        "&"+ URLEncoder.encode("type", "UTF-8") +"="+ URLEncoder.encode(type, "UTF-8");
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
            btn.setEnabled(true);
            loginDrbtn.setEnabled(true);

            if(result.contains("all fields are required")) {
                txterror.setText("all fields are required");
            }
         else if(result.equals("username not found or incorrect password")) {
                txterror.setText("username not found or incorrect password");
            }else if (result.equals("user login success")){
                Intent intent = new Intent(ctx,NavigationButtonClass.class);
                intent.putExtra("username",user_name);
                startActivity(intent);
            }
            else if (result.contains("dr username not found try again")){
                txterror.setText("dr username not found try again");}
            else if (result.equals("dr login success")){
                txterror.setText("dr logged in");
                Intent intent = new Intent(ctx,DrAccount.class);
            intent.putExtra("username",user_name);
                startActivity(intent);}
            }
        }
    }