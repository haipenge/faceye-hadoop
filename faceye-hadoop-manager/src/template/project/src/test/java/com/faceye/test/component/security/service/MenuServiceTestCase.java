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

import com.faceye.component.security.entity.Menu;
import com.faceye.feature.repository.jpa.SearchFilter;
import com.faceye.component.security.service.MenuService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Menu  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class MenuServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private MenuService menuService = null;
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
		Assert.isTrue(menuService != null);
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
//		this.menuService.removeAllInBatch();
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
		Map params=new HashMap();
		params.put("name", "test-1");
		params.put("parentId", new Long(1));
		params.put("url", "ajax/test");
		this.menuService.saveMenu(params);
		List<Menu> entites = this.menuService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Menu entity = new Menu();
		this.menuService.save(entity);
		List<Menu> entites = this.menuService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Menu entity = new Menu();
			this.menuService.save(entity);
		}
		List<Menu> entities = this.menuService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Menu entity = new Menu();
		this.menuService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Menu e = this.menuService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Menu entity = new Menu();
		this.menuService.save(entity);
		this.menuService.remove(entity);
		List<Menu> entities = this.menuService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Menu entity = new Menu();
			this.menuService.save(entity);
		}
		List<Menu> entities = this.menuService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.menuService.removeAllInBatch();
		entities = this.menuService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Menu entity = new Menu();
			this.menuService.save(entity);
		}
		this.menuService.removeAll();
		List<Menu> entities = this.menuService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Menu> entities = new ArrayList<Menu>();
		for (int i = 0; i < 5; i++) {
			Menu entity = new Menu();
			
			this.menuService.save(entity);
			entities.add(entity);
		}
		this.menuService.removeInBatch(entities);
		entities = this.menuService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Menu entity = new Menu();
			this.menuService.save(entity);
		}
		List<Menu> entities = this.menuService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Menu entity = new Menu();
			this.menuService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Menu> page = this.menuService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.menuService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.menuService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Menu entity = new Menu();
			this.menuService.save(entity);
			id = entity.getId();
		}
		Menu e = this.menuService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Menu entity = new Menu();
			this.menuService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Menu> entities = this.menuService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
	
}
