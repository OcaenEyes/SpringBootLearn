package com.gzy.firstdemo.service;

import com.gzy.firstdemo.entity.Room;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Service("roomService")
public class RoomServiceImpl implements RoomService {
    private AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long,Room> rooms = new ConcurrentHashMap<>();

    @Override
    public Room save(Room room) {
        Long roomId = room.getRoomId();
        if (roomId == null){
            roomId = counter.incrementAndGet();
            room.setRoomId(roomId);
        }
        return this.rooms.put(roomId,room);
    }

    @Override
    public Room get(Long roomId) {
        return this.rooms.get(roomId);
    }
}
