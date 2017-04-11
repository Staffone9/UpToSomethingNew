package com.example.staffonechristian.fcm;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Send_Notifications extends AppCompatActivity {


    EditText titleText;
    EditText DetailText;
    Button sendButton;
    MyData myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notifications);
        //Intent intent = new Intent(Send_Notifications.this,SignInActivity.class);
        //startActivity(intent);
        FirebaseMessaging.getInstance().subscribeToTopic("MSG");
        //because of network exception
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        myData = new MyData();
//        myData.CreateData();

        titleText = (EditText) findViewById(R.id.titleId);
        DetailText = (EditText) findViewById(R.id.detailId);
        sendButton = (Button) findViewById(R.id.btnSend);
        System.out.println("Send_Notifications.onCreate: " + FirebaseInstanceId.getInstance().getToken());

        myData.ReadDataNew();

    }


    public static String makeRequest(String title,String Detail) throws JSONException {
        HttpURLConnection urlConnection;
        JSONObject json = new JSONObject();
        JSONObject info = new JSONObject();
        info.put("title", title );   // Notification title
        info.put("body", Detail); // Notification body
        info.put("sound", "default"); // Notification sound
        json.put("notification", info);
        json.put("to","/topics/MSG");

        Log.e("jsonn==> ",json.toString());
        String data = json.toString();
        String result = "kaipan";
        try {
            //Connect
            urlConnection = (HttpURLConnection) ((new URL("https://fcm.googleapis.com/fcm/send").openConnection()));
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Authorization", "key=AIzaSyAxZioY3tZ6RDHm40Y2gwD6bKZVjVmch0A");
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            //Write
            OutputStream outputStream = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(data);
            writer.close();
            outputStream.close();

            //Read
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            bufferedReader.close();
            result = sb.toString();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void SendNotification(View view) {
        try {
            MyData myData = new MyData();
            myData.CreateData(titleText.getText().toString(),DetailText.getText().toString());
            makeRequest(titleText.getText().toString(),DetailText.getText().toString());
            //  Toast.makeText(this,makeRequest(1+""),Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void OpenAnnouncement(View view) {

        Intent myIntent = new Intent(Send_Notifications.this,
                Announcements.class);
        startActivity(myIntent);
    }


}