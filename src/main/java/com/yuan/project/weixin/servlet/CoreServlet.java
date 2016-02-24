package com.yuan.project.weixin.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.yuan.project.weixin.message.res.TextMessage;
import com.yuan.project.weixin.util.MessageUtil;

public class CoreServlet extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("plain/text;charset=UTF-8");
		
		Map<String,String> params = null;
		try {
			params = MessageUtil.parseXml(req);
		} catch(Exception e) {
			LOG.error(e.getMessage(), e);
			return;
		}
		LOG.debug("request:\n" + params);
		
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(params.get("FromUserName"));
		textMessage.setFromUserName(params.get("ToUserName"));
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setFuncFlag(1);
		textMessage.setMsgType("text");
		
		if("text".equals(params.get("MsgType"))) {
			textMessage.setContent(params.get("Content"));
		} else {
			textMessage.setContent("消息类型：" + params.get("MsgType"));
		}
		
		String resText = MessageUtil.textMessageToXml(textMessage);
		LOG.debug("response:\n" + resText);
		
		resp.getWriter().append(resText).close();
	}

	private static final Logger LOG = Logger.getLogger(CoreServlet.class);
}
