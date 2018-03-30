package com.faceye.test.feature.service;

import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.feature.service.RedisService;

public class RedisServiceTestCase extends BaseServiceTestCase {

	
	@Autowired
	private RedisService redisService=null;
	
	@Test
	public void testGetKeys() throws Exception{
		Set keys=redisService.getKeys("*");
		Object o=this.redisService.get("t");
		Assert.assertTrue(null!=keys && keys.size()>0);
	}
}
