package com.faceye.feature.util;

import java.util.Random;

public class MathUtil {

	/**
	 * 最后一个生成的随机数
	 */
	private static int LAST_RAND_NUM = 0;

	/**
	 * 生成随机数
	 * @todo
	 * @param start
	 * @param end
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年10月1日
	 */
	synchronized public static int getRandInt(int start, int end) {
		int rand = 0;
		Random random = new Random();
		rand = start + random.nextInt(end - start);
		//在这里，要保证最近两次生成的随机数不一样
//		if (rand == LAST_RAND_NUM || Math.abs(LAST_RAND_NUM-rand)<=2) {
//			rand = getRandInt(start, end);
//		} else {
//			LAST_RAND_NUM = rand;
//		}
		return rand;
	}
}
