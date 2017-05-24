package com.faceye.feature.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

public class DisableUrlSessionFilter implements Filter
{

	public void destroy()
	{
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException
	{
		if(!(arg0 instanceof HttpServletRequest)){
			arg2.doFilter(arg0, arg1);
			return;
		}
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		if(request.isRequestedSessionIdFromURL()){
			HttpSession session=request.getSession();
			if(null!=session){
				session.invalidate();
			}
		}
		HttpServletResponseWrapper warppResponse=new HttpServletResponseWrapper(response){
			@Override
		   public String encodeRedirectUrl(String url){
			   return url;
		   }
			@Override
		   public String encodeRedirectURL(String url){
				return url;
			}
			@Override
			public String encodeUrl(String url){
				return url;
			}
			@Override
			public String encodeURL(String url){
				return url;
			}
			
		};
		arg2.doFilter(arg0, warppResponse);
	}

	public void init(FilterConfig arg0) throws ServletException
	{
	}

}
