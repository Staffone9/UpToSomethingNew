package com.example.staffonechristian.fcm;


import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

    public void ReadData()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dr = reference.child("Announcement");
        final DatabaseReference body = dr.child("Body");
        dr.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.e(TAG, "=======On child added"+dataSnapshot.getKey());
                switch (dataSnapshot.getKey())
                {
                    case "Title":
                    {
                       // nameArray[0]=dataSnapshot.getValue(String.class);
                        if(bodyList.contains(dataSnapshot.getValue(String.class)))
                        {

                        }else {
                            bodyList.add(dataSnapshot.getValue(String.class));
                        }


                        break;
                    }
                    case  "Body":
                    {
                        if(titleList.contains(dataSnapshot.getValue(String.class)))
                        {

                        }else{
                            titleList.add(dataSnapshot.getValue(String.class));
                        }

                        break;
                    }
                    case  "From":
                    {

                    }
                    case "Id":
                    {


                    }
                    case "Time":
                    {


                    }
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.e(TAG, "=======On child changed");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.e(TAG, "=======OnData Changed"+dataSnapshot.child("Title").getValue().toString()+"number of child"+dataSnapshot.getChildren());

                for(DataSnapshot data : dataSnapshot.getChildren()){

                 //   MyAnnouncementData myAnnouncementData = data.getValue(MyAnnouncementData.class);

                    Log.e(TAG, "=======kkk"+data.child("Body").getValue(String.class));
                    // use this object and store it into an ArrayList<Template> to use it further

                }
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    nameArray[0]= postSnapshot.child("Title").getValue().toString();
//                    Log.e(TAG, "======="+postSnapshot.child("Body").getValue());
//                    Log.e(TAG, "======="+postSnapshot.child("From").getValue());
//                    Log.e(TAG, "======="+postSnapshot.child("Id").getValue());
//                    Log.e(TAG, "======="+postSnapshot.child("Time").getValue());
//                    Log.e(TAG, "======="+postSnapshot.child("Title").getValue());
//
//                }
//                body.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        nameArray[0] = dataSnapshot.getValue(String.class);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
