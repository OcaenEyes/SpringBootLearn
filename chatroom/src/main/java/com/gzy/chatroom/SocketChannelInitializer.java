package com.gzy.chatroom;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

public class SocketChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline channelPipeline = channel.pipeline();
        channelPipeline.addLast("http-codec", new HttpServerCodec()); //http消息编码解码
        channelPipeline.addLast("aggregator", new HttpObjectAggregator(65536)); // http消息组装
        channelPipeline.addLast("http-chunked", new ChunkedWriteHandler()); // websocket通信支持
        channelPipeline.addLast("handler", new SocketHandller()); //指定房间通信
    }
}
