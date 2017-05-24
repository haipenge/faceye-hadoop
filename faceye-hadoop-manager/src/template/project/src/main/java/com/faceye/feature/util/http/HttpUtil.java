package com.faceye.feature.util.http;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月19日
 */
public class HttpUtil {

	private Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	public static Map getRequestParams(Map params) {
		Map result = new HashMap();
		if (MapUtils.isEmpty(params)) {
			return result;
		}
		@SuppressWarnings("rawtypes")
		Iterator it = params.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String[] keyValues = (String[]) params.get(key);
			String keyValue = StringUtils.join(keyValues);
			result.put(key, keyValue);
		}
		return result;
	}

	public static Map getRequestParams(HttpServletRequest request) {
		Map params = request.getParameterMap();
		return getRequestParams(params);
	}
	

}
