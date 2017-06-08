package com.example.staffonechristian.fcm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateChannel extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolBar;
    private NavigationView mNavView;
    ChannelDataModel channelDataModel,channelOne;
    Button createChannel;
    EditText channelName;
    EditText channelDescription,userName;
    String creatorName,creatorEmailId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_channel);

        //Design part
        //Created by Anjali Desai
        mToolBar = (Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mToolBar);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.createChannel);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        channelOne = new ChannelDataModel();
        mNavView = (NavigationView)findViewById(R.id.my_nav_view);
        mNavView.setNavigationItemSelectedListener(CreateChannel.this);
        creatorName= channelOne.getCreatorName();
        creatorEmailId = channelOne.getCreatorEmailId();

        //Backend part
        //Created by Staffone Christian
        createChannel = (Button) findViewById(R.id.CreateChannelButton);
        channelName = (EditText) findViewById(R.id.ChannelNameEditText);
        channelDescription = (EditText) findViewById(R.id.ChannerlDescriptionEdittext);
        userName = (EditText) findViewById(R.id.username);

        createChannel.setEnabled(false);
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
        else if (id == R.id.sendAnnoun) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.chann) {
            Intent intent = new Intent(getApplicationContext(),CreateChannel.class);
            startActivity(intent);
        }
        else if(id==R.id.sub){

        }
        else if(id == R.id.logout){

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //Backend part
    //Created by Staffone Christian
    public void CreateChannel(View view) {

        channelDataModel = new ChannelDataModel(channelName.getText().toString(),channelDescription.getText().toString(),userName.getText().toString(),creatorName,creatorEmailId);
        DatabaseReference referenceWrite = FirebaseDatabase.getInstance().getReference();
        DatabaseReference drWrite = referenceWrite.child("ChannelSubscription").push();
        drWrite.setValue(channelDataModel);

    }

    public void CheckAvailability(View view) {
        createChannel.setEnabled(true);
    }
}
