package com.faceye.feature.util;

import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class RandUtil {
	public static AtomicInteger _RAND_NUM_SEED = new AtomicInteger(0);
	public static String _PREFIX = "FaceYe";

	public static int getRandInt(int start, int end) {
		int rand = 0;
		Random random = new Random();
		rand = start + random.nextInt(end - start);
		return rand;
	}

	public static String randString(String prefix) {
		StringBuffer sb = new StringBuffer("");
		if (StringUtils.isEmpty(prefix)) {
			prefix = _PREFIX;
		}
		_RAND_NUM_SEED.incrementAndGet();
		sb.append(prefix);
		sb.append(StringPool.CHARACTER_SPLIT_LINE);
		long currentMillionSecond = System.currentTimeMillis();
		sb.append(currentMillionSecond);
		sb.append(StringPool.CHARACTER_SPLIT_LINE);
		int num = getRandInt(100, 999);
		sb.append(num);
		sb.append(StringPool.CHARACTER_SPLIT_LINE);
		sb.append(_RAND_NUM_SEED.get());
		return sb.toString();
	}

	public static String randDateString(String prefix) {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotEmpty(prefix)) {
			sb.append(prefix);
			sb.append("_");
		}
		Date date = new Date();
		String date2String = DateUtil.formatDate(date);
		date2String = date2String.replaceAll(" ", "-").replaceAll(":", "-");
		// String suffix=""+Calendar.YEAR + Calendar.MONTH + Calendar.DAY_OF_MONTH+ Calendar.HOUR + Calendar.MINUTE + Calendar.SECOND+Calendar.MILLISECOND;
		int num = getRandInt(100, 999);
		date2String += num;
		sb.append(date2String);
		return sb.toString();
	}

	public static String randDateString() {
		return randDateString(null);
	}

	public static String randString() {
		return randString(null);
	}

	public static String randId(String prefix) {
		String id = "";
		if (StringUtils.isNotEmpty(prefix)) {
			id = id + prefix;
		}
		String date2String = DateUtil.formatDate(new Date(), "yyyyMMddHHmmss");
		int num = getRandInt(100, 999);
		date2String += num;
		id = id + date2String;
		return id;
	}
}
