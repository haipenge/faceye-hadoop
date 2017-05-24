package com.faceye.feature.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SHA加密
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2015年1月27日
 */
public class SHAUtils {

     private static Logger logger=LoggerFactory.getLogger(SHAUtils.class);
	/**
	 * 对字符进行SHA
	 * @todo
	 * @param password
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年1月27日
	 */
	public static String sha(String password){
		StringBuffer buf = new StringBuffer();
		if (StringUtils.isNotEmpty(password)) {
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("SHA1");
				md.update(password.getBytes());
				byte[] bits = md.digest();
				for (int i = 0; i < bits.length; i++) {
					int a = bits[i];
					if (a < 0)
						a += 256;
					if (a < 16)
						buf.append("0");
					buf.append(Integer.toHexString(a));
				}
			} catch (NoSuchAlgorithmException e) {
				logger.error(">>FaceYe throws Exception: --->" + e.toString());
			}
		}
		return buf.toString();
	}
}
