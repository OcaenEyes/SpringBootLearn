package com.gzy.chatroom;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InformationOperationMap {

    public static ConcurrentMap<String, ConcurrentMap<String, InformationOperationMap>> map = new ConcurrentHashMap<>();
    private Information information;
    private ChannelHandlerContext channelHandlerContext;

    private InformationOperationMap(Information information, ChannelHandlerContext channelHandlerContext) {
        this.information = information;
        this.channelHandlerContext = channelHandlerContext;
    }

    /**
     * 添加用户信息
     *
     * @param ctx
     * @param information
     */
    public static void add(ChannelHandlerContext ctx, Information information) {
        InformationOperationMap informationOperationMap = new InformationOperationMap(information, ctx);
        ConcurrentMap<String, InformationOperationMap> concurrentMap = new ConcurrentHashMap<>();
        if (map.containsKey(information.getRoomId())) {
            map.get(information.getRoomId()).put(information.getUserId(), informationOperationMap);
        } else {
            concurrentMap.put(information.getUserId(), informationOperationMap);
            map.put(information.getRoomId(), concurrentMap);
        }
    }

    /**
     * 删除用户信息
     *
     * @param userId
     * @param roomId
     */
    public static void delete(String userId, String roomId) {
        ConcurrentMap<String, InformationOperationMap> concurrentMap = map.get(roomId);
        if (concurrentMap.size() <= 1) {
            map.remove(roomId);
        } else {
            concurrentMap.remove(userId);
        }
    }

    /**
     * 判断是否存在有用户
     *
     * @param information
     * @return 存在为false
     */
    public static boolean isUser(Information information) {
//        return map.containsKey(information.getRoomId()) ? map.get(information.getRoomId()).containsKey(information.getUserId()) ? false : true : true;
        return !map.containsKey(information.getRoomId()) || (!map.get(information.getRoomId()).containsKey(information.getUserId()));
    }

    /**
     * 给用户发送消息
     *
     * @param information
     * @throws Exception
     */
    public void send(Information information) throws Exception {
        channelHandlerContext.writeAndFlush(new TextWebSocketFrame(information.infoJson()));
    }

    public Information getInformation() {
        return information;
    }
}
