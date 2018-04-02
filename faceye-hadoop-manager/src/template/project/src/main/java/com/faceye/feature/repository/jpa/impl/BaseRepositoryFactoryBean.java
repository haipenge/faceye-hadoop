package com.faceye.feature.repository.jpa.impl;

import static org.springframework.data.querydsl.QueryDslUtils.QUERY_DSL_PRESENT;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.faceye.feature.repository.jpa.BaseRepository;

public class BaseRepositoryFactoryBean<R extends JpaRepository<T, ID>, T, ID extends Serializable> extends
		JpaRepositoryFactoryBean<R, T, ID> {
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new BaseRepositoryFactory(entityManager);
	}

	private static class BaseRepositoryFactory extends JpaRepositoryFactory {
		private final EntityManager entityManager;

		public BaseRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;

		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(RepositoryMetadata metadata,
				EntityManager entityManager) {
			Class<?> repositoryInterface = metadata.getRepositoryInterface();
			JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());

			SimpleJpaRepository<?, ?> repo = null;
			Boolean isQueryDslExecutor = isQueryDslExecutor(repositoryInterface);
			if (isQueryDslExecutor) {
				repo = new QueryDslJpaRepository(entityInformation, entityManager);
			} else {
				repo = new BaseRepositoryImpl(entityInformation, entityManager);
			}
			return repo;
		}

		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			if (isQueryDslExecutor(metadata.getRepositoryInterface())) {
				return QueryDslJpaRepository.class;
			} else {
				return BaseRepository.class;
			}
		}

		private boolean isQueryDslExecutor(Class<?> repositoryInterface) {
			return QUERY_DSL_PRESENT && QueryDslPredicateExecutor.class.isAssignableFrom(repositoryInterface);
		}
	}
}
