package com.faceye.feature.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import com.faceye.feature.repository.jpa.BaseRepository;
import com.faceye.feature.service.BaseService;
import com.faceye.feature.util.ServiceException;

import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Service基础类
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 * @param <T>
 * @param <ID>
 * @param <D>
 */
public class BaseServiceImpl<T, ID extends Serializable, D extends BaseRepository<T, ID>> implements BaseService<T, ID> {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected D dao = null;

	public BaseServiceImpl(D dao) {
		this.dao = dao;
	}

	@Override
	public void save(T entity) throws ServiceException {
		dao.save(entity);
	}

	@Override
	public void save(Iterable<T> entities) throws ServiceException {
		dao.save(entities);
	}

	@Override
	public void saveAndFlush(T entity) throws ServiceException {
		dao.saveAndFlush(entity);
	}

	@Override
	public T get(ID id) throws ServiceException {
		return dao.findOne(id);
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
	}

	@Override
	public void removeAllInBatch() throws ServiceException {
		dao.deleteAllInBatch();
	}

	@Override
	public void removeInBatch(Iterable<T> entities) throws ServiceException {
		dao.deleteInBatch(entities);
	}

	@Override
	public List<T> getAll() throws ServiceException {
		return dao.findAll();
	}

	@Override
	public List<T> getAll(Iterable<ID> ids) throws ServiceException {
		return dao.findAll(ids);
	}

	/**
	 * page :0,1,2,3
	 * 分页查询
	 */
	@Override
	public Page<T> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		return dao.getPage(searchParams, page, size);
	}

}
