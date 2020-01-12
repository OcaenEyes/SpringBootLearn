package com.gzy.chatroom;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

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
        information.setMessage("再见哟~");

        //将下线消息发送给 未下线用户
        InformationOperationMap.map
                .get(this.roomId)
                .forEach((id, informationOperationMap) -> {
                    try {
                        informationOperationMap.send(information);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }


    /**
     * 处理http请求
     *
     * @param channelHandlerContext
     * @param request
     */
    private void handleHttpRequest(ChannelHandlerContext channelHandlerContext, FullHttpRequest request) throws Exception {
        //如果http解码失败，返回http异常
        if (!request.getDecoderResult().isSuccess() || (!"websocket".equals(request.headers().get("Upgrade")))) {
            sendHttpResponse(channelHandlerContext, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        //正常websocket 的http连接请求， 构造握手响应返回
        WebSocketServerHandshakerFactory webSocketServerHandshakerFactory = new WebSocketServerHandshakerFactory(
                "ws://" + request.headers().get(HttpHeaders.Names.HOST), null, false);
        webSocketServerHandshaker = webSocketServerHandshakerFactory.newHandshaker(request);
        if (webSocketServerHandshaker == null) { //无法处理的 websocket版本
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(channelHandlerContext.channel());
        } else {// 向客户端发送 websocket握手， 完成握手
            webSocketServerHandshaker.handshake(channelHandlerContext.channel(), request);
            //记录管道处理上下文， 便于服务器推送数据到客户端的
            this.channelHandlerContext = channelHandlerContext;
        }
    }


    private static void sendHttpResponse(ChannelHandlerContext channelHandlerContext, FullHttpRequest request, FullHttpResponse response) throws Exception {
        // 返回应答给客户端
        if (response.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(response.getStatus().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(buf);
            buf.release();
            HttpHeaders.setContentLength(response, response.content().readableBytes());
        }

        // 如果是 非keep-Alive 关闭连接
        ChannelFuture future = channelHandlerContext.writeAndFlush(response);
        if (!HttpHeaders.isKeepAlive(request) || response.getStatus().code() != 200) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 处理socket请求
     *
     * @param channelHandlerContext
     * @param webSocketFrame
     * @throws Exception
     */
    private void handleWebSocketFrame(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame) throws Exception {
        // 判断是否涉及关闭链路的指令
        if (webSocketFrame instanceof CloseWebSocketFrame) {
            webSocketServerHandshaker.close(
                    channelHandlerContext.channel(),
                    ((CloseWebSocketFrame) webSocketFrame).retain());
            return;
        }

        //判断是否ping消息
        if (webSocketFrame instanceof PingWebSocketFrame){
            channelHandlerContext.channel().write(new PongWebSocketFrame(webSocketFrame.content().retain()));
            return;
        }

        // 当前只支持文本信息 ，不支持二进制
        if (webSocketFrame instanceof  TextWebSocketFrame){
            //获取发来的消息
            String text = ((TextWebSocketFrame) webSocketFrame).text();
            System.out.println("information:"+text);

            //消息转换为Information
            Information information = Information.strJson2Information(text);
            //判断是否已存在用户信息
            assert information != null;
            if (InformationOperationMap.isUser(information)){
                //判断是否有这个聊天室
                if (InformationOperationMap.map.containsKey(information.getRoomId())){
                    //判断是否有其他用户
                    if (InformationOperationMap.map.get(information.getRoomId()).size() > 0){
                        InformationOperationMap.map.get(information.getRoomId()).forEach((id,informationOperationMap) ->{
                            try {
                                Information information1 = informationOperationMap.getInformation();
                                information1.setMessage("我来啦~");
                                this.sendWebSocket(information1.infoJson());
                            }catch (Exception e){
                               e.printStackTrace();
                            }
                        });
                    }
                }
                //添加用户
                InformationOperationMap.add(channelHandlerContext,information);
                System.out.println("add:" + information.infoJson());
            }
            //将用户发送的消息 发给所有同在一个聊天室内的用户
            InformationOperationMap.map.get(information.getRoomId()).forEach((id,informationOperationMap) ->{
                try {
                    informationOperationMap.send(information);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });

            //记录useId 和roomId 当页面刷新活浏览器关闭时，注销此链路
            this.sessionId =information.getUserId();
            this.roomId = information.getRoomId();
            this.name = information.getUserName();
        }else {
            System.err.println("--------------error-----------");
        }
    }

    // websocket 返回
    public void sendWebSocket(String msg) throws Exception {
        if (this.webSocketServerHandshaker == null || this.channelHandlerContext ==null || this.channelHandlerContext.isRemoved()){
            throw new Exception("尚未握手成功，无法发送websocket消息");
        }
        //发送消息
        this.channelHandlerContext.channel().write(new TextWebSocketFrame(msg));
        this.channelHandlerContext.flush();
    }

}
