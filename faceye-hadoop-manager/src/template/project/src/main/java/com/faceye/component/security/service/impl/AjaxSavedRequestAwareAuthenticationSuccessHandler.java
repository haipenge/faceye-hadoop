package com.faceye.component.security.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import com.faceye.component.security.util.AjaxSecurity;
/**
 * 让Spring支持ajax方式登陆
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年6月28日
 */
public class AjaxSavedRequestAwareAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	protected final Log logger = LogFactory.getLog(this.getClass());

	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		// 加入ajax处理
		boolean isAjax = AjaxSecurity.isAjaxRequest(request);
		if (isAjax) {
			AjaxSecurity.allowAccess(response, Boolean.TRUE);
			clearAuthenticationAttributes(request);
			return;
		}
		
		if (savedRequest == null) {
			super.onAuthenticationSuccess(request, response, authentication);

			return;
		}

		if (isAlwaysUseDefaultTargetUrl() || StringUtils.hasText(request.getParameter(getTargetUrlParameter()))) {
			requestCache.removeRequest(request, response);
			super.onAuthenticationSuccess(request, response, authentication);

			return;
		}

		clearAuthenticationAttributes(request);

		// Use the DefaultSavedRequest URL
		String targetUrl = savedRequest.getRedirectUrl();
		logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
		// ajax不跳转
		// getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}



	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}
}
