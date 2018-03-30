package com.faceye.feature.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;

 


/**
 * Redis 基础服务
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年4月12日
 */

public interface RedisService {
	public RedisTemplate getRedisTemplate()  ;
	
	/**
	 * 设置List
	 * @todo
	 * @param key
	 * @param values
	 * @ 
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年4月13日
	 */
	public void rPush(Object key,List values)  ;
	
	/**
	 * 设置一个值
	 * @todo
	 * @param key
	 * @param value
	 * @ 
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年4月13日
	 */
	public void rPush(Object key,Object value)  ;
	
	/**
	 * 读取list
	 * @todo
	 * @param key
	 * @return
	 * @ 
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年4月13日
	 */
	public List getList(Object key)  ;
	
	/**
	 * 设置 key:value
	 * @todo
	 * @param key
	 * @param value
	 * @ 
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年4月13日
	 */
	public void set(Object key,Object value)  ;
	
	/**
	 * 取值
	 * @todo
	 * @param key
	 * @return
	 * @ 
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年4月13日
	 */
	public Object get(Object key)  ;
	
	/**
	 * 是否存在某一Key.
	 * @todo
	 * @param key
	 * @return
	 * @ 
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年4月14日
	 */
	public Boolean exist(Object key)  ;
	
	/**
	 * 取得Key列表
	 * @todo
	 * @param pattern
	 * @return
	 * @ 
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年4月14日
	 */
	public Set getKeys(Object pattern)  ;
	
	/**
	 * 清除缓存
	 * @todo
	 * @param key
	 * @ 
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月7日
	 */
	public void delete(Object key)  ;
}
