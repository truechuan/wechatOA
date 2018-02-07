package com.leyidai.web.weChat.entity;

/**
 * 发送客服消息基类
 * @author mikan
 * @version 1.0
 */
public class CustomerBaseMessage {
	
	/**
	 * 普通用户openid
	 */
	private String touser;
	
	/**
	 * 消息类型
	 */
	private String msgtype;


	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	
}
