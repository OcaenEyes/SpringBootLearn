package com.gzy.firstdemo.entity;

import java.util.Date;

public class User {
    private long id;
    private String name;
    private Date currentTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public User() {
        super();
    }

    public User(Long id,String name,Date date){
        this.id= id;
        this.name = name;
        this.

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currentTime=" + currentTime +
                '}';
    }
}
