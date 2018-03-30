package com.faceye.test.feature.util;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.junit.Test;

import com.faceye.feature.util.SHAUtils;
import com.faceye.test.feature.service.BaseTestCase;

public class SHAUtilsTestCase extends BaseTestCase {

	@Test
	public void testSHA() throws Exception{
		String sha=SHAUtils.sha("ecsunchina147");
		logger.debug(">>FaceYe -> sha is :"+sha);
		//b1e6d52ecba7cbfd111d515e8db0011ed39de189
		//b1e6d52ecba7cbfd111d515e8db0011ed39de189
		Assert.assertTrue(StringUtils.isNotEmpty(sha)&&StringUtils.equals(sha, "b1e6d52ecba7cbfd111d515e8db0011ed39de189"));
	}
}
