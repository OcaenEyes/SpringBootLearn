package com.gzy.firstdemo.entity;

import java.util.Date;

public class TalkMessage {
    private String content;
    private Date date;
    private String userId;
    private String roomId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "TalkMessage{" +
                "content='" + content + '\'' +
                ", date=" + date +
                ", userId='" + userId + '\'' +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}
