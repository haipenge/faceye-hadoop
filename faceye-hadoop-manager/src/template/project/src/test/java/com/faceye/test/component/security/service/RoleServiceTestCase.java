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

import com.faceye.component.security.entity.Role;
import com.faceye.feature.repository.jpa.SearchFilter;
import com.faceye.component.security.service.RoleService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Role  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class RoleServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private RoleService roleService = null;
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
		Assert.assertTrue(roleService != null);
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
		this.roleService.removeAllInBatch();
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
		Role entity = new Role();
		this.roleService.save(entity);
		List<Role> entites = this.roleService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Role entity = new Role();
		this.roleService.save(entity);
		List<Role> entites = this.roleService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Role entity = new Role();
			this.roleService.save(entity);
		}
		List<Role> entities = this.roleService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Role entity = new Role();
		this.roleService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Role e = this.roleService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Role entity = new Role();
		this.roleService.save(entity);
		this.roleService.remove(entity);
		List<Role> entities = this.roleService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Role entity = new Role();
			this.roleService.save(entity);
		}
		List<Role> entities = this.roleService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.roleService.removeAllInBatch();
		entities = this.roleService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Role entity = new Role();
			this.roleService.save(entity);
		}
		this.roleService.removeAll();
		List<Role> entities = this.roleService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Role> entities = new ArrayList<Role>();
		for (int i = 0; i < 5; i++) {
			Role entity = new Role();
			
			this.roleService.save(entity);
			entities.add(entity);
		}
		this.roleService.removeInBatch(entities);
		entities = this.roleService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Role entity = new Role();
			this.roleService.save(entity);
		}
		List<Role> entities = this.roleService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Role entity = new Role();
			this.roleService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Role> page = this.roleService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.roleService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.roleService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Role entity = new Role();
			this.roleService.save(entity);
			id = entity.getId();
		}
		Role e = this.roleService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Role entity = new Role();
			this.roleService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Role> entities = this.roleService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
