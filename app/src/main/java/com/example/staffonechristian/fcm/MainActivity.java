package com.example.staffonechristian.fcm;

import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolBar;
    private NavigationView mNavView;

    EditText titleText;
    EditText DetailText;
    Button sendButton,logOutButton;
    MyData myData;
    LatestDataModel latestDataModel;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Design part
        //Created by Anjali Desai
        mToolBar = (Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mToolBar);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.activity_main);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNavView = (NavigationView)findViewById(R.id.my_nav_view);
        mNavView.setNavigationItemSelectedListener(MainActivity.this);

        //Backend part
        //Created by Staffone Christian
        latestDataModel = new LatestDataModel();
        FirebaseMessaging.getInstance().subscribeToTopic("MSG");
        //because of network exception
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        myData = new MyData();
        auth = FirebaseAuth.getInstance();
        //myData.CreateData();
        myData.CreatUser(latestDataModel.getEmailId());
        titleText = (EditText) findViewById(R.id.titleId);
        DetailText = (EditText) findViewById(R.id.detailId);
        sendButton = (Button) findViewById(R.id.btnSend);
        System.out.println("MainActivity.onCreate: " + FirebaseInstanceId.getInstance().getToken());

        myData.ReadDataNew();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null)
                {
                    startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                }
            }
        };
    }

    //Design part
    //Created by Anjali Desai
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.announ) {
            Intent intent = new Intent(getApplicationContext(),Announcements.class);
            startActivity(intent);
        }
        else if (id == R.id.chann) {
            Intent intent = new Intent(getApplicationContext(),CreateChannel.class);
            startActivity(intent);

        }
        else if (id == R.id.sendAnnoun) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.logout) {
            auth.signOut();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //Backend part
    //Created by Staffone Christian
    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(mAuthListener);
    }

    public static String makeRequest(String title, String Detail) throws JSONException {
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
        Intent myIntent = new Intent(MainActivity.this, Announcements.class);
        startActivity(myIntent);
    }

   /* public void LogoutMethod(View view) {
        auth.signOut();
    }*/

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
