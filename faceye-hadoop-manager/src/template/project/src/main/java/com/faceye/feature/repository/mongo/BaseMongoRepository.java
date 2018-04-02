package com.faceye.feature.repository.mongo;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BaseMongoRepository<T,ID extends Serializable> extends MongoRepository<T, ID>,QueryDslPredicateExecutor<T>{
	
}
