package com.faceye.test.component.security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.junit.Assert;

import com.faceye.component.security.entity.Resource;
import com.faceye.feature.repository.jpa.SearchFilter;
import com.faceye.component.security.service.ResourceService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Resource  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class ResourceServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private ResourceService resourceService = null;
	/**
	 * 初始化
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Before
	public void set() throws Exception {
		Assert.assertTrue(resourceService != null);
	}

	/**
	 * 清理
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@After
	public void after() throws Exception {
		this.resourceService.removeAllInBatch();
	}

	/**
	 *  保存测试
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Test
	public void testSave() throws Exception {
		Resource entity = new Resource();
		entity.setName("test");
		this.resourceService.save(entity);
		List<Resource> entites = this.resourceService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Resource entity = new Resource();
		this.resourceService.save(entity);
		List<Resource> entites = this.resourceService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Resource entity = new Resource();
			this.resourceService.save(entity);
		}
		List<Resource> entities = this.resourceService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Resource entity = new Resource();
		this.resourceService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Resource e = this.resourceService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Resource entity = new Resource();
		this.resourceService.save(entity);
		this.resourceService.remove(entity);
		List<Resource> entities = this.resourceService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Resource entity = new Resource();
			this.resourceService.save(entity);
		}
		List<Resource> entities = this.resourceService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.resourceService.removeAllInBatch();
		entities = this.resourceService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Resource entity = new Resource();
			this.resourceService.save(entity);
		}
		this.resourceService.removeAll();
		List<Resource> entities = this.resourceService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Resource> entities = new ArrayList<Resource>();
		for (int i = 0; i < 5; i++) {
			Resource entity = new Resource();
			
			this.resourceService.save(entity);
			entities.add(entity);
		}
		this.resourceService.removeInBatch(entities);
		entities = this.resourceService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Resource entity = new Resource();
			this.resourceService.save(entity);
		}
		List<Resource> entities = this.resourceService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Resource entity = new Resource();
			this.resourceService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Resource> page = this.resourceService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.resourceService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.resourceService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Resource entity = new Resource();
			this.resourceService.save(entity);
			id = entity.getId();
		}
		Resource e = this.resourceService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Resource entity = new Resource();
			this.resourceService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Resource> entities = this.resourceService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
