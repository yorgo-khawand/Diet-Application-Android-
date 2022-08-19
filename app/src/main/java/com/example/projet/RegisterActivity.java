package com.example.projet;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {
    EditText username,Email,password,phoneNumber,birthday,height,weight,body_fat;
    TextView errortxt,logintextview;
    Button registre;
   String genderId;
    Spinner spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registre=findViewById(R.id.btnregister);
        username = findViewById(R.id.txtusername);
        password = findViewById(R.id.password);
        phoneNumber = findViewById(R.id.phonenumber);
        birthday=findViewById(R.id.birthday);
        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        body_fat=findViewById(R.id.body_fat);
        spn=findViewById(R.id.genderspinner);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.spinnerlist,android.R.layout.simple_spinner_item);
       adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
       spn.setAdapter(adapter);
        Email=findViewById(R.id.emailAddress);
        errortxt=findViewById(R.id.errortxt);
        logintextview=findViewById(R.id.logintextview);
        logintextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        registre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register(view);

            }
        });

    }
    public void Register(View view){

        String Txtusername = username.getText().toString();
        String Txtemail = Email.getText().toString();
        String Txtpassword = password.getText().toString();
        String TxtPhoneNumber = phoneNumber.getText().toString();
        String bday=birthday.getText().toString();
        String h = height.getText().toString();
        String w=weight.getText().toString();
        String bfat= body_fat.getText().toString();
        String gender=spn.getSelectedItem().toString();
        if(gender.equals("male"))  genderId = "1";
        else if(gender.equals("female")) genderId="1";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(Txtusername,Txtemail,Txtpassword,TxtPhoneNumber,bday,h,w,bfat,genderId);

    }

    class BackgroundWorker extends AsyncTask<String,String,String> {
        Context ctx;
        public BackgroundWorker(Context context){
            this.ctx=context;
        }
        @Override
        protected String doInBackground(String... params) {
            String user_name=params[0];
            String Email_Address = params[1];
            String password = params[2];
            String phoneNumber= params[3];
            String bday = params[4];
            String h = params[5];
            String w=params[6];
            String bfat = params[7];
             String g = params[8];
            String register_url = "http://192.168.1.107/php_project/register.php";

            try {
                URL url = new URL(register_url);
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+
                            "&"+ URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(Email_Address,"UTF-8")+
                            "&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+
                            "&"+URLEncoder.encode("phoneNumber","UTF-8")+"="+URLEncoder.encode(phoneNumber,"UTF-8")+
                            "&"+ URLEncoder.encode("birthday","UTF-8")+"="+URLEncoder.encode(bday,"UTF-8")+
                            "&"+URLEncoder.encode("height","UTF-8")+"="+URLEncoder.encode(h,"UTF-8")+
                            "&"+URLEncoder.encode("weight","UTF-8")+"="+URLEncoder.encode(w,"UTF-8")+
                            "&"+URLEncoder.encode("body_fat","UTF-8")+"="+URLEncoder.encode(bfat,"UTF-8")+
                            "&"+URLEncoder.encode("gender","UTF-8")+"="+URLEncoder.encode(g,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result ="";
                    String line="";
                    while((line = bufferedReader.readLine())!=null) {
                        result+=line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPreExecute(){
            registre.setEnabled(false);
        }
        @Override
        protected void onPostExecute(String result) {
            registre.setEnabled(true);
            if (result.contains("email is invalid")) {
                Email.setError("email is invalid");

            }
            else if (result.contains("all fields are required"))
                errortxt.setText("all fields are required");

            else if (result.contains("username cant be more than 20 char"))
                username.setError("username cant be more than 20 char");
            else  if (result.contains("username exist"))
                username.setError("username exist try another one");
            else if (result.equals("height should be positive"))
                height.setError("height should be positive");
            else if (result.equals("weight should be positive"))
                weight.setError("weight should be positive");
            else if(result.equals("bodyfat in invalid"))
                body_fat.setError("bodyfat in invalid");
            else if(result.equals("pass cannot start with number"))
                password.setError("pass cannot start with number");
            else {
                Intent intent = new Intent(ctx,MainActivity.class);
                startActivity(intent);
            }
        }
        @Override
        protected void onProgressUpdate(String...values){
        }
    }

}