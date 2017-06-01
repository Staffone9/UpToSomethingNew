package com.example.staffonechristian.fcm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateChannel extends AppCompatActivity {

    Button createChannel;
    EditText channelName;
    EditText channelDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_channel);

        createChannel = (Button) findViewById(R.id.CreateChannelButton);
        channelName = (EditText) findViewById(R.id.ChannelNameEditText);
        channelDescription = (EditText) findViewById(R.id.ChannerlDescriptionEdittext);
    }

    public void CreateChannel(View view) {


    }
}
