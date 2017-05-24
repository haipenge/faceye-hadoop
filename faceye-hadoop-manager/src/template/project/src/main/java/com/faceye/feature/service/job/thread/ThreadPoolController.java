package com.faceye.feature.service.job.thread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线程池控制器
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2013年12月15日
 */
public class ThreadPoolController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	public static int DEFAULT_THREAD_POOL_SIZE = 2;
	private Map<String, ExecutorService> map = new HashMap<String, ExecutorService>();

	private ThreadPoolController() {

	}

	/**
	 * 内部类
	 * 
	 * @author @haipenge haipenge@gmail.com Create Date:2013年12月15日
	 */
	private static class ThreadPoolControllerHolder {
		private static ThreadPoolController INSTANCE = new ThreadPoolController();
	}

	/**
	 * 取得实例
	 * 
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2013年12月15日
	 */
	public static ThreadPoolController getINSTANCE() {
		return ThreadPoolControllerHolder.INSTANCE;
	}

	public ExecutorService getExecutorService(String poolName) {
		return this.getExecutorService(poolName, this.DEFAULT_THREAD_POOL_SIZE);
	}

	public ExecutorService getExecutorService(String poolName, int poolSize) {
		ExecutorService executorService = null;
		executorService = this.map.get(poolName);
		if (executorService == null || executorService.isShutdown()) {
			logger.debug(">>FaceYe debug:create now executorService for :" + poolName);
			executorService = Executors.newFixedThreadPool(poolSize);
			this.map.put(poolName, executorService);
		} else {
			logger.debug(">>FaceYe debgu:got executorService from pool:" + poolName);
		}
		return executorService;
	}

	public void execute(ExecutorService executorService, Runnable runnable) {
		executorService.execute(runnable);
	}

	public void execute(String poolName, Runnable runnable, int poolSize) {
		ExecutorService executorService = this.getExecutorService(poolName, poolSize);
		this.execute(executorService, runnable);
	}

	@Deprecated
	public void execute(String poolName, List<Runnable> runnables, int poolSize) {
		ExecutorService executorService = this.getExecutorService(poolName, poolSize);
		if (CollectionUtils.isNotEmpty(runnables)) {
			for (Runnable runnable : runnables) {
				executorService.execute(runnable);
			}
		}
	}

	/**
	 * 线程池是否已关闭
	 * @todo
	 * @param poolName
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月19日
	 */
	public boolean isShutdonw(String poolName) {
		boolean res = false;
		ExecutorService executorService = null;
		executorService = this.map.get(poolName);
		if (null == executorService) {
			res = true;
		} else {
			res = executorService.isShutdown();
		}
		return res;
	}

}
