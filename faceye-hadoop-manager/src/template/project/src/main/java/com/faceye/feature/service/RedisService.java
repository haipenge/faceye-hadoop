package com.faceye.feature.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;

import com.faceye.feature.util.ServiceException;


/**
 * Redis 基础服务
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年4月12日
 */

public interface RedisService {
	public RedisTemplate getRedisTemplate() throws ServiceException;
	
	/**
	 * 设置List
	 * @todo
	 * @param key
	 * @param values
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年4月13日
	 */
	public void rPush(Object key,List values) throws ServiceException;
	
	/**
	 * 设置一个值
	 * @todo
	 * @param key
	 * @param value
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年4月13日
	 */
	public void rPush(Object key,Object value) throws ServiceException;
	
	/**
	 * 读取list
	 * @todo
	 * @param key
	 * @return
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年4月13日
	 */
	public List getList(Object key) throws ServiceException;
	
	/**
	 * 设置 key:value
	 * @todo
	 * @param key
	 * @param value
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年4月13日
	 */
	public void set(Object key,Object value) throws ServiceException;
	
	/**
	 * 取值
	 * @todo
	 * @param key
	 * @return
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年4月13日
	 */
	public Object get(Object key) throws ServiceException;
	
	/**
	 * 是否存在某一Key.
	 * @todo
	 * @param key
	 * @return
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年4月14日
	 */
	public Boolean exist(Object key) throws ServiceException;
	
	/**
	 * 取得Key列表
	 * @todo
	 * @param pattern
	 * @return
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年4月14日
	 */
	public Set getKeys(Object pattern) throws ServiceException;
	
	/**
	 * 清除缓存
	 * @todo
	 * @param key
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月7日
	 */
	public void delete(Object key) throws ServiceException;
}
