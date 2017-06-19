package com.example.staffonechristian.fcm;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.google.android.gms.internal.zzt.TAG;

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
    SharedPreferences sharedPreferences;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener mAuthListener;
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
        auth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("SignIn",MODE_PRIVATE);
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
            auth.signOut();
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(mAuthListener);
    }

    //Backend part
    //Created by Staffone Christian
    public void CreateChannel(View view) {

        channelDataModel = new ChannelDataModel(channelName.getText().toString(),channelDescription.getText().toString(),userName.getText().toString(),sharedPreferences.getString("creatorName",null),sharedPreferences.getString("emailID",null));
        DatabaseReference referenceWrite = FirebaseDatabase.getInstance().getReference();
        DatabaseReference drWrite = referenceWrite.child("ChannelSubscription").push();
        drWrite.setValue(channelDataModel);
        Toast.makeText(getApplicationContext(),"Channel Created Successfully",Toast.LENGTH_SHORT).show();
        createChannel.setEnabled(false);

    }

    public void CheckAvailability(View view) {

        DatabaseReference referenceWrite = FirebaseDatabase.getInstance().getReference();
        referenceWrite.child("ChannelSubscription").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean flag=true;
                for (DataSnapshot individual : dataSnapshot.getChildren()) {
                    String userName1 = individual.child("uniqueUserName").getValue(String.class);
                    if(userName1.equals(userName.getText().toString()))
                    {
                        flag=false;
                    }else{

                    }
                }
                if(flag)
                {
                    Toast.makeText(getApplicationContext(),"User name is available",Toast.LENGTH_SHORT).show();
                    createChannel.setEnabled(true);
                }else{

                    Toast.makeText(getApplicationContext(),"User name is not available please choose another one",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//        drWrite.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot data: dataSnapshot.getChildren()){
//                    if (data.child(userName.getText().toString()).exists()) {
//                        Toast.makeText(getApplicationContext(),"Exist che bhai",Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getApplicationContext(),"Nathi bhai",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }
}
