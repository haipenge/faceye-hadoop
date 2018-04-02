package com.faceye.test.component.security.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.security.entity.Role;
import com.faceye.component.security.repository.jpa.RoleRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Role DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class RoleRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private RoleRepository roleRepository = null;

	@Before
	public void before() throws Exception {
		this.roleRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Role entity = new Role();
		this.roleRepository.save(entity);
		Iterable<Role> entities = this.roleRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Role entity = new Role();
		this.roleRepository.save(entity);
        this.roleRepository.deleteById(entity.getId());
        Iterable<Role> entities = this.roleRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Role entity = new Role();
		this.roleRepository.save(entity);
		Role role=this.roleRepository.findById(entity.getId()).get();
		Assert.assertTrue(role!=null);
	}

	
}
