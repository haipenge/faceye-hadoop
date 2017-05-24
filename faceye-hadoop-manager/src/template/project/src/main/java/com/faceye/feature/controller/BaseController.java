package com.faceye.feature.controller;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.faceye.feature.service.BaseService;

/**
 * 基础控制器
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月21日
 * @param <T>
 * @param <ID>
 * @param <S>
 */
abstract public class BaseController<T, ID extends Serializable, S extends BaseService<T, ID>> {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected S service = null;

	/**
	 * 默认构造器
	 * 
	 * @param service
	 */
	public BaseController(S service) {
		this.service = service;
	}

	/**
	 * 取得页码
	 * @todo
	 * @param params
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月26日
	 */
	public int getPage(Map params){
		int page=MapUtils.getIntValue(params, "page");
		if(page==0){
			page=1;
		}
		return page;
	}
	/**
	 * 取得每页数据大小
	 * @todo
	 * @param params
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月26日
	 */
	public int getSize(Map params){
		int size=MapUtils.getIntValue(params, "size");
		if(size==0){
			size=20;
		}
		return size;
	}
	
}
