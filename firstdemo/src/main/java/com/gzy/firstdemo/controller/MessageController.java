package com.gzy.firstdemo.controller;

import com.gzy.firstdemo.entity.Message;
import com.gzy.firstdemo.service.MessageRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "消息操作APi")
@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    //获取所有消息
    @ApiOperation(
            value = "消息列表",
            notes = "完整的消息列表",
            produces = "application/json,application/xml",
            consumes = "application/json,application/xml",
            response = List.class
    )
    @GetMapping("/findAll")
    public List<Message> list(){
        List<Message> messages = this.messageRepository.finaAll();
        return messages;
    }

    //创建一个消息
    @PostMapping("/save")
    public Message create(Message message){
         message = this.messageRepository.save(message);
         return message;
    }

    //使用put请求 更新消息
    @PutMapping("/update")
    public Message update(Message message){
        Message messageResult = this.messageRepository.update(message);
        return messageResult;
    }

    // 更新消息的text字段
    @PatchMapping("/updateText")
    public Message updateText(Message message){
        Message messageResult = this.messageRepository.updateText(message);
        return messageResult;
    }


    //获取某个信息
    @GetMapping("/get/{id}")
    public Message get(@PathVariable("id") Long id){
        Message message = this.messageRepository.findMessage(id);
        return message;
    }

    //删除某个信息
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        this.messageRepository.deleteMessage(id);
    }


}
