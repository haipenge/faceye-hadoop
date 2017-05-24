package com.faceye.feature.repository.mongo.impl;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;

public class BaseMongoRepositoryBean<R extends MongoRepository<T, ID>,T,ID extends Serializable> extends MongoRepositoryFactoryBean<R, T, ID> {

}
