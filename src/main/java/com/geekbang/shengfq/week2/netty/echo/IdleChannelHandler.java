package com.geekbang.shengfq.week2.netty.echo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
/**
 * 心跳处理器
 *
 * */
public class IdleChannelHandler extends IdleStateHandler {
    private static final Logger logger = LoggerFactory.getLogger(IdleChannelHandler.class);
    /**
     * @param readerIdleTime
     * @param writerIdleTime
     * @param allIdleTime
     * @param unit
     * @see #IdleStateHandler(boolean, long, long, long, TimeUnit)
     */
    public IdleChannelHandler(long readerIdleTime, long writerIdleTime, long allIdleTime, TimeUnit unit) {
        super(readerIdleTime, writerIdleTime, allIdleTime, unit);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        logger.info("server channel Idle registered...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ctx.write(msg);
        logger.info("server channel Idle channelRead()...");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
        logger.info("server channel Idle channelRead()...");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
        logger.info("server channel Idle exceptionCaught()...");
    }
}
