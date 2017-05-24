package com.faceye.component.security.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.faceye.component.security.entity.Menu;
import com.faceye.component.security.service.MenuService;
/**
 * 对生成菜单的拦截器
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年7月2日
 */
public class MenuHandlerInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private MenuService menuService=null;
	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		List<Menu> roots=this.menuService.getRoots();
		List<Menu> menus=this.menuService.getAll();
		request.setAttribute("MENU_ROOTS", roots);
		request.setAttribute("MENU_MENUS", menus);
		
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterConcurrentHandlingStarted(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	}
}
