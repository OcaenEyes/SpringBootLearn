package com.gzy.firstdemo.entity;

public class User {
    private int id;
    private  long userId;
    private String userName;
    private long userRoomId;

    public User() {
        super();
    }

    public User(long userId) {
        this.userId = userId;
    }

    public User(long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public User(long userId, long userRoomId) {
        this.userId = userId;
        this.userRoomId = userRoomId;
    }

    public User(long userId, String userName, long userRoomId) {
        this.userId = userId;
        this.userName = userName;
        this.userRoomId = userRoomId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserRoomId() {
        return userRoomId;
    }

    public void setUserRoomId(long userRoomId) {
        this.userRoomId = userRoomId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userRoomId=" + userRoomId +
                '}';
    }
}
