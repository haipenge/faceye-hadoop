package com.faceye.feature.repository.jpa;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
@NoRepositoryBean
public interface BaseRepository <T, ID extends Serializable> extends JpaRepository<T,ID>,JpaSpecificationExecutor<T>{
	/**
	 * 增加JPA通用分页查询
	 * @todo
	 * @param searchParams
	 * @param page
	 * @param size
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月24日
	 */
	public Page<T> getPage(Map<String, Object> searchParams, int page, int size) ;
	
	
	/**
	 * 使用SQL进行查询
	 * @todo
	 * @param params,查询参数的封装
	 * @param sql
	 * @param page
	 * @param size
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月24日
	 */
	public Page<?> getPage(Map<String,Object> params,String sql,String resultSetMapping,int page,int size);
	
	/**
	 * 查询总数
	 * @todo
	 * @param params
	 * @param sql
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月26日
	 */
	public long getCount(Map<String,Object> params,String sql);
	
}
