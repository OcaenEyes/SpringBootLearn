package com.gzy.firstdemo.entity;

import javax.persistence.*;

@Entity
@Table(name = "tk_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id",length = 20)
    private  Long userId;
    @Column(name = "user_name",length = 50)
    private String userName;
    @Column(name = "user_room_id",length = 20)
    private Long userRoomId;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserRoomId() {
        return userRoomId;
    }

    public void setUserRoomId(Long userRoomId) {
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
