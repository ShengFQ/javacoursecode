package com.geekbang.shengfq.week2.netty.echo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * channel 初始化事件回调
 * @author sheng
 * */
public class EchoChannelInitializer extends ChannelInitializer<SocketChannel> {

    private static final Logger logger = LoggerFactory.getLogger(EchoChannelInitializer.class);

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        logger.info("server channel init...");
        ChannelPipeline p = ch.pipeline();
        //p.addLast(new LoggingHandler(LogLevel.INFO));
        p.addLast(new EchoServerHandler());
        // p.addLast("idleStateHandler",new IdleChannelHandler(15,0,0, TimeUnit.MINUTES));
        // p.addLast(new DecodeChannelHandler());
        // p.addLast(new MessagePacketDecoder());
        //  p.addLast(new MessagePacketEncoder());
    }
}
