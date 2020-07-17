package com.gzy.nettychattoom;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 *  初始化连接时候的各个组件
 */
public class ChatWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        //添加一个http的编解码器
        channelPipeline.addLast("HttpServerCodec", new HttpServerCodec());
        // 添加一个聚合器
        channelPipeline.addLast("HttpObjectAggregator", new HttpObjectAggregator(65536));
        //添加一个大数据流的支持
        channelPipeline.addLast("http-chunked", new ChunkedWriteHandler());

        channelPipeline.addLast("websocket",new WebSocketServerProtocolHandler("/ws"));
        channelPipeline.addLast("handler", new ChatWebsocketHandler());
    }

}
