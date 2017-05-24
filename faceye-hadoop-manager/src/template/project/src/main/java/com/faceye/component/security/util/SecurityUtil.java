package com.faceye.component.security.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
/**
 * 安全认证工作类
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年7月5日
 */
public class SecurityUtil {
	/**
	 * 取得当前登录用户名
	 * 需要HttpSessionEventPublisher支持
	 * @return
	 */
	public static String getCurrentUserName() {
		String username = null;
		Object obj = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (null != obj) {
			if (obj instanceof UserDetails) {
				username = ((UserDetails) obj).getUsername();
			} else {
				username = obj.toString();
			}
		}
		return username;
	}
}
