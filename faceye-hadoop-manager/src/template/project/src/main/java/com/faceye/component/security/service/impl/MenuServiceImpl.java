package com.faceye.component.security.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faceye.component.security.entity.Menu;
import com.faceye.component.security.entity.Resource;
import com.faceye.component.security.entity.Role;
import com.faceye.component.security.repository.jpa.MenuRepository;
import com.faceye.component.security.service.MenuService;
import com.faceye.component.security.service.ResourceService;
import com.faceye.component.security.service.RoleService;
import com.faceye.feature.service.impl.BaseServiceImpl;
 

@Service
@Transactional
public class MenuServiceImpl extends BaseServiceImpl<Menu, Long, MenuRepository> implements MenuService {
	@Autowired
	private RoleService roleService = null;
	@Autowired
	private ResourceService resourceService = null;

	@Autowired
	public MenuServiceImpl(MenuRepository dao) {
		super(dao);
	}

	/**
	 * 删除一个菜单
	 */
	@Override
	public void remove(Long id)   {
		Menu menu =this.get(id);
		this.remove(menu);
	}
	@Override
	public void remove(Menu entity)  {
//		Resource resource=entity.getResource();
//		this.resourceService.remove(resource);
//		entity.setRoles(new HashSet<Role>(0));
//		this.save(entity);
//		entity.getRoles().clear();
//		entity.getChildren().clear();
		this.dao.delete(entity);
		Resource resource=entity.getResource();
		this.resourceService.remove(resource);
		
	}
	/**
	 * 角色-菜单授权
	 */
	@Override
	public void saveAuthRoles(Long roleId, Long[] menuIds) {
		Role role = this.roleService.get(roleId);
		Set<Menu> menus = role.getMenus();
		if (menus == null) {
			menus = new HashSet<Menu>(0);
		}
		if (role != null) {
			if (menuIds == null) {
				// 如果menuIds为空,则将已授于的角色资源权限去除
				Set<Resource> resources = role.getResources();
				Iterator<Menu> it = menus.iterator();
				while (it.hasNext()) {
					Menu menu = it.next();
					Resource resource = menu.getResource();
					if (resources.contains(resource)) {
						resources.remove(resource);
					}
				}
				role.setResources(resources);
				menus.clear();
				role.setMenus(menus);
				this.roleService.save(role);
			} else {
				for (long menuId : menuIds) {
					Menu menu = this.dao.getOne(menuId);
					if (!menus.contains(menu)) {
						menus.add(menu);
						Resource resource = menu.getResource();
						role.getResources().add(resource);
					}
				}
				role.setMenus(menus);
				this.roleService.save(role);
			}
		}
	}

	@Override
	public void saveMenu(Map params) {
		Long id = MapUtils.getLong(params, "id");
		Menu menu = null;
		Resource resource = null;
		if (id != null) {
			menu = this.dao.getOne(id);
		} else {
			menu = new Menu();
		}
		menu.setName(MapUtils.getString(params, "name"));
		menu.setType(MapUtils.getInteger(params, "type"));
		String url = MapUtils.getString(params, "url");
		Long parentId=MapUtils.getLong(params, "parentId");
		
		if (StringUtils.isNotEmpty(url)) {
			resource = this.resourceService.getResourceByUrl(url);
		}
		if (resource == null) {
			resource = new Resource();
			resource.setName(menu.getName());
			resource.setUrl(url);
			this.resourceService.save(resource);
		}
		menu.setResource(resource);
		if(parentId!=null){
			Menu parent=this.dao.findOne(parentId);
			menu.setParent(parent);
		}
		this.dao.save(menu);
	}

	@Override
	public List<Menu> getChildren(Long parentId) {
		return this.dao.getChildren(parentId);
	}

	@Override
	public List<Menu> getRoots() {
		return dao.getRoots();
	}

}
/**@generate-service-source@**/
