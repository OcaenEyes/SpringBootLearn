package com.gzy.chatroom;

import io.netty.channel.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;

//https://blog.csdn.net/qq_36994771/article/details/80085876
//https://blog.csdn.net/qq_36994771/article/details/80086145
public class SocketHandller extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker webSocketServerHandshaker;
    private ChannelHandlerContext channelHandlerContext;
    private String sessionId;
    private String roomId;
    private String name;

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        if (o instanceof FullHttpRequest) { // 传统的HTTP接入
            handleHttpRequest(channelHandlerContext, (FullHttpRequest) o);
        } else if (o instanceof WebSocketFrame) { // WebSocket接入
            handleWebSocketFrame(channelHandlerContext, (WebSocketFrame) o);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        super.close(ctx, promise);
        System.out.println("delete:id = " + this.sessionId + "room=" + this.roomId);
        //关闭连接 并移除用户信息
        InformationOperationMap.delete(this.sessionId, this.roomId);
        Information information = new Information();
        information.setUserName(this.name);
        information.getMessage();

        //将下线消息发送给 未下线用户
        InformationOperationMap.map
                .get(this.roomId)
                .forEach((id, informationOperationMap) -> {
                    try {
                        informationOperationMap.send(information);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                });
    }

    /**
     * 处理socket请求
     *
     * @param channelHandlerContext
     * @param o
     * @throws Exception
     */
    private void handleWebSocketFrame(ChannelHandlerContext channelHandlerContext, WebSocketFrame o) throws Exception{

    }

    /**
     * 处理http请求
     *
     * @param channelHandlerContext
     * @param o
     */
    private void handleHttpRequest(ChannelHandlerContext channelHandlerContext, FullHttpRequest o) throws Exception{
        //如果http解码失败，返回http异常

    }
}
