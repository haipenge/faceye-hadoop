package com.faceye.test.component.security.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.security.entity.Menu;
import com.faceye.component.security.repository.jpa.MenuRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Menu DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class MenuRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private MenuRepository menuRepository = null;

	@Before
	public void before() throws Exception {
//		this.menuRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Map params=new HashMap();
		params.put("name", "test-1");
		params.put("parentId", new Long(1));
		params.put("url", "ajax/test");
		
		Iterable<Menu> entities = this.menuRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Menu entity = new Menu();
		this.menuRepository.save(entity);
        this.menuRepository.deleteById(entity.getId());
        Iterable<Menu> entities = this.menuRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Menu entity = new Menu();
		this.menuRepository.save(entity);
		Menu menu=this.menuRepository.findById(entity.getId()).get();
		Assert.assertTrue(menu!=null);
	}
	
	@Test
	public void testGetChildren() throws Exception{
		
	}

	@Test
	public void testGetRoot() throws Exception{
	  Long parentId=null;
	  List<Menu> roots=this.menuRepository.getRoots();
	  Assert.assertTrue(CollectionUtils.isNotEmpty(roots));
	}
	
	@Test
	public void testGetRootByUserId() throws Exception{
		Long userId=209L;
		List<Menu> menus=this.menuRepository.getMenusByUserId(userId);
		Assert.assertTrue(CollectionUtils.isNotEmpty(menus)&&menus.size()==5);
	}
	
}
