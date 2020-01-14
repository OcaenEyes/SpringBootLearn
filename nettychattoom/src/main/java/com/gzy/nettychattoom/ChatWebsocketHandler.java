package com.gzy.nettychattoom;

import com.gzy.nettychattoom.config.NettyConfig;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 接收 / 处理 / 响应客户端 websocket请求的核心业务类
 */
@ChannelHandler.Sharable
public class ChatWebsocketHandler extends SimpleChannelInboundHandler<Object> {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private WebSocketServerHandshaker webSocketServerHandshaker;
    private static final String WEB_SOCKET_URL = "ws://localhost:8888";
    private ChannelHandlerContext channelHandlerContext;
    private String userId;
    private String roomId;
    private String userName;

    /**
     * 客户端与服务端建立连接的时候调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.add(ctx.channel());
        logger.info("客户端与服务端连接开启！");
    }

    /**
     * 客户端与 服务端断开连接的时候调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.remove(ctx.channel());
        logger.info("客户端与服务端连接关闭...");
    }

    /**
     * 服务端接收 客户端发送过来的数据结束之后调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 工程出现异常的时候调用
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        logger.error(String.valueOf(cause));
        ctx.close();
    }

    /**
     * 服务端处理客户端websocket请求的核心方法
     *
     * @param channelHandlerContext
     * @param o
     * @throws Exception
     */
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        logger.info("接到服务请求");
        //处理客户端向服务端发起的 http握手请求业务
        if (o instanceof FullHttpRequest) {
            logger.info("这是http请求");
            handHttpRequest(channelHandlerContext, (FullHttpRequest) o);
        }
        // 处理客户端向服务端发起的 websocket 请求
        else if (o instanceof WebSocketFrame) {
            logger.info("这是websocket 请求");
            handWebSocketFrame(channelHandlerContext, (WebSocketFrame) o);
        }
    }

    /**
     * 处理客户端向服务端发起的http请求的业务
     *
     * @param ctx
     * @param request
     */
    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        if (!request.getDecoderResult().isSuccess() || ("websocket").equals(request.headers().get("Upgrade"))) {
            sendHttpResponse(
                    ctx,
                    request,
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST)
            );
            return;
        }

//        WebSocketServerHandshakerFactory webSocketServerHandshakerFactory = new WebSocketServerHandshakerFactory("ws://" + request.headers().get(HttpHeaders.Names.HOST), null, false);
        WebSocketServerHandshakerFactory webSocketServerHandshakerFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL, null, false);

        webSocketServerHandshaker = webSocketServerHandshakerFactory.newHandshaker(request);

        if (webSocketServerHandshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        } else {
            webSocketServerHandshaker.handshake(ctx.channel(), request);

            // 记录管道处理上下文，便于服务器推送数据道客户端
            this.channelHandlerContext = ctx;
        }
    }

    /**
     * 服务端向客户端响应 http消息
     *
     * @param ctx
     * @param request
     * @param response
     */
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, DefaultFullHttpResponse response) {
        //返回应用给客户端
        if (response.getStatus().code() == 200) {
            ByteBuf byteBuf = Unpooled.copiedBuffer(response.getStatus().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(byteBuf);
            byteBuf.release();
            HttpHeaders.setContentLength(response, response.content().readableBytes());
        }

        // 如果是非 keep-alive 关闭连接
        ChannelFuture channelFuture = ctx.channel().writeAndFlush(response);
        if (!HttpHeaders.isKeepAlive(request) || (response.getStatus().code() != 200)) {
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }


    /**
     * 处理客户端与服务端之前的 websocket 业务
     *
     * @param ctx
     * @param webSocketFrame
     */
    private void handWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame webSocketFrame) throws Exception {
        // 判断是否是关闭websocket的指令
        if (webSocketFrame instanceof CloseWebSocketFrame) {
            webSocketServerHandshaker.close(ctx.channel(), ((CloseWebSocketFrame) webSocketFrame).retain());
            return;
        }
        // 判断是否是ping消息
        if (webSocketFrame instanceof PongWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(webSocketFrame.content().retain()));
            return;
        }

        // 判断是否是二进制消息，如果是二进制消息 抛出异常
        if (!(webSocketFrame instanceof TextWebSocketFrame)) {
            logger.info("目前我们暂不支持二进制消息");
            throw new RuntimeException(this.getClass().getName() + "不支持消息");
        }

        // 获取客户端向服务端发送的消息
        String text = ((TextWebSocketFrame) webSocketFrame).text();
        logger.info("服务端收到客户端的消息" + text);
        // 将消息转换为 Information
        Information information = Information.strJson2Information(text);
        // 判断是否已存在用户信息
        if (InformationOperationMap.isUser(information)) {
            // 判断是否有这个聊天室
            if (InformationOperationMap.map.containsKey(information.getRoomId())) {
                // 判断是否有其他用户在
                if (InformationOperationMap.map.get(information.getRoomId()).size() > 0) {
                    InformationOperationMap.map.get(information.getRoomId()).forEach((id, iom) -> {
                        try {
                            Information information1 = iom.getInformation();
                            information1.setMessage("30003");
                            // 发送其他用户信息给要注册用户
                            sendWebSocket(information1.infoJson());

                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.error(String.valueOf(e));
                        }
                    });
                }
                // 添加用户
                InformationOperationMap.add(ctx, information);
                System.out.println("add:" + information.infoJson());
                logger.info(information.infoJson());
            }

            // 将用户发送的消息发给所有在同一聊天室的用户
            InformationOperationMap.map.get(information.getRoomId()).forEach((id, iom) -> {
                try {
                    iom.send(information);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(String.valueOf(e));
                }
            });

            // 记录userId 和roomId ，当页面刷新活浏览器关闭时， 注销掉此次链路
            this.userId = information.getUserId();
            this.roomId = information.getRoomId();
            this.userName = information.getUserName();
        } else {
            System.out.println("-----------error-----------");
        }
    }

    /**
     * websocket返回消息
     *
     * @param msg
     * @throws Exception
     */
    public void sendWebSocket(String msg) throws Exception {
        if (this.webSocketServerHandshaker == null || this.channelHandlerContext == null || this.channelHandlerContext.isRemoved()) {
            throw new Exception("尚未握手成功，无法发送websocket消息");
        }
        //发送消息
        this.channelHandlerContext.channel().write(new TextWebSocketFrame(msg));
        this.channelHandlerContext.flush();
    }


    /**
     * 处理关闭情况
     *
     * @param ctx
     * @param promise
     * @throws Exception
     */
    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        // 关闭连接并移除用户信息
        InformationOperationMap.delete(this.userId,this.roomId);
        Information information = new Information();
        information.setUserName(this.userName);
        information.setMessage("20002");
        // 将用户下线消息发送给下线用户
        InformationOperationMap.map.get(this.roomId).forEach((id,iom) ->{
            try{
                iom.send(information);
            }catch (Exception e){
                logger.error(String.valueOf(e));
            }
        });

    }
}
