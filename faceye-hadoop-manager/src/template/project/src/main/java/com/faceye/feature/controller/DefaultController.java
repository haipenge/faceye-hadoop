package com.faceye.feature.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.converter.StringHttpMessageConverter;
/**
 * 
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月18日
 */
@Controller
@RequestMapping("/default")
@Scope("prototype")
public class DefaultController {
	/**
	 * 管理后台首页
	 * @todo
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月29日
	 */
	@RequestMapping(value="/index")
	public String index(){
		return "default.manager";
	}
	/**
	 * 默认错误页
	 * @todo
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月29日
	 */
	@RequestMapping("/error")
	public String error(){
		return "default.error";
	}
	/**
	 * 动态转向页面
	 * @todo
	 * @param forward
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月29日
	 */
	@RequestMapping("/forward")
	public String forward(@RequestParam("forward")String forward){
		return forward;
	}
}
