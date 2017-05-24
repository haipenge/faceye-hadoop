/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.faceye.feature.repository;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.faceye.feature.service.Reporter;
import com.faceye.feature.service.impl.BeanContextUtil;
import com.google.common.collect.Maps;

public class SearchFilter {

	public enum Operator {
		EQ, LIKE, GT, LT, GTE, LTE, ISTRUE, ISFALSE, ISEMPTY, ISNULL, NE
	}

	public String fieldName;
	public Object value;
	public Operator operator;

	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * searchParams中key的格式为OPERATOR_FIELDNAME
	 */
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
		Reporter reporter = BeanContextUtil.getInstance().getBean(Reporter.class);
		reporter.reporter(searchParams);
		Map<String, SearchFilter> filters = Maps.newHashMap();
		if (MapUtils.isEmpty(searchParams)) {
			return filters;
		}
		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			// if (StringUtils.isBlank((String) value)) {
			// continue;
			// }

			// 拆分operator与filedAttribute
			String[] names = StringUtils.split(key, "|");
			if (names.length != 2) {
				// throw new IllegalArgumentException(key + " is not a valid search filter name");
				continue;
			}
			String filedName = names[1];
			Operator operator = Operator.valueOf(names[0]);

			// 创建searchFilter
			SearchFilter filter = new SearchFilter(filedName, operator, value);
			filters.put(key, filter);
		}

		return filters;
	}
}
