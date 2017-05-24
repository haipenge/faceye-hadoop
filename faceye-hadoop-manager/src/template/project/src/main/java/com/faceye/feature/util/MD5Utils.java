package com.faceye.feature.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Utils {
    private static Logger logger=LoggerFactory.getLogger(MD5Utils.class);
	public static String md5(String password) {
		MessageDigest md;
		StringBuffer buf = new StringBuffer();
		try {
			md = MessageDigest.getInstance("MD5");
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
		return buf.toString();
	}
}
