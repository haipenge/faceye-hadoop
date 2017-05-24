package com.faceye.component.security.service;

import java.util.List;
import java.util.Map;

import com.faceye.component.security.entity.Menu;
import com.faceye.feature.service.BaseService;

public interface MenuService extends BaseService<Menu,Long>{
    /**
     * 菜单-角色授权
     * @todo
     * @param roleId
     * @param menuIds
     * @author:@haipenge
     * haipenge@gmail.com
     * 2014年7月1日
     */
	public void saveAuthRoles(Long roleId,Long [] menuIds);
	
	/**
	 * 保存菜单数据
	 * @todo
	 * @param params
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年7月2日
	 */
	public void saveMenu(Map params);
	
	/**
	 * 取得子节点
	 * @todo
	 * @param parentId
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年7月2日
	 */
	public List<Menu> getChildren(Long parentId);
	
	/**
	 * 取得根节点，一级节点，parent id  is null
	 * @todo
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年7月2日
	 */
	public List<Menu> getRoots();
}/**@generate-service-source@**/
