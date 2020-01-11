package com.gzy.firstdemo.entity;

public class Room {
    private int id;
    private long roomId;
    private String roomName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                '}';
    }
}
