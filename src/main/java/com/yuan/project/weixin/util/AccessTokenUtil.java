package com.yuan.project.weixin.util;

import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import com.alibaba.fastjson.JSONObject;

public class AccessTokenUtil {
	
	public static synchronized String getAccessToken() {
		long nowTime = new Date().getTime();
		
		if(accessToken != null && expiresTime - nowTime > 10000) {
			return accessToken;
		}
		
		// 重新获得accessToken
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + SECRET);
		method.getParams().setSoTimeout(5000);
		String resText = "";
		try {
			client.executeMethod(method);
			resText = method.getResponseBodyAsString();
		} catch(Exception e) {
			
		}
		JSONObject json = JSONObject.parseObject(resText);
		
		accessToken = json.getString("access_token");
		int expiresIn = json.getIntValue("expires_in");
		expiresTime = nowTime + expiresIn * 1000;
		
		return accessToken;
	}

	// 访问凭据
	private static String accessToken;
	// 剩余时间 秒
	private static long expiresTime;
	
	private static final String APPID = "wx795f3d6df67b019c";
	private static final String SECRET = "882feb7ccf8412338d7150f4ee048a87";
}
