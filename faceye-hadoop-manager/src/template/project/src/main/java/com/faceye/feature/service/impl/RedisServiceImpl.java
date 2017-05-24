package com.faceye.feature.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.faceye.feature.service.RedisService;
import com.faceye.feature.util.ServiceException;
/**
 * Redis基础服务
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年4月12日
 */

public class RedisServiceImpl implements RedisService {
   
	@Autowired
	private RedisTemplate redisTemplate=null;
	@Override
	public RedisTemplate getRedisTemplate() throws ServiceException {
		return redisTemplate;
	}
	/**
	 * 存储list结构
	 * @todo
	 * @param key
	 * @param value
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年4月13日
	 */
	public void rPush(Object key,Object value) throws ServiceException{
		this.getRedisTemplate().boundListOps(key).rightPush(value);
	}
	public void rPush(Object key,List values) throws ServiceException{
		if(key!=null&&CollectionUtils.isNotEmpty(values)){
			for(Object value:values){
				this.rPush(key, value);
			}
		}
	}
	
	public List getList(Object key) throws ServiceException{
		List res=null;
		res=this.getRedisTemplate().boundListOps(key).range(0, -1);
		return res;
	}
	@Override
	public void set(Object key, Object value) throws ServiceException {
		this.getRedisTemplate().opsForValue().set(key, value);
	}
	@Override
	public Object get(Object key) throws ServiceException {
		Object o=this.getRedisTemplate().opsForValue().get(key);
		return o==null?null:o;
	}
	@Override
	public Boolean exist(Object key) throws ServiceException {
		return this.redisTemplate.hasKey(key);
	}
	@Override
	public Set getKeys(Object pattern) throws ServiceException {
		return this.redisTemplate.keys(pattern);
	}
	@Override
	public void delete(Object key) throws ServiceException {
		this.redisTemplate.delete(key);
	}
	
}
