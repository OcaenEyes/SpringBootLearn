package com.gzy.firstdemo.entity;

import javax.persistence.*;

//使用JPA注解配置
//告诉JPA这是一个实体类 （和数据表映射的类）
@Entity
// @Table 指定与哪个表对应，如果省略则默认为user
@Table(name = "tk_room")

public class Room {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增逐渐
    private Integer id;

    @Column(name = "room_id",length = 20)
    private Long roomId;
    @Column(name = "room_name",length = 50)
    private String roomName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
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
