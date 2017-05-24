package com.faceye.feature.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

/**
 * 包装ajax的请求结果
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年9月26日
 */
public class AjaxResult {

	private Logger logger = LoggerFactory.getLogger(AjaxResult.class);

	/**
	 * 默认成功结果
	 */
	private static DefaultResult DEFAULT_SUCCESS_RESULT = null;

	/**
	 * 默认失败结果
	 */
	private static DefaultResult DEFAULT_FAILER_RESULT = null;

	/**
	 * 静态内部类
	 * @author @haipenge 
	 * haipenge@gmail.com
	*  Create Date:2014年9月26日
	 */
	private static class AjaxResultHolder {
		private static final AjaxResult INSTANCE = new AjaxResult();
	}

	private AjaxResult() {
		if (null == DEFAULT_SUCCESS_RESULT) {
			DEFAULT_SUCCESS_RESULT = new DefaultResult();
			DEFAULT_SUCCESS_RESULT.setResult(Boolean.TRUE);
		}
		if (null == DEFAULT_FAILER_RESULT) {
			DEFAULT_FAILER_RESULT = new DefaultResult();
			DEFAULT_FAILER_RESULT.setResult(Boolean.FALSE);
		}
	}

	synchronized public static AjaxResult getInstance() {
		return AjaxResultHolder.INSTANCE;
	}

	/**
	 * 构建简单默认响应消息，用于标记操作是否成功
	 * @todo
	 * @param isSuccess
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年9月26日
	 */
	public String buildDefaultResult(Boolean isSuccess) {
		return Json.toJson(isSuccess ? DEFAULT_SUCCESS_RESULT : DEFAULT_FAILER_RESULT);
	}

	/**
	 * 构建带返回消息的默认响应
	 * @todo
	 * @param isSuccess
	 * @param msg
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年9月26日
	 */
	public String buildDefaultResult(Boolean isSuccess, String msg) {
		DefaultResult result = new DefaultResult();
		result.setResult(isSuccess);
		result.setMsg(msg);
		String res = Json.toJson(result);
		logger.debug(">>FaceYe-->json is:" + res);
		return res;
	}

	// //////////////////////////////////////////////////内部类////////////////////////////////////////////////////////////////////
	/**
	 * 简单默认返回结果
	 * @author @haipenge 
	 * haipenge@gmail.com
	*  Create Date:2014年9月26日
	 */

	class DefaultResult implements java.io.Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 936432368888388916L;

		private String msg = "";
		private Boolean result = Boolean.FALSE;

		public Boolean getResult() {
			return result;
		}

		public void setResult(Boolean result) {
			this.result = result;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

	}

}
