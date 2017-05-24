package com.faceye.component.security.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.faceye.component.security.entity.Menu;
import com.faceye.feature.repository.jpa.BaseRepository;
/**
 * Menu 实体DAO
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月20日
 */
public interface MenuRepository extends BaseRepository<Menu,Long> {
	 
	/**
	 * 查询子节点
	 * @todo
	 * @param parentId
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年7月2日
	 */
	@Query("from Menu menu where menu.parent.id=?1")
	public List<Menu> getChildren(Long parentId);
	/**
	 * 查询根节点
	 * @todo
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年7月2日
	 */
	@Query("from Menu menu where menu.parent.id is null")
	public List<Menu> getRoots();
	
	
	@Query("select menu from User  user inner join user.roles role inner join role.menus menu  where user.id =?1")
	public List<Menu> getMenusByUserId(Long userId);
}/**@generate-repository-source@**/
