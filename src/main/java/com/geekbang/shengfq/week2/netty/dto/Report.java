package com.geekbang.shengfq.week2.netty.dto;

/**
 *  GPRS通讯协议DTO
 * @author wangjincheng
 * @date 2017-12-07
 */
public class Report {

	/**
	 * 包头固定部分
	 */
	public byte[] header = new byte[12];

	/**
	 * 协议版本
	 */
	public byte reportVer;
	
	/**
	 * 终端编号   一般是 12位
	 */
	public String terminalNo;
	
	/**
	 * 终端交易序号 hex str
	 */
	public String pageSn;
	
	/**
	 * 数据长度
	 */
	public int dataLen;

	/**
	 * 包数据
	 */
	public byte[] data;

	/**
	 * 指令
	 */
	public short command;

	/**
	 * 包数据校验字段
	 */
	public byte dcs;

	/**
	 * 报文结束符号  默认为AA
	 */
	public int postamble;

	/**
	 * 客户端连接ip
	 */
	public String ip;

	/**
	 * 客户端连接sessionId
	 */
	public long sessionId;
	
	/**
	 * 二维码交易记录发送成功字段  true:成功  false：失败。刷传统卡记录 默认为true
	 */
	public boolean qrcodeResult = true;

	/**
	 * 获取校验值
	 * @param header
	 * @param data
	 * @return
	 */
	public byte getCheckSum(byte[] header, byte[] data) {
		//从PD0 至PDn；PD0 至PDn 的累加和加此校验码等于0)
		byte b = 0;
		for (byte temp : header) {
			b += temp;
		}
		for (byte temp : data) {
			b += temp;
		}
		byte res = (byte) (0 - b);
		return res;
	}

	/**
	 * 对当前报文进行检验码验证
	 * @return
	 */
	public boolean chechSum(){
		byte crc = getCheckSum(this.header, this.data);
		if(crc == this.dcs){
			return true;
		}
		return false;
	}

}
