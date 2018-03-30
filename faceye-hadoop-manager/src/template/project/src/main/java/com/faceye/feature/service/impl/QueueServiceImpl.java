package com.faceye.feature.service.impl;

import java.util.Collection;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.faceye.feature.service.QueueService;
 

public abstract class QueueServiceImpl<T> implements QueueService<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	@Override
	synchronized public T get()   {
		T o = (T) this.getQueue().poll();
		return o;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(T o)   {
		this.getQueue().add(o);
	}

	/**
	 * 添加整个集合
	 * @todo
	 * @param collections
	 * @ 
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年1月2日
	 */
	@SuppressWarnings("unchecked")
	public void addAll(Collection<T> collections)   {
		this.getQueue().addAll(collections);
	}

	/**
	 * 是否为空
	 */
	public Boolean isEmpty()   {
		Boolean res = Boolean.TRUE;
		if (this.getQueue() != null && this.getQueue().size() > 0) {
			res = Boolean.FALSE;
		}
		return res;
	}

	public int getSize()   {
		int size = 0;
		if (!isEmpty()) {
			size = this.getQueue().size();
		}
		return size;
	}
}
