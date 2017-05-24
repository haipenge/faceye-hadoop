package com.faceye.feature.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.ClassUtils;

import com.faceye.feature.doc.Sequence;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.BaseService;
import com.faceye.feature.service.Reporter;
import com.faceye.feature.service.SequenceService;
import com.faceye.feature.util.ServiceException;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.NumberExpression;

/**
 *  基于Mongo的底层服务实现
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年8月10日
 * @param <T>
 * @param <ID>
 * @param <D>
 */
public abstract class BaseMongoServiceImpl<T, ID extends Serializable, D extends BaseMongoRepository<T, ID>> implements BaseService<T, ID> {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	public SequenceService sequenceService = null;

	@Autowired
	protected Reporter reporter = null;
	protected D dao = null;
	protected Class<T> entityClass = null;

	public BaseMongoServiceImpl(D dao) {
		this.dao = dao;
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class<T>) params[0];
	}

	@Override
	public void save(T entity) throws ServiceException {
		try {
			ID id = (ID) PropertyUtils.getProperty(entity, "id");
			if (id == null) {
				PropertyUtils.setProperty(entity, "id", this.sequenceService.getNextSequence(entityClass.getName()));
			}
		} catch (IllegalAccessException e) {
			logger.error(">>FaceYe throws Exception: --->" + e.toString());
		} catch (InvocationTargetException e) {
			logger.error(">>FaceYe throws Exception: --->" + e.toString());
		} catch (NoSuchMethodException e) {
			logger.error(">>FaceYe throws Exception: --->" + e.toString());
		}
		dao.save(entity);
	}

	@Override
	public void save(Iterable<T> entities) throws ServiceException {
		if (entities != null) {
			Iterator<T> iterator = entities.iterator();
			while (iterator.hasNext()) {
				T entity = iterator.next();
				ID id;
				try {
					id = (ID) PropertyUtils.getProperty(entity, "id");
					if (id == null) {
						PropertyUtils.setProperty(entity, "id", this.sequenceService.getNextSequence(entityClass.getName()));
					}
				} catch (IllegalAccessException e) {
					logger.error(">>FaceYe throws Exception: --->" + e.toString());
				} catch (InvocationTargetException e) {
					logger.error(">>FaceYe throws Exception: --->" + e.toString());
				} catch (NoSuchMethodException e) {
					logger.error(">>FaceYe throws Exception: --->" + e.toString());
				}
			}
		}
		dao.save(entities);
	}

	@Override
	public void saveAndFlush(T entity) throws ServiceException {
		save(entity);
	}

	@Override
	public void remove(ID id) throws ServiceException {
		dao.delete(id);
	}

	@Override
	public void remove(T entity) throws ServiceException {
		dao.delete(entity);
	}

	@Override
	public void removeAll() throws ServiceException {
		dao.deleteAll();
		;
	}

	@Override
	public void removeAllInBatch() throws ServiceException {
		this.removeAll();
	}

	@Override
	public void removeInBatch(Iterable<T> entities) throws ServiceException {
		this.dao.delete(entities);
	}

	@Override
	public T get(ID id) throws ServiceException {
		return dao.findOne(id);
	}

	@Override
	public List<T> getAll() throws ServiceException {
		return dao.findAll();
	}

	@Override
	public List<T> getAll(Iterable<ID> ids) throws ServiceException {
		List<T> res = null;
		Iterable<T> its = dao.findAll(ids);
		res = (List) its;
		return res;
	}

	@Override
	public Page<T> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		Predicate predicate=DynamicSpecifications.builder(searchParams, entityClass);
		Sort sort=new Sort(Direction.DESC,"id");
		Page<T> res=null;
		if(size!=0){
		Pageable pageable = new PageRequest(page, size,sort);
		 res = this.dao.findAll(predicate,pageable);
		}else{
//			OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<T>("id") {
//			})
			List<T> items=(List) this.dao.findAll(predicate);
			res=new PageImpl<T>(items);
			
		}
		return res;
	}

	/**
	 * 取得实体当前的最大ID
	 * @todo
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月10日
	 */
	public void resetEntitySequenceID() {
		Long maxId = 0L;
		Sort sort = new Sort(Direction.DESC, "id");
		Pageable pageable = new PageRequest(0, 1, sort);
		Page<T> page = this.dao.findAll(pageable);
		if (page != null && CollectionUtils.isNotEmpty(page.getContent())) {
			T entity = page.getContent().get(0);
			if (ClassUtils.hasAtLeastOneMethodWithName(entityClass, "getId")) {
				try {
					Object id = PropertyUtils.getProperty(entity, "id");
					if (null != id) {
						maxId = (Long) id;
					}
				} catch (IllegalAccessException e) {
					logger.error(">>FaceYe throws Exception: --->" + e.toString());
				} catch (InvocationTargetException e) {
					logger.error(">>FaceYe throws Exception: --->" + e.toString());
				} catch (NoSuchMethodException e) {
					logger.error(">>FaceYe throws Exception: --->" + e.toString());
				}
			}
		}
		Sequence sequence = this.sequenceService.getSequenceByName(entityClass.getName());
		sequence.setSeq(maxId);
		logger.debug(">>FaceYe --Reset entity class " + entityClass + " sequence id to :" + maxId);
		this.sequenceService.save(sequence);
	}

}
