package com.faceye.feature.service;

import java.util.Collection;
import java.util.Queue;

import com.faceye.feature.util.ServiceException;

/**
 * 队列服务接口
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年1月2日
 */
public interface QueueService<T> {

	/*
	 * 从队列中取出对像
	 */
	public T get() throws ServiceException;

	/**
	 * 往队列中加入对像
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年1月2日
	 */
	public void add(T o) throws ServiceException;

	/**
	 * 添加整个集合
	 * @todo
	 * @param collections
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年1月2日
	 */
	public void addAll(Collection<T> collections) throws ServiceException;

	/**
	 * 
	 * @todo
	 * @return
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年7月8日
	 */
	public Boolean isEmpty() throws ServiceException;

	/**
	 * 取得队列大小
	 * @todo
	 * @return
	 * @throws ServiceExcepotion
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年7月8日
	 */
	public int getSize() throws ServiceException;

	public Queue<T> getQueue();
}
