package com.faceye.component.security.service.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;

import com.faceye.component.security.util.AjaxSecurity;

/**
 * 扩展Spring security 支持ajax方式 的数据处理
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年6月28日
 */
public class AccessDeniedHandlerImpl extends org.springframework.security.web.access.AccessDeniedHandlerImpl {
	private final static String DEFAULT_CHARACTER_ENCODING = "UTF-8";
	private String characterEncoding = DEFAULT_CHARACTER_ENCODING;
	private String errorPage;

	public void handle(javax.servlet.http.HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		if (response != null) {
			response.setCharacterEncoding(this.getCharacterEncoding());
		}
		boolean isAjaxRequest=AjaxSecurity.isAjaxRequest(request);
		if(isAjaxRequest){
			AjaxSecurity.printAjaxLogin(response);
			return;
		}
		if (!response.isCommitted()) {
			if (errorPage != null) {
				// request.setCharacterEncoding()
				// Put exception into request scope (perhaps of use to a view)
				request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);

				// Set the 403 status code.
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);

				// forward to error page.
				RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
				dispatcher.forward(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
			}
		}
	}

	public String getCharacterEncoding() {
		return characterEncoding;
	}

	public void setCharacterEncoding(String characterEncoding) {
		if (StringUtils.isNotEmpty(characterEncoding)) {
			this.characterEncoding = characterEncoding;
		}
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		if ((errorPage != null) && !errorPage.startsWith("/")) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}
		super.setErrorPage(errorPage);
		this.errorPage = errorPage;
	}

}
