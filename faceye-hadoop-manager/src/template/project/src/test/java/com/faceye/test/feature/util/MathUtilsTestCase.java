package com.faceye.test.feature.util;

import org.junit.Test;

import com.faceye.feature.util.MathUtil;
import com.faceye.test.feature.service.BaseTestCase;

public class MathUtilsTestCase extends BaseTestCase {

	@Test
	public void testRand() throws Exception{
		int rand  =MathUtil.getRandInt(0, 10);
		logger.debug(">>FaceYe --> rand is:"+rand);
	}
}
