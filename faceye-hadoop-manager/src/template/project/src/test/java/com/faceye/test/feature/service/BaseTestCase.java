package com.faceye.test.feature.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础测试用例
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年10月23日
 */

@RunWith(JUnit4.class)
abstract public class BaseTestCase {

	protected Logger logger=LoggerFactory.getLogger(getClass());
	/**
	 * 基础测试环境测试
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年10月23日
	 */
	@Test
	public void testEnv() throws Exception{
		logger.debug(">>FaceYe : BaseTestCase Test Env now.");
	}
}
