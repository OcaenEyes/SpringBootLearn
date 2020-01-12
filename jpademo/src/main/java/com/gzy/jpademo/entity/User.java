package com.gzy.jpademo.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tk_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id", length = 20)
    private Long userId;
    @Column(name = "user_name", length = 50)
    private String userName;

    @ManyToOne(targetEntity = Room.class)
    @JoinColumn(name = "user_room_id", referencedColumnName = "room_id")
    private Room room;

    public User() {
    }

    public User(Room room) {
        this.room = room;
    }

    public User(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public User(Long userId, String userName, Room room) {
        this.userId = userId;
        this.userName = userName;
        this.room = room;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", room=" + room +
                '}';
    }
}
