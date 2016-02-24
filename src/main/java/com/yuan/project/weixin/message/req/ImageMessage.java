package com.yuan.project.weixin.message.req;

/**
 * 图片消息
 * 
 * @author Chen
 * @date 2016-02-24
 */
public class ImageMessage extends BaseMessage {
	
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	// 图片链接
	private String picUrl;
}
