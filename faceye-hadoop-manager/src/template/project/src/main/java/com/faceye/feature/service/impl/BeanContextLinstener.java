package com.faceye.feature.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.repository.util.ClassUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.faceye.feature.service.SequenceService;

@Component
public class BeanContextLinstener implements ApplicationListener, ApplicationContextAware {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ApplicationContext applicationContext = null;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
//		logger.debug(">>Faceye --> Envent is" + event.getClass().getName());
		if (event instanceof ContextRefreshedEvent) {
			SequenceService sequenceService = this.applicationContext.getBean(SequenceService.class);
			Map<String, BaseMongoServiceImpl> beans = this.applicationContext.getBeansOfType(BaseMongoServiceImpl.class);
			if (MapUtils.isNotEmpty(beans)) {
				Iterator<String> it = beans.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					logger.debug(">>FaceYe --> key of bean is:" + key);
					BaseMongoServiceImpl bean = beans.get(key);
					if(bean!=null){
						if(bean.sequenceService==null){
							bean.sequenceService=sequenceService;
						}
					}
					Method method = ReflectionUtils.findMethod(bean.getClass(), "resetEntitySequenceID");
					if (method == null) {
						logger.debug(">>FaceYe -->没有找到方法:resetEntitySequenceID");
					} else {
						ReflectionUtils.invokeMethod(method, bean);
					}
				}
			}
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
