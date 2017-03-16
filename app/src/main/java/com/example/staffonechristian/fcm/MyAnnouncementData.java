package com.example.staffonechristian.fcm;

/**
 * Created by staffonechristian on 2017-03-15.
 */

public class MyAnnouncementData {
    private String Body;
    private String Title;
    private String Time;
    private int Id;
    private String From;

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }
}
