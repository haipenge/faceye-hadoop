package com.faceye.feature.repository.mongo;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

import com.faceye.feature.repository.SearchFilter;
import com.faceye.feature.repository.SearchFilter.Operator;
import com.faceye.feature.service.Reporter;
import com.faceye.feature.service.impl.BeanContextUtil;
import com.google.common.collect.Lists;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.Path;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathBuilder;
import com.mysema.query.types.path.SimplePath;
import com.mysema.query.types.path.StringPath;

/**
 * Mongo DB Query DSL 动态参数解析
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2015年2月12日
 */
public class DynamicSpecifications {
	private static Logger logger = LoggerFactory.getLogger(DynamicSpecifications.class);

	public static <T> Predicate builder(Map searchParams, Class<T> clazz) {
		List<Predicate> predicates = Lists.newArrayList();
		Predicate predicate = null;
		Map<String, SearchFilter> searchFilters = SearchFilter.parse(searchParams);
		SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		EntityPath<T> entityPath = resolver.createPath(clazz);
		PathBuilder<T> builder = new PathBuilder<T>(entityPath.getType(), entityPath.getMetadata());
		Path path = entityPath.getRoot();
		if (MapUtils.isNotEmpty(searchFilters)) {
			for (SearchFilter searchFilter : searchFilters.values()) {
				Operator operator = searchFilter.operator;
				String fieldName = searchFilter.fieldName;
				Object fieldValue = searchFilter.value;
				switch (operator) {
				case EQ:
					if (fieldValue != null) {
						if (fieldValue instanceof String) {
							StringPath stringPath = new StringPath(path, fieldName);
							predicates.add(stringPath.eq(fieldValue.toString()));
						} else if (fieldValue instanceof Number) {
							NumberPath numberPath = new NumberPath(fieldValue.getClass(), path, fieldName);
							predicates.add(numberPath.eq(fieldValue));
						} else {
							SimplePath simplePath = builder.getSimple(fieldName, fieldValue.getClass());
							predicates.add(simplePath.eq(fieldValue));
						}
					} else {
						logger.debug(">>FaceYe --> field value is empty of field" + fieldName);
					}
					break;
				case NE:
					if (fieldValue != null) {
						if (fieldValue instanceof String) {
							StringPath stringPath = new StringPath(path, fieldName);
							predicates.add(stringPath.ne(fieldValue.toString()));
						} else if (fieldValue instanceof Number) {
							NumberPath numberPath = new NumberPath(fieldValue.getClass(), path, fieldName);
							predicates.add(numberPath.ne(fieldValue));
						} else {
							SimplePath simplePath = builder.getSimple(fieldName, fieldValue.getClass());
							predicates.add(simplePath.ne(fieldValue));
						}
					} else {
						logger.debug(">>FaceYe --> field value is empty of field" + fieldName);
					}
					break;
				case GT:
					if (fieldValue != null) {
						if (fieldValue instanceof String) {
							StringPath stringPath = new StringPath(path, fieldName);
							predicates.add(stringPath.gt(fieldValue.toString()));
						} else if (fieldValue instanceof Number) {
							NumberPath numberPath = new NumberPath(fieldValue.getClass(), path, fieldName);
							predicates.add(numberPath.gt((Number) fieldValue));
						} else {

						}
					} else {
						logger.debug(">>FaceYe --> field value is empty of field" + fieldName);
					}
					break;
				case GTE:
					if (fieldValue != null) {
						if (fieldValue instanceof String) {
							StringPath stringPath = new StringPath(path, fieldName);
							predicates.add(stringPath.goe(fieldValue.toString()));
						} else if (fieldValue instanceof Number) {
							NumberPath numberPath = new NumberPath(fieldValue.getClass(), path, fieldName);
							predicates.add(numberPath.goe((Number) fieldValue));
						} else {

						}
					} else {
						logger.debug(">>FaceYe --> field value is empty of field" + fieldName);
					}
					break;
				case LT:
					if (fieldValue != null) {
						if (fieldValue instanceof String) {
							StringPath stringPath = new StringPath(path, fieldName);
							predicates.add(stringPath.lt(fieldValue.toString()));
						} else if (fieldValue instanceof Number) {
							NumberPath numberPath = new NumberPath(fieldValue.getClass(), path, fieldName);
							predicates.add(numberPath.lt((Number) fieldValue));
						} else {

						}
					} else {
						logger.debug(">>FaceYe --> field value is empty of field" + fieldName);
					}
					break;
				case LTE:
					if (fieldValue != null) {
						if (fieldValue instanceof String) {
							StringPath stringPath = new StringPath(path, fieldName);
							predicates.add(stringPath.loe(fieldValue.toString()));
						} else if (fieldValue instanceof Number) {
							NumberPath numberPath = new NumberPath(fieldValue.getClass(), path, fieldName);
							predicates.add(numberPath.loe((Number) fieldValue));
						} else {

						}
					} else {
						logger.debug(">>FaceYe --> field value is empty of field" + fieldName);
					}
					break;
				case LIKE:
					if (fieldValue != null) {
						if (fieldValue instanceof String) {
							StringPath stringPath = new StringPath(path, fieldName);
							predicates.add(stringPath.like("%" + fieldValue.toString() + "%"));
						}
					} else {
						logger.debug(">>FaceYe --> field value is empty of field" + fieldName);
					}
					break;
				case ISTRUE:
					BooleanPath isTrueBooleanPath = new BooleanPath(path, fieldName);
					predicates.add(isTrueBooleanPath.isTrue());
					break;
				case ISFALSE:
					BooleanPath isFalseBooleanPath = new BooleanPath(path, fieldName);
					predicates.add(isFalseBooleanPath.isFalse());
					break;
				case ISEMPTY:
					if (fieldValue instanceof String) {
						StringPath stringPath = new StringPath(path, fieldName);
						predicates.add(stringPath.isEmpty());
					} else if (fieldValue instanceof Number) {
						NumberPath numberPath = new NumberPath(fieldValue.getClass(), path, fieldName);
						predicates.add(numberPath.isNull());
					} else {
						SimplePath simplePath = builder.getSimple(fieldName, fieldValue.getClass());
						predicates.add(simplePath.isNull());
					}
					break;
				case ISNULL:
					if (fieldValue instanceof String) {
						StringPath stringPath = new StringPath(path, fieldName);
						predicates.add(stringPath.isNull());
					} else if (fieldValue instanceof Number) {
						NumberPath numberPath = new NumberPath(fieldValue.getClass(), path, fieldName);
						predicates.add(numberPath.isNull());
					} else {
						SimplePath simplePath = builder.getSimple(fieldName, fieldValue.getClass());
						predicates.add(simplePath.isNull());
					}
					break;
				default:
				}
			}
		}
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		if (CollectionUtils.isNotEmpty(predicates)) {
			for (Predicate p : predicates) {
				booleanBuilder.and(p);
			}
		}
		predicate = booleanBuilder.getValue();
		if (null != predicate) {
			logger.debug(">>FaceYe --> Predicate is :" + predicate.toString());
		}
		return predicate;
	}

}
