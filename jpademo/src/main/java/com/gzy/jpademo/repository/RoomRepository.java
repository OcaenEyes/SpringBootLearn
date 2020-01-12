package com.gzy.jpademo.repository;

import com.gzy.jpademo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Integer> {

}
