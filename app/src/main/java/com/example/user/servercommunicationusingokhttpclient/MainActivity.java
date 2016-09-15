package com.example.user.servercommunicationusingokhttpclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendRequest(View view) {
        new GetDataFromServer().execute();
    }

    class GetDataFromServer extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] params) {
            OkHttpClient client = new OkHttpClient();

            //For normal get method
        /*Request request = new Request.Builder()
                .url("http://192.168.1.7/androidService.php")
                .build();*/
            Log.d("response","okHttpClient instance is created");
            //for sending the query string to the get method
            HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.github.help").newBuilder();
            urlBuilder.addQueryParameter("v","1.0");
            urlBuilder.addQueryParameter("user","vogella");
            String url = urlBuilder.build().toString();

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                Toast.makeText(getApplicationContext(),"response got",Toast.LENGTH_SHORT).show();
                Log.d("response",response.body().toString());

            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),"exception is "+e,Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            return null;
        }
    }
}
