package com.faceye.feature.repository.jpa.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.faceye.feature.repository.SearchFilter;
import com.faceye.feature.repository.jpa.BaseRepository;
import com.faceye.feature.repository.jpa.DynamicSQLBuilder;
import com.faceye.feature.repository.jpa.DynamicSpecifications;
import com.faceye.feature.repository.jpa.SQLBuilderEntity;

/**
 * 扩展基础DAO实现
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月25日
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected EntityManager entityManager = null;
	// 默认数据库类型
	protected String DEFAULT_DATA_BASE_TYPE = "mysql";

	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager = em;
	}

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	/**
	 * 查询分页数据
	 */
	@Override
	public Page<T> getPage(Map<String, Object> searchParams, int page, int size) {
		Specification<T> specification = null;
		Page<T> result = null;
		Class clazz = this.getDomainClass();
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		specification = DynamicSpecifications.bySearchFilter(filters.values(), clazz);
		Sort sort = new Sort(Direction.DESC, "id");
		if (size != 0) {
			//前端页码从1开始，分页代码:从0开始
			Pageable pageable = new PageRequest(page-1, size);
			pageable=new PageRequest(page-1, size, sort);
			result = findAll(specification, pageable);
		} else {
			List<T> items = findAll(specification,sort);
			result = new PageImpl<T>(items);
		}
		return result;
	}

	/**
	 * 使用SQL进行数据查询
	 */
	public Page<?> getPage(Map<String, Object> params, String sql, String resultSetMapping, int page, int size) {
		Page<?> result = null;
		Pageable pageable = null;
		logger.debug(">>Before Builder,SQL is:" + sql);
		// 根据查询条件构造SQL
		Map<String, SearchFilter> filters = SearchFilter.parse(params);
		SQLBuilderEntity builderEntity = DynamicSQLBuilder.builder(filters, sql);
		// 防止注入攻击
		String realSQL = builderEntity.getSql();
		logger.debug(">>After Build,SQL is:" + sql);
		Query query = this.entityManager.createNativeQuery(realSQL, resultSetMapping);
		this.wrapperQuery(query, builderEntity);
		// 如果sie=0，则默认查询全部，如果size!=0,则进行分页
		if (size != 0) {
			query.setFirstResult((page - 1) * size);
			query.setMaxResults(size);
			pageable = new PageRequest(page, size);
		} else {
			query.setFirstResult(0);
		}
		List items = query.getResultList();
		// 进行分页查询
		long count = 0;
		if (size != 0) {
			count = this.getCount(params, sql);
		} else {
			count = items.size();
		}
		result = new PageImpl(items, pageable, count);
		return result;
	}

	/**
	 * 查询记录总数
	 */
	@Override
	public long getCount(Map<String, Object> params, String sql) {
		long count = 0;
		Map<String, SearchFilter> filters = SearchFilter.parse(params);
		SQLBuilderEntity builderEntity = DynamicSQLBuilder.builder(filters, sql);
		// 防止注入攻击
		sql = builderEntity.getSql();
		String countSQL = this.buildCountSQL(sql);
		Query countQuery = this.entityManager.createNativeQuery(countSQL);
		this.wrapperQuery(countQuery, builderEntity);
		Object singleObject = countQuery.getSingleResult();
		count = ((BigInteger) singleObject).longValue();
		return count;
	}

	/**
	 * 将SQL中的参数与值进行包装
	 * @todo
	 * @param query
	 * @param builderEntity
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月26日
	 */
	private void wrapperQuery(Query query, SQLBuilderEntity builderEntity) {
		if (query != null && builderEntity != null) {
			List<Object> paramValues = builderEntity.getParamValues();
			if (CollectionUtils.isNotEmpty(paramValues)) {
				for (int i = 0; i < paramValues.size(); i++) {
					Object value = paramValues.get(i);
					query.setParameter(i + 1, value);
				}
			}
		}
	}

	/**
	 * 构造查询总量的SQL
	 * @todo
	 * @param sql
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月24日
	 */
	private String buildCountSQL(String sql) {
		String countSQL = "";
		if (StringUtils.isNotEmpty(sql)) {
			sql = this.removeOrders(sql);
			String temp = sql.toLowerCase();
			int fromIndex = temp.indexOf("from ");
			if (fromIndex != -1) {
				// 如果有Order by,以下查询会失效
				countSQL = "select count(*) " + sql.substring(fromIndex);
			}
		}
		return countSQL;
	}

	/**
	 * 去除SQL中的Order by 语句 代码来自springside
	 * @todo
	 * @param sql
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月24日
	 */
	private static String removeOrders(String sql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

}
