package com.geekbang.shengfq.week2.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件描述
 * 报文编码器,发送出去
 * @author fuqiang.sheng
 * @date 2019年08月21日 下午10:25
 */
public class MessagePacketEncoder extends MessageToByteEncoder<Object> {
    private static final Logger logger = LoggerFactory.getLogger(MessagePacketEncoder.class);

    /**
     * @param channelHandlerContext
     * @param o
     * @param byteBuf
     * */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        logger.info("encode...object:{} byte:{}",byteBuf);
        if(o instanceof String){
            String[] temp = o.toString().split(String.valueOf(','));
            String header=temp[0];
            String data=temp[1];
            //补齐字节数,包长占2字节    比如 5 -> 0005
            String len=String.format("%04x",data.length()/2);
            //小端模式
            len=len.substring(2)+len.substring(0,2);
            data=len+data;
            String report=header+data;
            report=report+checksum(StringUtils.hexStrToBytes(report))+"aa";
            logger.info("chinnel id:{}, 服务器发送:{}", channelHandlerContext.channel().id(), report);
            //logUtil.writeLogFileSend(report);
            byte[] b = StringUtils.hexStrToBytes(report);
           /* IoBuffer buf = IoBuffer.allocate(b.length).setAutoExpand(
                    true);
            buf.put(b);
            buf.flip();*/
            byteBuf.writeBytes(b);
        }
    }

    /**
     *
     * @Description 根据新的报文生成校验码
     *
     * @param data 生成校验码的数据段
     *
     * @return String
     *
     * @author wangjincheng
     *
     * @Date 2016-6-20
     *
     */
    public String checksum(byte []data) {
        byte b = 0;
        for (byte temp : data) {
            b += temp;
        }
        return String.format("%02x",(0-b)&0xff) ;
    }

}
