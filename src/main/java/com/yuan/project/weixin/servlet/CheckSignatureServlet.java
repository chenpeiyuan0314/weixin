package com.yuan.project.weixin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuan.project.weixin.util.SignatureUtil;

public class CheckSignatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");  
		String echostr = req.getParameter("echostr");
		
		if(SignatureUtil.checkSignature(signature, timestamp, nonce)) {
			resp.getWriter().write(echostr);
		}
		
		resp.getWriter().close();
	}

}
