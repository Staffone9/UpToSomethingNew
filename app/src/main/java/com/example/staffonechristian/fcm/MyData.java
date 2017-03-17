package com.example.staffonechristian.fcm;


import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static com.google.android.gms.internal.zzt.TAG;

public class MyData {

//    DatabaseReference databaseReference =  FirebaseDatabase.getInstance().getReference();
//    DatabaseReference sub = databaseReference.child("SubscriptionName");
//    DatabaseReference Id = databaseReference.child("ID1");



//    DatabaseReference body = dr.child("Body");
//    DatabaseReference from = dr.child("From");
//    DatabaseReference id = dr.child("Id");
//    DatabaseReference Time = dr.child("Time");
//    DatabaseReference title = dr.child("Title");

    static String[] nameArray = {"Jean Fillion", "Head", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich","JellyBean", "Kitkat", "Lollipop", "Marshmallow"};
    static String[] versionArray = {"Lecture canceled", "New Event", "2.0-2.1", "2.2-2.2.3", "2.3-2.3.7", "3.0-3.2.6", "4.0-4.0.4", "4.1-4.3.1", "4.4-4.4.4", "5.0-5.1.1","6.0-6.0.1"};
    static List bodyList = new ArrayList();
    static List titleList = new ArrayList();
    static List fromList = new ArrayList();
    static List idList = new ArrayList();
    static List timeList = new ArrayList();
    static Integer[] drawableArray = {R.drawable.conestoga, R.drawable.csi, R.drawable.eclair,
            R.drawable.froyo, R.drawable.gingerbread, R.drawable.honeycomb, R.drawable.ics,
            R.drawable.jellybean, R.drawable.kitkat, R.drawable.lollipop,R.drawable.marsh};

    static Integer[] id_ = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dr = reference.child("Announcement");
    public void CreateData()
    {

        LatestDataModel latestDataModel = new LatestDataModel("New New","Dalibor123",3,"7 AM","khaber nahi Bhai");
//        latestDataModel.setBody("New Body of message");
//        latestDataModel.setFrom("Dalibor");
//        latestDataModel.setId("2");
//        latestDataModel.setTime("6 AM");
//        latestDataModel.setTitle("Bhai bhai");
        DatabaseReference referenceWrite = FirebaseDatabase.getInstance().getReference();
        DatabaseReference drWrite = referenceWrite.child("Announcement").push();
        drWrite.setValue(latestDataModel);


    }
    public void ReadDataNew()
    {
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "=======>>>>>dataSnapshot.getValue()"+dataSnapshot.getValue());
                for (DataSnapshot individual : dataSnapshot.getChildren()) {
                    String Body = individual.child("body").getValue(String.class);
                    String From = individual.child("from").getValue(String.class);
                    String Time = individual.child("time").getValue(String.class);
                    int Id = individual.child("id").getValue(Integer.class);
                    String Title = individual.child("title").getValue(String.class);
                    Log.e(TAG, "=======>>>>>boss stage body"+Body);
                    if(bodyList.contains(Body))
                        {

                        }else {
                            bodyList.add(Body);
                        }
                    if(titleList.contains(Title))
                        {

                        }else{
                            titleList.add(Title);
                        }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }




}
