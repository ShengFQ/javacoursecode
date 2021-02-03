package com.geekbang.shengfq.week2.netty.homework;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
/**
 * 系统编解码
 * */
public class DecodeChannelHandler extends ByteToMessageCodec {
    private static final Logger logger = LoggerFactory.getLogger(DecodeChannelHandler.class);
    /**
     * 出站编码
     * @param ctx
     * @param msg
     * @param out
     * @see #encode(ChannelHandlerContext, Object, ByteBuf)
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        logger.info("channel handler encode...");

        FullHttpRequest fullRequest = (FullHttpRequest) msg;

    }

    /**
     * 入站解码
     * @param ctx
     * @param in
     * @param out
     * @see #decode(ChannelHandlerContext, ByteBuf, List)
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
        logger.info("channel handler decode...");
    }
}
