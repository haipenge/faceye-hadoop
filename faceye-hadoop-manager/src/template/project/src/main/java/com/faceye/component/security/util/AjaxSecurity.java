package com.faceye.component.security.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.faceye.feature.util.Json;

/**
 * ajax安全控制
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年3月12日
 */
public class AjaxSecurity {
	//允许访问
	private static Map allowAccess = new HashMap();
	//拒绝访问
	private static Map deniedAccess = new HashMap();
	
	//以ajax 方式进行登录
	private static Map ajaxLogin=new HashMap();
	// 请求类型:ajax,common.
	private static Logger log = LoggerFactory.getLogger(AjaxSecurity.class);
	static {
		allowAccess.put("allow", Boolean.TRUE);
		deniedAccess.put("allow", Boolean.FALSE);
		ajaxLogin.put("loginType", "ajax");
	}

	public static void allowAccess(HttpServletResponse response,boolean isAllow) {
		try {
			if (isAllow) {
				Json.print(response,Json.toJson(allowAccess));

			} else {
				Json.print(response,Json.toJson(deniedAccess));
			}
		} catch (IOException e) {
			log.error(">>--->" + e.getMessage());
		}
	}
	
	public static void printAjaxLogin(HttpServletResponse response){
		try {
			Json.print(response,Json.toJson(ajaxLogin));
		} catch (IOException e) {
			log.error(">>--->"+e.getMessage());
		}
	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		boolean isAjaxRequest = Boolean.FALSE;
//		Map params = CollectionUtil.getInstance().transferRequstParameterMap(request.getParameterMap());
//		if (MapUtils.isNotEmpty(params) && params.containsKey(REQUEST_TYPE)
//				&& StringUtils.equals(REQUEST_TYPE_AJAX, MapUtils.getString(params, REQUEST_TYPE))) {
//			isAjaxRequest = Boolean.TRUE;
//		}
		try{
		String xRequestedWith=request.getHeader("x-requested-with");
		if(StringUtils.isNotEmpty(xRequestedWith)&&StringUtils.equals(xRequestedWith, "XMLHttpRequest")){
			isAjaxRequest=Boolean.TRUE;
		}
		}catch(Exception e){
			log.error(">>Faceye -> :"+e);
		}
		return isAjaxRequest;
	}
}
