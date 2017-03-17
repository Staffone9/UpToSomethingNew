package com.example.staffonechristian.fcm;

import java.io.Serializable;

/**
 * Created by staffonechristian on 2017-03-16.
 */

public class LatestDataModel implements Serializable {
    private String Body;
    private String From;
    private int Id;
    private String Time;
    private String Title;

    public LatestDataModel(String body, String from, int id, String time, String title) {
        Body = body;
        From = from;
        Id = id;
        Time = time;
        Title = title;
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

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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
