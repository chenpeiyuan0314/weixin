package com.yuan.project.weixin.message.res;

/**
 * 消息基类
 * 公众账号 -> 普通用户
 * 
 * @author Chen
 * @date 2016-02-24
 */
public class BaseMessage {
	
	public String getToUserName() {
		return toUserName;
	}
	
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	
	public String getMsgType() {
		return msgType;
	}
	
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	public long getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	public int getFuncFlag() {
		return funcFlag;
	}
	
	public void setFuncFlag(int funcFlag) {
		this.funcFlag = funcFlag;
	}
	
	
	// 接收方账号 OpenID
	private String toUserName;
	// 开发者账号
	private String fromUserName;
	// 消息类型
	// text/music/news
	private String msgType;
	// 消息创建时间
	private long createTime;
	// 位0x0001被标志时，星标刚收到的消息
	private int funcFlag;	
}
