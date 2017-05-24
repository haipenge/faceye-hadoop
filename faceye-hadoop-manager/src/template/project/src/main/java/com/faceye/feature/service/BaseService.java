package com.faceye.feature.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.faceye.feature.util.ServiceException;

public interface BaseService<T, ID extends Serializable> {
	/**
	 * 保存数据
	 * 
	 * @param entity
	 * @throws Exception
	 * @Date:Create Date:2014年3月19日
	 * @Author @haipenge
	 */
	public void save(T entity) throws ServiceException;
	
	/**
	 * 批量保存
	 * @todo
	 * @param entities
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	
	public void save(Iterable<T> entities) throws ServiceException;
	
	/**
	 * 
	 * @todo
	 * @param entity
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	public void saveAndFlush(T entity) throws ServiceException;

	

	/**
	 * 删除
	 * 
	 * @param id
	 * @throws Exception
	 * @Date:Create Date:2014年3月19日
	 * @Author @haipenge
	 */
	public void remove(ID id) throws ServiceException;
	
	/**
	 * 删除
	 * @todo
	 * @param entity
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月19日
	 */
	public void remove(T entity) throws ServiceException;
	
	/**
	 * 
	 * @todo
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	public void removeAll() throws ServiceException;
	
	/**
	 * 
	 * @todo
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	public void removeAllInBatch() throws ServiceException;
	
	/**
	 * 批量删除
	 * @todo
	 * @param entities
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	public void removeInBatch(Iterable<T> entities) throws ServiceException;
	
	
	/**
	 * 根据主键进行数据查询
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @Date:Create Date:2014年3月19日
	 * @Author @haipenge
	 */
	public T get(ID id) throws ServiceException;
	
	
	/**
	 * 
	 * @todo
	 * @return
	 * @throws ServiceException
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
    public List<T> getAll() throws ServiceException;
    
    /**
     * 查询ID列表的值
     * @todo
     * @param ids
     * @return
     * @throws ServiceException
     * @author:@haipenge
     * haipenge@gmail.com
     * 2014年5月20日
     */
    public List<T> getAll(Iterable<ID> ids) throws ServiceException;
    /**
     * 分页查询
     * @todo
     * @param pageable
     * @return
     * @throws ServiceException
     * @author:@haipenge
     * haipenge@gmail.com
     * 2014年5月20日
     */
    public Page<T> getPage(Map<String,Object> searchParams,int page,int size) throws ServiceException;
}
