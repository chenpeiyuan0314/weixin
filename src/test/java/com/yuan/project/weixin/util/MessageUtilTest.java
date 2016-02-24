package com.yuan.project.weixin.util;

import junit.framework.Assert;

import org.junit.Test;

import com.yuan.project.weixin.message.res.TextMessage;

public class MessageUtilTest {

	@Test
	public void testTextMessageToXml() {
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName("123456789");
		textMessage.setFromUserName("987654321");
		textMessage.setMsgType("text");
		textMessage.setCreateTime(1L);
		textMessage.setFuncFlag(1);
		textMessage.setContent("Hello");
		
		String exp = 
		"<xml>\n" +
		"  <ToUserName><![CDATA[123456789]]></ToUserName>\n" + 
		"  <FromUserName><![CDATA[987654321]]></FromUserName>\n" +
		"  <MsgType><![CDATA[text]]></MsgType>\n" +
		"  <CreateTime><![CDATA[1]]></CreateTime>\n" +
		"  <FuncFlag><![CDATA[1]]></FuncFlag>\n" +
		"  <Content><![CDATA[Hello]]></Content>\n" +
		"</xml>";
		
		String xml = MessageUtil.textMessageToXml(textMessage);
		Assert.assertEquals(exp, xml);
	}
}
