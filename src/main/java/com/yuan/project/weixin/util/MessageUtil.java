package com.yuan.project.weixin.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.yuan.project.weixin.message.res.TextMessage;

public class MessageUtil {

	/**
	 * 解析微信发来的请求
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> parseXml(HttpServletRequest request) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		
		InputStream input = request.getInputStream();
		SAXReader reader = new SAXReader();
		Document doc = reader.read(input);
		
		Element root = doc.getRootElement();
		List<Element> list =  root.elements();
		
		for(Element e : list) {
			map.put(e.getName(), e.getText());
		}
		
		input.close();
		input = null;
		
		return map;
	}
	
	/**
	 * 文本消息对象转化为XML
	 * 
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				
				@Override
				public void startNode(String name) {
					if(!"xml".equals(name)) {
						name = name.substring(0,1).toUpperCase() + name.substring(1);
					}
					super.startNode(name);
				}

				@Override
				protected void writeText(QuickWriter writer, String text) {
					writer.write("<![CDATA[");
                    writer.write(text);
                    writer.write("]]>");
				}
			};
		}
	});
}
