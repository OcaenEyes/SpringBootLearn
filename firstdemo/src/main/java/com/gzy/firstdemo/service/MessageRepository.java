package com.gzy.firstdemo.service;

import com.gzy.firstdemo.entity.Message;

import java.util.List;

public interface MessageRepository {

    List<Message> finaAll();

    Message save(Message message);

    Message update(Message message);

    Message updateText(Message message);
    //根据ID查找消息
    Message findMessage(Long id);
    // 根据ID删除消息
    void deleteMessage(Long id);
}
