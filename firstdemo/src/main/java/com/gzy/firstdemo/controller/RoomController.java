package com.gzy.firstdemo.controller;

import com.gzy.firstdemo.entity.Room;
import com.gzy.firstdemo.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RoomController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Room room = new Room();

    @Autowired
    private RoomService roomService;

    @RequestMapping("/chatroom")
    public String chat() {
        return "chatroom";
    }

    @RequestMapping("/chat/{room}")
    public String chatroom(@PathVariable("room") Long id) {
        if (id == null || id.equals("")) {
            return "";
        }

        Room existRoom = new Room();
//        logger.error(String.valueOf(id));
        existRoom = this.roomService.get(id);
//        logger.error("打印查到的结果" + String.valueOf(existRoom));
        if (existRoom == null) {
            room.setRoomId(id);
            this.roomService.save(room);
//            logger.error("打印保存的结果" + String.valueOf(room));
        }
        return "chat";

    }

    @RequestMapping("/getRoom/{id}")
    @ResponseBody
    public Room get(@PathVariable("id") Long id) {
        Room room = this.roomService.get(id);
        logger.info("根据房间号" + id + "取到信息了");
        return room;
    }

    @RequestMapping("/saveRoom")
    @ResponseBody
    public Room save(Room room) {
        room = this.roomService.save(room);
        logger.info("保存房间" + room.getId() + "信息成功了");
        return room;
    }

}
