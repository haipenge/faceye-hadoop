package com.faceye.feature.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.faceye.feature.service.Reporter;

/**
 * 打印报告
 * 
 * @author haipeng
 * 
 */
@Service
public class PrintReporter implements Reporter {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void reporter(Object o) {
		if (o == null) {
			return;
		}
		if (o instanceof List) {
			reporter((List) o);
		} else if (o instanceof Map) {
			reporter((Map) o);
		} else {
			reporter(o.toString());
		}
	}

	private void reporter(List items) {
		if (items != null && !items.isEmpty()) {
			for (int i = 0; i < items.size(); i++) {
				Object o = items.get(i);
				if (o == null) {
					continue;
				}
				if (o instanceof Map) {
					reporter((Map) o);
				} else if (o instanceof String) {
					reporter(o.toString());
				}
			}
		}
	}

	private void reporter(Map map) {
		if (map != null && !map.isEmpty()) {
			Iterator it = map.keySet().iterator();
			while (it.hasNext()) {
				Object key = it.next();
				Object value = map.get(key);
				if (value != null && value instanceof List) {
					reporter((List) value);
				} else {
					String line = "" + key + "=" + value;
					reporter(line);
				}
			}
		}
	}

	private void reporter(String str) {
		logger.debug(str);
	}

}
