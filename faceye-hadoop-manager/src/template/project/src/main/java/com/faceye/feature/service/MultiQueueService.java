package com.faceye.feature.service;

import java.util.Collection;
import java.util.Set;


public interface MultiQueueService<T> extends QueueService<T> {		
	
	/**
	 * 将某一值添加到某一指定队列中
	 * @todo
	 * @param key
	 * @param elements
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月4日
	 */
	public void add(String key,T object);
	
	
	/**
	 * 添加全部集合到队列中
	 * @todo
	 * @param key
	 * @param coll
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月4日
	 */
	public void addAll(String key,Collection<T> coll);
	
	
	/**
	 * 某一队列是否为空
	 * @todo
	 * @param key
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月4日
	 */
	public boolean isEmpty(String key);
	
	
	
	/**
	 * 取得某一队列的大小
	 * @todo
	 * @param key
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月4日
	 */
	public int getSize(String key);
	
	
	/**
	 * 从某一队列中取得元素.
	 * @todo
	 * @param key
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月4日
	 */
	public T get(String key);
	
	/**
	 * 添加 一个队列
	 * @todo
	 * @param key
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月4日
	 */
	public void addQueue(String key);
	
	/**
	 * 添加一系列队列
	 * @todo
	 * @param keys
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月4日
	 */
	public void addQueues(String [] keys);
	
	/**
	 * 取得所有键值
	 * @todo
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月4日
	 */
	public Set<String> getKeys();
}
