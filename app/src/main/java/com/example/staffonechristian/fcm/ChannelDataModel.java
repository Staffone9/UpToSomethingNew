package com.example.staffonechristian.fcm;

/**
 * Created by staffonechristian on 2017-06-08.
 */

public class ChannelDataModel {

    private String channelName;
    private String description;
    private String uniqueUserName;
    private String creatorName;
    private String creatorEmailId;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUniqueUserName() {
        return uniqueUserName;
    }

    public void setUniqueUserName(String uniqueUserName) {
        this.uniqueUserName = uniqueUserName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorEmailId() {
        return creatorEmailId;
    }

    public void setCreatorEmailId(String creatorEmailId) {
        this.creatorEmailId = creatorEmailId;
    }
}
