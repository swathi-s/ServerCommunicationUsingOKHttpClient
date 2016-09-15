package com.example.user.servercommunicationusingokhttpclient;

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
        OkHttpClient client = new OkHttpClient();

        //For normal get method
        /*Request request = new Request.Builder()
                .url("http://192.168.1.7/androidService.php")
                .build();*/

        //for sending the query string to the get method
        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://192.168.1.7/androidService.php").newBuilder();
        urlBuilder.addQueryParameter("data","twenty");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            Log.d("response",response.body().toString());
        } catch (IOException e) {
            Toast.makeText(this, "exception is "+e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
