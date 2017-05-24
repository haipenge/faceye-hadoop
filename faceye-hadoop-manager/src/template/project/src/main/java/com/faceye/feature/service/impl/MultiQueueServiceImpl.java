package com.faceye.feature.service.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.faceye.feature.service.MultiQueueService;

public abstract class MultiQueueServiceImpl<T> extends QueueServiceImpl<T> implements MultiQueueService<T> {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private Map<String, Queue<T>> queues = new ConcurrentHashMap<String, Queue<T>>();

	@Override
	public void add(String key, T object) {
		Queue<T> queue = getQueue(key);
		if (null != queue) {
			queue.add(object);
		} else {
			logger.debug(">>FaceYe -->Queue :" + key + " 不存在.");
		}
	}

	@Override
	public void addAll(String key, Collection<T> coll) {
		Queue<T> queue = getQueue(key);
		if (null != queue) {
			queue.addAll(coll);
		} else {
			logger.debug(">>FaceYe -->Queue :" + key + " 不存在.");
		}
	}

	@Override
	public boolean isEmpty(String key) {
		boolean res = true;
		Queue<T> queue = getQueue(key);
		res = CollectionUtils.isEmpty(queue);
		return res;
	}

	@Override
	public int getSize(String key) {
		int res = 0;
		Queue<T> queue = getQueue(key);
		if (null != queue) {
			res = queue.size();
		}
		return res;
	}

	@Override
	public T get(String key) {
		T res = null;
		Queue<T> queue = getQueue(key);
		if (null != queue) {
			res = queue.poll();
		}
		return res;
	}

	protected Queue<T> getQueue(String key) {
		Queue<T> queue = null;
		if (isExistKey(key)) {
			queue = queues.get(key);
		}
		return queue;
	}

	/**
	 * 是否包含某个序列
	 * @todo
	 * @param key
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月4日
	 */
	protected boolean isExistKey(String key) {
		boolean res = queues.containsKey(key);
		if (!res) {
			logger.debug(">>FaceYe -->序列 " + key + "  还没有创建.");
		}
		return res;
	}

	@Override
	public void addQueue(String key) {
		if (!queues.containsKey(key)) {
			Queue<T> queue = new ConcurrentLinkedQueue<T>();
			queues.put(key, queue);
		}
	}

	@Override
	public void addQueues(String[] keys) {
		if (null != keys) {
			for (String key : keys) {
				addQueue(key);
			}
		}
	}

	/**
	 * 取得所有键值
	 * @todo
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月4日
	 */
	public Set<String> getKeys() {
       Set<String>keys=null;
       if(MapUtils.isNotEmpty(queues)){
    	   keys=queues.keySet();
       }
       return keys;
	}
}
