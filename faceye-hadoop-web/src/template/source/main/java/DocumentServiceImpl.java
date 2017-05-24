package com.faceye.component.@component.name@.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.faceye.component.@component.name@.doc.@entity.name@;

import com.faceye.component.@component.name@.repository.mongo.@entity.name@Repository;
import com.faceye.component.@component.name@.service.@entity.name@Service;
import com.faceye.feature.service.impl.BaseServiceImpl;
import com.faceye.feature.util.ServiceException;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;

@Service
public class @entity.name@ServiceImpl extends BaseMongoServiceImpl<@entity.name@, Long, @entity.name@Repository> implements @entity.name@Service {

	@Autowired
	public @entity.name@ServiceImpl(@entity.name@Repository dao) {
		super(dao);
	}
	
	
}/**@generate-service-source@**/
