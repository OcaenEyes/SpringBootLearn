package com.gzy.firstdemo.controller;

import com.gzy.firstdemo.utils.WebSocketUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;

import static com.gzy.firstdemo.utils.WebSocketUtils.ONLINE_USER_SESSIONS;
import static com.gzy.firstdemo.utils.WebSocketUtils.*;

@RestController
@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndpoint {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @OnOpen
    public void openSession(@PathParam("username") String username, Session session){
        ONLINE_USER_SESSIONS.put(username,session);

        String message = "欢迎用户【" +username +"】来到聊天室！";
        logger.info("用户登录："+message);
        sendMessageAll(message);
    }

    @OnMessage
    public void onMessage(@PathParam("username") String username,String message){
        logger.info("发送信息："+username);
        sendMessageAll("【"+ username +"】："+message);
    }

    @OnClose
    public void onClose(@PathParam("username") String username,Session session){
        //当前的session被移除
        ONLINE_USER_SESSIONS.remove(username);
        //通知其他人当前用户已离开
        sendMessageAll("用户【"+username +"】已经离开聊天室");
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
