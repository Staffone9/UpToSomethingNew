package com.example.staffonechristian.fcm;

import java.io.Serializable;

/**
 * Created by staffonechristian on 2017-03-16.
 */

public class LatestDataModel implements Serializable {
    private String Body;
    private String From;

    private String Time;
    private String Title;
    private static String emailId;


    public LatestDataModel(String body, String from, String time, String title) {
        Body = body;
        From = from;

        Time = time;
        Title = title;
    }
    public LatestDataModel()
    {

    }
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }


    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
