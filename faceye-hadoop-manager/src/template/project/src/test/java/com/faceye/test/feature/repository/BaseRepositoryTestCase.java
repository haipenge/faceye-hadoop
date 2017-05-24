package com.faceye.test.feature.repository;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * 基础测试用例
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月19日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext.xml"})
@TransactionConfiguration(defaultRollback=true)
abstract public class BaseRepositoryTestCase {
	protected Logger logger=LoggerFactory.getLogger(getClass());
}
