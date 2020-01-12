package com.gzy.jpademo.controller;

import com.gzy.jpademo.entity.User;
import com.gzy.jpademo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;

import static com.gzy.jpademo.utils.WebSocketUtils.*;

@RestController
@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndpoint {
    private Logger logger = LoggerFactory.getLogger(getClass());
    // 存放每个客户端对应的websoket对象
    private static CopyOnWriteArraySet<ChatRoomServerEndpoint> chatRoomServerEndpoints = new CopyOnWriteArraySet<ChatRoomServerEndpoint>();
    // 用session作为key保存用户信息
    private static Map<Session,User> sessionUserMap = new HashMap<>();

    private Session session;

    @Resource
    private UserRepository userRepository;

    @OnOpen
    public void openSession(@PathParam("username") String username, Session session){
        this.session = session;
        User user = new User(Long.parseLong(session.getId()),username);
        sessionUserMap.put(session,user);
        chatRoomServerEndpoints.add(this);
        ONLINE_USER_SESSIONS.put(username,session);
//        userRepository.save(user);
        String message = "欢迎用户【" +username +"】来到聊天室！";
        logger.info(message);
        sendMessageAll(message+"\n"+"当前在线人数"+chatRoomServerEndpoints.size());
    }

    @OnMessage
    public void onMessage(@PathParam("username") String username,String message){
        logger.info(username+"发送信息："+message);
        sendMessageAll("【"+ username +"】："+message);
    }

    @OnClose
    public void onClose(@PathParam("username") String username,Session session){
        //当前的session被移除
        ONLINE_USER_SESSIONS.remove(username);
        sessionUserMap.remove(session);
        chatRoomServerEndpoints.remove(this);
        //通知其他人当前用户已离开
        sendMessageAll("用户【"+username +"】已经离开聊天室!" +"\n"+"当前在线人数"+chatRoomServerEndpoints.size());
        try {
            session.close();
        }
        catch (IOException e){
            logger.error("onClose error",e);

        }
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        try {
            session.close();
        }catch (IOException e){
            logger.error("onError exception",e);
        }
        logger.info("Throwable msg" + throwable.getMessage());

    }
}
