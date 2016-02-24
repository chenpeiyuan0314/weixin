package com.yuan.project.weixin.message.res;

/**
 * 文本消息
 * 
 * @author Chen
 * @date 2016-02-24
 */
public class TextMessage extends BaseMessage {

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	// 文本内容
	private String content;
}
