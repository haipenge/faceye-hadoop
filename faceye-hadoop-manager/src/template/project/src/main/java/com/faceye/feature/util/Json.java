package com.faceye.feature.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * Json 工具类
 * 
 * @author haipeng
 * 
 */
public class Json {
	private static Logger logger = LoggerFactory.getLogger(Json.class);
	private static Gson gson = null;
	private static JsonGenerator jsonGenerator = null;
	private static ObjectMapper objectMapper = null;
	static {
		// gson = new Gson();
		// 不将"<","="等转义
		// new GsonBuilder().
//		gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
		// gson=new GsonBuilder().setPrettyPrinting().create();
		objectMapper = new ObjectMapper();
		// 解析器支持解析单引号
//		objectMapper.configure(Feature.INTERN_FIELD_NAMES, true);
		// 解析器支持解析结束符
		// objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
		// jsonGenerator = objectMapper.getJsonFactory().cr;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T toObject(String json, Class<T> clazz) {
		T o = null;
		if (StringUtils.isNotEmpty(json) && clazz != null) {
			// o = gson.fromJson(json, clazz);
			try {
				o = objectMapper.readValue(json, clazz);
			} catch (JsonParseException e) {
				logger.error(">>--->" + e.getMessage());
			} catch (JsonMappingException e) {
				logger.error(">>--->" + e.getMessage());
			} catch (IOException e) {
				logger.error(">>--->" + e.getMessage());
			}

		}
		return o;
	}

	public static String toJson(Object o) {
		String json = "";
		// json = gson.toJson(o);
		try {
			json = objectMapper.writeValueAsString(o);
		} catch (JsonGenerationException e) {
			logger.error(">>--->" + e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(">>--->" + e.getMessage());
		} catch (IOException e) {
			logger.error(">>--->" + e.getMessage());
		}

		logger.debug(">>FaceYe -> json is:" + json);
		return json;
	}

	public static void print(HttpServletResponse response, String json) throws IOException {
		response.setContentType("text/json");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

//	public static void print(String json) throws IOException {
//		HttpServletResponse response = ServletActionContext.getResponse();
//		print(response, json);
//	}
}
