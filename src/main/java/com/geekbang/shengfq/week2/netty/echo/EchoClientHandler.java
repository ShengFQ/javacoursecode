
package com.geekbang.shengfq.week2.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 客户端业务逻辑处理
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(EchoClientHandler.class);
    private final ByteBuf firstMessage;

    /**
     * 只初始化一次
     */
    public EchoClientHandler() {
        logger.info("#######client handler init... ");
        firstMessage = Unpooled.buffer(EchoClient.SIZE);
        for (int i = 0; i < firstMessage.capacity(); i ++) {
            firstMessage.writeByte((byte) i);
        }
    }
    /**
     * channel激活时调用
     * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        logger.info("client channelActive()...");
        ctx.writeAndFlush(firstMessage);
    }

    /**
     * 读取数据响应事件
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ctx.write(msg);
        logger.info("client channelRead()...");
    }
    /**
     * 读取数据完成响应事件
     * */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       ctx.flush();
        logger.info("client channelReadComplete()...");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
        logger.info("client exceptionCaught()...");
    }
}
