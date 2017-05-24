package com.faceye.feature.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.faceye.feature.service.PropertyService;
import com.faceye.feature.service.impl.BeanContextUtil;

public class JspUtil {
	private static Logger logger=LoggerFactory.getLogger(JspUtil.class);
	private static String HOST="";
	public static String characterEncoding(String arg0) {
		String result = "";
		if (StringUtils.isNotEmpty(arg0)) {
			try {
				result = URLEncoder.encode(arg0, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(">>FaceYe:",e);
			}
		}
		return result;
	}

	public static String characterDecoder(String arg0) {
		String result = "";
		if (StringUtils.isNotEmpty(arg0)) {
			try {
				result = URLDecoder.decode(arg0, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(">>FaceYe:",e);
			}
		}
		return result;
	}

	public static String getSummary(Object arg0, int length) {
		if (null != arg0) {
			return filterHtmlCharacters(arg0.toString(), length);
		} else {
			return "";
		}
	}

	public static String filterHtmlCharacters(String content, int length) {
		if (StringUtils.isEmpty(content)) {
			return "";
		}
		// 去掉所有html元素,
		String str = content.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");
		int len = str.length();
		if (len <= length) {
			return str;
		} else {
			str = str.substring(0, length);
			str += "...";
		}
		return str;
	}
	
	public static String getDomain(String url){
		String domain="";
		if(StringUtils.isNotEmpty(url)){
			domain=url.substring(7, url.substring(7).indexOf("/")+7);
		}
		return domain;
	}
    
	public static String getHost(){
		if(StringUtils.isEmpty(HOST)){
			HOST=BeanContextUtil.getInstance().getBean(PropertyService.class).get("faceye.host");
		}
		return HOST;
	}
	
}
