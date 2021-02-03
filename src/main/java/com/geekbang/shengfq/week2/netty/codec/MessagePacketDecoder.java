package com.geekbang.shengfq.week2.netty.codec;

import com.geekbang.shengfq.week2.netty.dto.Report;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
/**
 * 消息报文解码器
 * */
public class MessagePacketDecoder extends ByteToMessageDecoder {

    private static final Logger logger = LoggerFactory.getLogger(MessagePacketDecoder.class);
    private ChannelHandlerContext channelHandlerContext;
    private ByteBuf byteBuf;
    private List<Object> list;

    public MessagePacketDecoder() throws Exception
    {
    }
    /**
     * @param byteBuf 传入数据
     * @param list 添加解码消息
     * decode()方法被调用时将会传
     * 入一个包含了传入数据的 ByteBuf，以及一个用来添加解码消息
     * 的 List。对这个方法的调用将会重复进行，直到确定没有新的元
     * 素被添加到该 List，或者该 ByteBuf 中没有更多可读取的字节
     * 时为止。然后，如果该 List 不为空，那么它的内容将会被传递给
     * ChannelPipeline 中的下一个 ChannelInboundHandler
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        this.channelHandlerContext = channelHandlerContext;
        this.byteBuf = byteBuf;
        this.list = list;
        List<Report> retList = new ArrayList<>();
        boolean isgetProtocol = false;
        byte[] b = new byte[1];
        Report temp= null;
        while (byteBuf.isReadable()) {
            byteBuf.readBytes(b);
            logger.info("session id:{}, 服务器收到报文:{}", channelHandlerContext.channel().id().asLongText(), StringUtils.bytesToHexStr(temp.header)+StringUtils.bytesToHexStr(temp.data)+String.format("%02xaa", temp.dcs));
            /*if (b[0] == 0x55) {
                if (11 <byteBuf.capacity()) {
                    temp = new Report();
                    temp.reportVer=byteBuf.readByte();
                    //处理包序列
                    byte[] bytelen = new byte[2];
                    byte tempbyte;
                    byteBuf.readBytes(bytelen);
                    tempbyte=bytelen[0];
                    bytelen[0]=bytelen[1];
                    bytelen[1]=tempbyte;
                    temp.pageSn= StringUtils.bytesToHexStr(bytelen);
                    //处理源地址(SADDR)
                    byte[] terminalIDByte = new byte[6];
                    //终端代号
                    int headerLen = 12;
                    byteBuf.readBytes(terminalIDByte);
                    temp.terminalNo = StringUtils.bytesToHexStr(terminalIDByte);
                    //处理包长
                    byteBuf.readBytes(bytelen);
                    tempbyte=bytelen[0];
                    bytelen[0]=bytelen[1];
                    bytelen[1]=tempbyte;

                    // 将byte转成int
                    temp.dataLen = Integer.parseInt(StringUtils
                            .bytesToHexStr(bytelen), 16);

                    //重新处理包头 因为校验是 包头+内容+校验值
                    byteBuf.writerIndex(byteBuf.readerIndex() - headerLen);
                    byteBuf.readBytes(temp.header);

                    // 收到协议入口后，收取不完整
                    *//*if (iobuffer.remaining() < (temp.dataLen+2)) {
                        iobuffer.position(iobuffer.position() - headerLen);
                        return false;
                    }*//*
                    //包数据
                    temp.data =new byte[temp.dataLen];
                    byteBuf.readBytes(temp.data);
                    //校验码
                    temp.dcs=byteBuf.readByte();
                    //结束码
                    byteBuf.readBytes(b);
                    temp.postamble = b[0]&0xff;
                    if(0xaa!=(b[0]&0xff)){
                        byteBuf.markReaderIndex();
                        continue;
                    }
                    // 收到完整后进行处理
                    if(temp.chechSum()){
                        InetSocketAddress insocket = (InetSocketAddress) channelHandlerContext.channel()
                                .remoteAddress();
                        temp.ip  = insocket.getAddress().getHostAddress();
                        // temp.sessionId = channelHandlerContext.channel().id().asLongText();
                        retList.add(temp);
                        isgetProtocol = true;

                        logger.info("session id:{}, 服务器收到报文:{}", channelHandlerContext.channel().id().asLongText(), StringUtils.bytesToHexStr(temp.header)+StringUtils.bytesToHexStr(temp.data)+String.format("%02xaa", temp.dcs));
                        //logUtil.wirteLogFileReceive(temp);
                    }
                }
            }*/
        }
        if (isgetProtocol) {
            channelHandlerContext.writeAndFlush(retList);
        }
    }
}