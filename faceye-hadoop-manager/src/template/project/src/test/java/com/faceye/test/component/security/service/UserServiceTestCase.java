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
import org.springframework.util.Assert;

import com.faceye.component.security.entity.User;
import com.faceye.feature.repository.jpa.SearchFilter;
import com.faceye.component.security.service.UserService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * User  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class UserServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private UserService userService = null;
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
		Assert.isTrue(userService != null);
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
		this.userService.removeAllInBatch();
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
		User entity = new User();
		this.userService.save(entity);
		List<User> entites = this.userService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		User entity = new User();
		this.userService.save(entity);
		List<User> entites = this.userService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			User entity = new User();
			this.userService.save(entity);
		}
		List<User> entities = this.userService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		User entity = new User();
		this.userService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		User e = this.userService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		User entity = new User();
		this.userService.save(entity);
		this.userService.remove(entity);
		List<User> entities = this.userService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			User entity = new User();
			this.userService.save(entity);
		}
		List<User> entities = this.userService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.userService.removeAllInBatch();
		entities = this.userService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			User entity = new User();
			this.userService.save(entity);
		}
		this.userService.removeAll();
		List<User> entities = this.userService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<User> entities = new ArrayList<User>();
		for (int i = 0; i < 5; i++) {
			User entity = new User();
			
			this.userService.save(entity);
			entities.add(entity);
		}
		this.userService.removeInBatch(entities);
		entities = this.userService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			User entity = new User();
			this.userService.save(entity);
		}
		List<User> entities = this.userService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			User entity = new User();
			this.userService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<User> page = this.userService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.userService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.userService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			User entity = new User();
			this.userService.save(entity);
			id = entity.getId();
		}
		User e = this.userService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			User entity = new User();
			this.userService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<User> entities = this.userService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
