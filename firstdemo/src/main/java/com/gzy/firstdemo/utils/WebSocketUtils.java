package com.gzy.firstdemo.utils;

import jdk.nashorn.internal.runtime.logging.DebugLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketUtils {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static final Map<String, Session> ONLINE_USER_SESSIONS = new ConcurrentHashMap<>();

    public void sendMessage(Session session,String message){
        if (session == null){
            return;
        }
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null){
            return;
        }
        try {
            basic.sendText(message);
        }catch (IOException e){
            logger.error("sendMessage IOException",e);
        }
    }


    public void sendMessageAll(String message){
        ONLINE_USER_SESSIONS.forEach((sessionId, session) -> sendMessage(session,message));

    }

}
