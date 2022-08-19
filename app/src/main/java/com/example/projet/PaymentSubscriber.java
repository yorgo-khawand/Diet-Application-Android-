package com.example.projet;
import android.content.Context;
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


public class PaymentSubscriber extends AppCompatActivity {
    public String amount,date;
    EditText name;
    TextView payment,txterror;
    Button pay;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_subscriber);
        payment = findViewById(R.id.payment_amount);
        name = findViewById(R.id.user_name);
        txterror = findViewById(R.id.txterror);
        pay = findViewById(R.id.btn_pay);
            Bundle bundle = getIntent().getExtras();
            if(bundle != null){
                amount=bundle.getString("payment").toString();
                date=bundle.getString("Date").toString();
                payment.setText(amount+"$");
            }

            pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String user= name.getText().toString();
                    PaymentSubscriber.Subscription s = new PaymentSubscriber.Subscription(PaymentSubscriber.this);
                    s.execute(date,amount,user);
                }
            });


    }
    class Subscription extends AsyncTask<String,Void,String>{
        String user_name;
        String payment_amount;
        String datesubs;

        Context ctx;

        public Subscription(Context context) {
            this.ctx = context;
        }

        @Override
        protected void onPreExecute() {
            pay.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... params) {
            datesubs = params[0];
            payment_amount = params[1];
            user_name = params[2];
            String login_url = "http://192.168.1.107/php_project/subscribe.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("datesub", "UTF-8") + "=" + URLEncoder.encode(datesubs, "UTF-8") +
                        "&" + URLEncoder.encode("amount", "UTF-8") + "=" + URLEncoder.encode(payment_amount, "UTF-8") +
                        "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8");
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
            pay.setEnabled(true);

            if(result.contains("all fields are required")) {
                txterror.setText("all fields are required");
            }
            else if(result.equals("username not found try again")){
                txterror.setText("username not found try again");}
            else{
                txterror.setText("user subscribed success");
            }
        }


    }
}

