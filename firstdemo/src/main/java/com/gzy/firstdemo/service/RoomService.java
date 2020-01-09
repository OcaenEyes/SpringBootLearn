package com.gzy.firstdemo.service;

import com.gzy.firstdemo.entity.Room;

public interface RoomService {
    Room save(Room room);
    Room get(Long id);
}
