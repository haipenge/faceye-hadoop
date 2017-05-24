package com.faceye.feature.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.faceye.feature.service.PropertyService;
import com.faceye.feature.util.ServiceException;

@Service
public class PropertyServiceImpl implements PropertyService {
	@Value("#{property}")
	private Properties properties = null;
	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public String get(String key) throws ServiceException {
//		log.debug(">>-->key is:" + key);
		String res = "";
		if (properties == null) {
			log.debug(">>--> properties is null.");
			res = "";
		} else {
			res = properties.getProperty(key);
		}
		if (StringUtils.isEmpty(res)) {
			res = "";
		}
//		log.debug(">>--> get property-> key :"+key+",value:"+res+"  <<");
		return res;
	}
	
	

	@Override
	public String[] getKeys() throws ServiceException {
		String keys[]=null;
		if(properties!=null){
			Enumeration en=properties.keys();
			if(en!=null){
				List items=new ArrayList();
				while(en.hasMoreElements()){
					String key=en.nextElement().toString();
					items.add(key);
				}
				Collections.sort(items);
				keys=(String[])items.toArray(new String[items.size()]);
			}
		}
		return keys;
	}

}
