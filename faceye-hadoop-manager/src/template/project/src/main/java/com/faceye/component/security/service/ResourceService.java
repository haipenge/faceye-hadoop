package com.faceye.component.security.service;

import org.springframework.stereotype.Service;

import com.faceye.component.security.entity.Resource;
import com.faceye.feature.service.BaseService;

public interface ResourceService extends BaseService<Resource,Long>{

	public Resource getResourceByUrl(String url);
	
}/**@generate-service-source@**/
