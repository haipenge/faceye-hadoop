package com.faceye.feature.service.job.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础线程
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年7月7日
 */
abstract public class BaseThread implements Runnable{
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public void run() {
		try {
			doBusiness();
		} catch (Exception e) {
			logger.error(">>FaceYe :", e);
		}
	}

	abstract public void doBusiness();
}
