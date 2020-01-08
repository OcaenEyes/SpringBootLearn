package com.gzy.firstdemo.service;


import com.gzy.firstdemo.entity.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Service("messageRepository")
public class InMemoryMessageRepository implements MessageRepository{
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, Message> messages = new ConcurrentHashMap<>();

    @Override
    public List<Message> finaAll() {
        List<Message> messages = new ArrayList<Message>(
                this.messages.values()
        );
        return messages;
    }

    @Override
    public Message save(Message message) {
        Long id = message.getId();
        if (id == null){
            id = counter.incrementAndGet();
            message.setId(id);
        }
        this.messages.put(id,message);
        return message;
    }

    @Override
    public Message update(Message message) {
        this.messages.put(message.getId(),message);
        return message;
    }

    @Override
    public Message updateText(Message message) {
        Message message1 = this.messages.get(message.getId());
        message1.setText(message.getText());
        this.messages.put(message1.getId(),message1);
        return message1;
    }

    @Override
    public Message findMessage(Long id) {
        return this.messages.get(id);
    }

    @Override
    public void deleteMessage(Long id) {
        this.messages.remove(id);
    }
}
